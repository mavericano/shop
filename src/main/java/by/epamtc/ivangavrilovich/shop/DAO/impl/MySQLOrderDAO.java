package by.epamtc.ivangavrilovich.shop.DAO.impl;

import by.epamtc.ivangavrilovich.shop.DAO.ConnectionPool;
import by.epamtc.ivangavrilovich.shop.DAO.DAOProvider;
import by.epamtc.ivangavrilovich.shop.DAO.exceptions.DAOException;
import by.epamtc.ivangavrilovich.shop.DAO.interfaces.CartDAO;
import by.epamtc.ivangavrilovich.shop.DAO.interfaces.OrderDAO;
import by.epamtc.ivangavrilovich.shop.DAO.interfaces.ProductDAO;
import by.epamtc.ivangavrilovich.shop.bean.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLOrderDAO implements OrderDAO {
    private final static Logger logger = LogManager.getLogger();

    private void close(ResultSet rs, Statement st) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException ex) {
            logger.error("Error while closing ResultSet", ex);
        }
        try {
            if (st != null) st.close();
        } catch (SQLException ex) {
            logger.error("Error while closing PreparedStatement", ex);
        }
    }

    private void close(ResultSet rs, PreparedStatement ps) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException ex) {
            logger.error("Error while closing ResultSet", ex);
        }
        try {
            if (ps != null) ps.close();
        } catch (SQLException ex) {
            logger.error("Error while closing PreparedStatement", ex);
        }
    }

    private void close(PreparedStatement ps) {
        try {
            if (ps != null) ps.close();
        } catch (SQLException ex) {
            logger.error("Error while closing PreparedStatement", ex);
        }
    }

    @Override
    public void addOrder(Order order, List<CartedProduct> products) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "INSERT INTO orders(user_id,address,price,info) VALUES (?,?,?,?)";
        String sqlForId = "SELECT last_insert_id();";
        String sqlForOrdersProducts = "INSERT INTO orders_products(order_id, product_id, quantity) VALUES (?,?,?)";
        String sqlForCart = "DELETE FROM cart WHERE user_id=? and product_id=?";
        String sqlForProduct = "UPDATE products SET stock = stock-? WHERE product_id=?";
        String sqlForProductPop = "UPDATE products SET `times ordered` = `times ordered`+? WHERE product_id=?";
        PreparedStatement ps = null;
        PreparedStatement psForCart = null;
        PreparedStatement psForProduct = null;
        PreparedStatement psForProductPop = null;
        Statement st;
        ResultSet rs = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setInt(1, order.getUserId());
            ps.setString(2, order.getAddress());
            ps.setDouble(3, order.getPrice());
            ps.setString(4, order.getInfo());
            ps.executeUpdate();

            st = conn.createStatement();
            rs = st.executeQuery(sqlForId);
            rs.next();
            int orderId = rs.getInt(1);

            ps = conn.prepareStatement(sqlForOrdersProducts);
            psForCart = conn.prepareStatement(sqlForCart);
            psForProduct = conn.prepareStatement(sqlForProduct);
            psForProductPop = conn.prepareStatement(sqlForProductPop);
            for (CartedProduct product : products) {
                ps.setInt(1, orderId);
                ps.setInt(2, product.getProduct().getProductId());
                ps.setInt(3, product.getQuantity());
                ps.executeUpdate();
                psForCart.setInt(1, order.getUserId());
                psForCart.setInt(2, product.getProduct().getProductId());
                psForCart.executeUpdate();

                psForProduct.setInt(1, product.getQuantity());
                psForProduct.setInt(2, product.getProduct().getProductId());
                psForProduct.executeUpdate();

                psForProductPop.setInt(1, product.getQuantity());
                psForProductPop.setInt(2, product.getProduct().getProductId());
                psForProductPop.executeUpdate();

                ps.clearParameters();
                psForCart.clearParameters();
                psForProduct.clearParameters();
                psForProductPop.clearParameters();
            }
            conn.commit();
        } catch (SQLException e) {
            close(rs, ps);
            close(psForCart);
            close(psForProduct);
            try {
                conn.rollback();
            } catch (SQLException ex) {
                logger.error("Error while rolling back", ex);
                throw new DAOException("Error while closing PreparedStatement", ex);
            }
            logger.error("Error while adding order and getting id", e);
            throw new DAOException("Error while adding order and getting id", e);
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                logger.error("Error while setting autoCommit to true", e);
            }
            ConnectionPool.getInstance().returnConnection(conn);
        }
    }

    @Override
    public List<AdminOrder> viewPageAdminOrders(int offset, int recsPerPage) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "SELECT * FROM orders JOIN statuses ON orders.status = statuses.status_id ORDER BY order_id LIMIT ? OFFSET ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        int orderId;
        int userId;
        String address;
        int statusId;
        double price;
        String info;

        List<AdminOrder> orders = new ArrayList<>();
        Order order;
        User user;
        List<CartedProduct> orderedProducts = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, recsPerPage);
            ps.setInt(2, offset);
            rs = ps.executeQuery();
            while (rs.next()) {
                orderId = rs.getInt("order_id");
                userId = rs.getInt("user_id");
                address = rs.getString("address");
                statusId = rs.getInt("status");
                price = rs.getFloat("price");
                info = rs.getString("info");
                order = new Order(orderId, userId, address, statusId, price, info);
                user = DAOProvider.getInstance().getUserDAOImpl().readUserById(userId);
                orderedProducts = readProductsForOrderById(orderId, conn);
                orders.add(new AdminOrder(order, user, orderedProducts));
            }
        } catch (SQLException e) {
            logger.error(String.format("Error while reading page products for offset %d recsPerPage %d", offset, recsPerPage), e);
            throw new DAOException(String.format("Error while reading page products for offset %d recsPerPage %d", offset, recsPerPage), e);
        } finally {
            close(rs, ps);
            ConnectionPool.getInstance().returnConnection(conn);
        }

        return orders;
    }

    private List<CartedProduct> readProductsForOrderById(int orderId, Connection conn) throws DAOException {
        String sql = "SELECT product_id, quantity FROM orders_products WHERE order_id=?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        int productId;
        int quantity;
        Product currentProduct;
        List<CartedProduct> result = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();

            while (rs.next()) {
                productId = rs.getInt("product_id");
                quantity = rs.getInt("quantity");
                currentProduct = DAOProvider.getInstance().getProductDAOImpl().retrieveProductById(productId, true);
                result.add(new CartedProduct(currentProduct, quantity));
            }
        } catch (SQLException e) {
            logger.error(String.format("Error while reading products list order %d", orderId), e);
            throw new DAOException(String.format("Error while reading products list order %d", orderId), e);
        } finally {
            close(rs, ps);
        }

        return result;
    }

    @Override
    public List<Order> viewPageOrders(int offset, int recsPerPage) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "SELECT * FROM orders JOIN statuses ON orders.status = statuses.status_id ORDER BY order_id LIMIT ? OFFSET ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        int orderId;
        int userId;
        String address;
        int statusId;
        double price;
        String info;

        List<Order> orders = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, recsPerPage);
            ps.setInt(2, offset);
            rs = ps.executeQuery();
            while (rs.next()) {
                orderId = rs.getInt("order_id");
                userId = rs.getInt("user_id");
                address = rs.getString("address");
                statusId = rs.getInt("status");
                price = rs.getFloat("price");
                info = rs.getString("info");

                orders.add(new Order(orderId, userId, address, statusId, price, info));
            }
        } catch (SQLException e) {
            logger.error(String.format("Error while reading page products for offset %d recsPerPage %d", offset, recsPerPage), e);
            throw new DAOException(String.format("Error while reading page products for offset %d recsPerPage %d", offset, recsPerPage), e);
        } finally {
            close(rs, ps);
            ConnectionPool.getInstance().returnConnection(conn);
        }

        return orders;
    }

    @Override
    public int retrieveNumberOfOrders() throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "SELECT count(*) FROM orders";
        int numberOfOrders;
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            if (rs.next()) {
                numberOfOrders = rs.getInt(1);
            } else {
                logger.error("Result set for number of orders is empty");
                throw new DAOException("Result set for number of orders is empty");
            }
        } catch (SQLException e) {
            logger.error("Error while reading number of orders", e);
            throw new DAOException("Error while reading number of orders", e);
        } finally {
            close(rs, st);
            ConnectionPool.getInstance().returnConnection(conn);
        }

        return numberOfOrders;
    }

    @Override
    public void changeStatus(int orderId, int newStatus) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "UPDATE orders SET status=? WHERE order_id = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, newStatus);
            ps.setInt(2, orderId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error while modifying order status", e);
            throw new DAOException("Error while modifying order status", e);
        } finally {
            close(ps);
            ConnectionPool.getInstance().returnConnection(conn);
        }
    }

    @Override
    public void changeAddress(int orderId, String newAddress) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "UPDATE orders SET address=? WHERE order_id = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, newAddress);
            ps.setInt(2, orderId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error while modifying order address", e);
            throw new DAOException("Error while modifying order address", e);
        } finally {
            close(ps);
            ConnectionPool.getInstance().returnConnection(conn);
        }
    }

    @Override
    public boolean validateCartByStock(int userId) throws DAOException {
        CartDAO cartDAO = DAOProvider.getInstance().getCartDAOImpl();
        ProductDAO productDAO = DAOProvider.getInstance().getProductDAOImpl();

        boolean fullyValid = true;
        List<CartedProduct> productsFromCart;
        Product productFromDB;
        productsFromCart = cartDAO.retrieveProductsForUserById(userId);
        for (CartedProduct product : productsFromCart) {
            productFromDB = productDAO.retrieveProductById(product.getProduct().getProductId());
            if (productFromDB.getStock() < product.getQuantity()) {
                fullyValid = false;
                cartDAO.removeProductFromUserById(userId, productFromDB.getProductId());
            }
        }

        return fullyValid;
    }
}

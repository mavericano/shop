package by.epamtc.ivangavrilovich.shop.DAO.impl;

import by.epamtc.ivangavrilovich.shop.DAO.ConnectionPool;
import by.epamtc.ivangavrilovich.shop.DAO.DAOProvider;
import by.epamtc.ivangavrilovich.shop.DAO.exceptions.DAOException;
import by.epamtc.ivangavrilovich.shop.DAO.interfaces.CartDAO;
import by.epamtc.ivangavrilovich.shop.DAO.interfaces.ProductDAO;
import by.epamtc.ivangavrilovich.shop.bean.CartedProduct;
import by.epamtc.ivangavrilovich.shop.bean.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLCartDAO implements CartDAO {
    private final static Logger logger = LogManager.getLogger();

    @Override
    public void addProductToUserById(int userId, int productId) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "INSERT INTO cart VALUES (?,?,?)";
        PreparedStatement ps;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ps.setInt(3, 1);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            logger.error(String.format("Error while adding pair to cart with userId %d and productId %d", userId, productId), e);
            throw new DAOException(String.format("Error while adding pair to cart with userId %d and productId %d", userId, productId), e);
        } finally {
            ConnectionPool.getInstance().returnConnection(conn);
        }
    }

    @Override
    public void removeProductFromUserById(int userId, int productId) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "DELETE FROM cart WHERE user_id=? and product_id=?";
        PreparedStatement ps;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            logger.error(String.format("Error while deleting pair from cart with userId %d and productId %d", userId, productId), e);
            throw new DAOException(String.format("Error while deleting pair from cart with userId %d and productId %d", userId, productId), e);
        } finally {
            ConnectionPool.getInstance().returnConnection(conn);
        }
    }

    @Override
    public double countTotalPrice(int userId) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "SELECT SUM(products.price*cart.quantity) FROM cart JOIN products ON products.product_id = cart.product_id WHERE user_id = ?";
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            rs.next();
            //SQL NULL returns 0
            return rs.getDouble(1);
        } catch (SQLException e) {
            logger.error(String.format("Error while counting total sum for userId %d", userId), e);
            throw new DAOException(String.format("Error while counting total sum for userId %d", userId), e);
        }
    }

    //TODO fix later
    @Override
    public int numberOfProducts(int userId) throws DAOException {
        return retrieveProductsForUserById(userId).size();
    }

    @Override
    public List<CartedProduct> viewPageProducts(int userId, int offset, int recsPerPage) throws DAOException {
        ProductDAO productDAO = DAOProvider.getInstance().getProductDAOImpl();
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "SELECT products.product_id, quantity FROM cart JOIN users ON cart.user_id = users.user_id JOIN products ON cart.product_id = products.product_id WHERE users.user_id = ? LIMIT ? OFFSET ?";
        PreparedStatement ps;
        ResultSet rs;
        List<CartedProduct> result = new ArrayList<>();
        int currentId;
        Product currentProduct;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, recsPerPage);
            ps.setInt(3, offset);
            rs = ps.executeQuery();
            while (rs.next()) {
                currentId = rs.getInt("product_id");
                currentProduct = productDAO.retrieveProductById(currentId);
                result.add(new CartedProduct(currentProduct, rs.getInt("quantity")));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            logger.error(String.format("Error while reading page products for offset %d recsPerPage %d", offset, recsPerPage), e);
            throw new DAOException(String.format("Error while reading page products for offset %d recsPerPage %d", offset, recsPerPage), e);
        } finally {
            ConnectionPool.getInstance().returnConnection(conn);
        }

        return result;
    }

    @Override
    public List<CartedProduct> retrieveProductsForUserById(int userId) throws DAOException {
        ProductDAO productDAO = DAOProvider.getInstance().getProductDAOImpl();
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "SELECT products.product_id, quantity FROM cart JOIN users ON cart.user_id = users.user_id JOIN products ON cart.product_id = products.product_id WHERE users.user_id = ?";
        PreparedStatement ps;
        ResultSet rs;
        List<CartedProduct> result = new ArrayList<>();
        int currentId;
        Product currentProduct;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                currentId = rs.getInt("product_id");
                currentProduct = productDAO.retrieveProductById(currentId);
                result.add(new CartedProduct(currentProduct, rs.getInt("quantity")));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            logger.error(String.format("Error while retrieving all products from cart with userId %d", userId), e);
            throw new DAOException(String.format("Error while retrieving all products from cart with userId %d", userId), e);
        } finally {
            ConnectionPool.getInstance().returnConnection(conn);
        }
        return result;
    }

    @Override
    public void increaseQuantity(int userId, int productId) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "UPDATE cart SET quantity = quantity + 1 WHERE user_id=? and product_id=?";
        PreparedStatement ps;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            logger.error(String.format("Error while increasing quantity for userId %d and productId %d", userId, productId), e);
            throw new DAOException(String.format("Error while increasing quantity for userId %d and productId %d", userId, productId), e);
        } finally {
            ConnectionPool.getInstance().returnConnection(conn);
        }
    }

    @Override
    public void decreaseQuantity(int userId, int productId) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "UPDATE cart SET quantity = quantity - 1 WHERE user_id=? and product_id=?";
        PreparedStatement ps;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            logger.error(String.format("Error while decreasing quantity for userId %d and productId %d", userId, productId), e);
            throw new DAOException(String.format("Error while decreasing quantity for userId %d and productId %d", userId, productId), e);
        } finally {
            ConnectionPool.getInstance().returnConnection(conn);
        }
    }

    @Override
    public boolean checkPresence(int userId, int productId) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "SELECT * FROM cart WHERE user_id=? and product_id=?";
        PreparedStatement ps;
        ResultSet rs;
        boolean result;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            rs = ps.executeQuery();
            result = rs.next();
            rs.close();
            ps.close();
        } catch (SQLException e) {
            logger.error(String.format("Error while checking presence for userId %d and productId %d", userId, productId), e);
            throw new DAOException(String.format("Error while checking presence for userId %d and productId %d", userId, productId), e);
        } finally {
            ConnectionPool.getInstance().returnConnection(conn);
        }

        return result;
    }
}

package by.epamtc.ivangavrilovich.shop.DAO.impl;

import by.epamtc.ivangavrilovich.shop.DAO.ConnectionPool;
import by.epamtc.ivangavrilovich.shop.DAO.DAOException;
import by.epamtc.ivangavrilovich.shop.DAO.interfaces.ProductDAO;
import by.epamtc.ivangavrilovich.shop.bean.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

//TODO add log
public class MySQLProductDAO implements ProductDAO {

    public static final String DEL = "del";
    public static final String PRODUCT_ID_COLUMN_NAME = "product_id";
    public static final String THUMBNAIL_COLUMN_NAME = "thumbnail";
    public static final String NAME_COLUMN_NAME = "name";
    public static final String PRICE_COLUMN_NAME = "price";
    public static final String STOCK_COLUMN_NAME = "stock";
    public static final String TYPE_COLUMN_NAME = "type";
    public static final String TYPE_NAME_COLUMN_NAME = "type name";

    @Override
    public int numberOfProducts() throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "SELECT count(*) FROM products JOIN types ON products.type = types.type_id WHERE del=0";
        int numberOfProducts;
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            if (rs.next()) {
                numberOfProducts = rs.getInt(1);
            } else {
                throw new DAOException("Result set for number of products is empty");
            }
        } catch (SQLException e) {
            throw new DAOException("Error while reading number of products", e);
        } finally {
            ConnectionPool.getInstance().returnConnection(conn);
        }

        return numberOfProducts;
    }

    @Override
    public List<Product> viewPageProducts(int offset, int recsPerPage) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "SELECT * FROM products JOIN types ON products.type = types.type_id WHERE del=0 ORDER BY product_id LIMIT ? OFFSET ?";
        PreparedStatement ps;
        ResultSet rs;
        int id;
        String thumbnail;
        String name;
        double price;
        int stock;
        int type;
        String typeName;
        List<Product> products = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, recsPerPage);
            ps.setInt(2, offset);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(PRODUCT_ID_COLUMN_NAME);
                thumbnail = rs.getString(THUMBNAIL_COLUMN_NAME);
                name = rs.getString(NAME_COLUMN_NAME);
                price = rs.getFloat(PRICE_COLUMN_NAME);
                stock = rs.getInt(STOCK_COLUMN_NAME);
                type = rs.getInt(TYPE_COLUMN_NAME);
                typeName = rs.getString(TYPE_NAME_COLUMN_NAME);
                products.add(new Product(id, thumbnail, name, price, stock, type, typeName));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new DAOException(String.format("Error while reading page products for offset %d recsPerPage %d", offset, recsPerPage), e);
        } finally {
            ConnectionPool.getInstance().returnConnection(conn);
        }

        return products;
    }

    @Override
    public void addProduct(Product product) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "INSERT INTO products(thumbnail,name,price,stock,type) VALUES(?,?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, product.getThumbnail());
            ps.setString(2, product.getName());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getStock());
            ps.setInt(5, product.getType());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new DAOException("Error while adding product", e);
        } finally {
            ConnectionPool.getInstance().returnConnection(conn);
        }
    }

    //TODO fix del
    @Override
    public List<Product> readProducts() throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "SELECT * FROM products JOIN types ON products.type = types.type_id";
        Statement st;
        ResultSet rs;
        List<Product> products = new ArrayList<>();
        int id;
        String thumbnail;
        String name;
        double price;
        int stock;
        int type;
        String typeName;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if (!rs.getBoolean(DEL)) {
                    id = rs.getInt(PRODUCT_ID_COLUMN_NAME);
                    thumbnail = rs.getString(THUMBNAIL_COLUMN_NAME);
                    name = rs.getString(NAME_COLUMN_NAME);
                    price = rs.getFloat(PRICE_COLUMN_NAME);
                    stock = rs.getInt(STOCK_COLUMN_NAME);
                    type = rs.getInt(TYPE_COLUMN_NAME);
                    typeName = rs.getString(TYPE_NAME_COLUMN_NAME);
                    products.add(new Product(id, thumbnail, name, price, stock, type, typeName));
                }
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw new DAOException("Error while reading all products", e);
        } finally {
            ConnectionPool.getInstance().returnConnection(conn);
        }
        return products;
    }

    @Override
    public boolean modifyDelStatus(Product product, boolean newStatus) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "UPDATE products SET del=? WHERE product_id = ?";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, newStatus);
            ps.setInt(2, product.getProductId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new DAOException("Error while modifying product del status", e);
        } finally {
            ConnectionPool.getInstance().returnConnection(conn);
        }
        return false;
    }

    private String buildSetExpr(Product product) {
        StringJoiner sj = new StringJoiner(", ");
        sj.add(THUMBNAIL_COLUMN_NAME + "=" + product.getThumbnail());
        sj.add(NAME_COLUMN_NAME + "=" + product.getName());
        sj.add(PRICE_COLUMN_NAME + "=" + product.getPrice());
        sj.add(STOCK_COLUMN_NAME + "=" + product.getStock());
        sj.add(TYPE_COLUMN_NAME + "=" + product.getType());

        return sj.setEmptyValue("").toString();
    }

    @Override
    public boolean updateProduct(Product product) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String setExpr = buildSetExpr(product);
        String sql = "UPDATE products SET " +
                setExpr +
                " WHERE product_id = ?";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, product.getProductId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new DAOException("Error while updating product", e);
        } finally {
            ConnectionPool.getInstance().returnConnection(conn);
        }
        return false;
    }

    @Override
    public List<Product> findPopularProducts(int amount) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "SELECT * FROM products JOIN types ON products.type = types.type_id ORDER BY `times ordered` DESC";
        Statement st;
        ResultSet rs;
        List<Product> products = new ArrayList<>();
        int id;
        String thumbnail;
        String name;
        double price;
        int stock;
        int type;
        String typeName;
        int i = 0;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next() && i <= amount) {
                if (!rs.getBoolean(DEL)) {
                    id = rs.getInt(PRODUCT_ID_COLUMN_NAME);
                    thumbnail = rs.getString(THUMBNAIL_COLUMN_NAME);
                    name = rs.getString(NAME_COLUMN_NAME);
                    price = rs.getFloat(PRICE_COLUMN_NAME);
                    stock = rs.getInt(STOCK_COLUMN_NAME);
                    type = rs.getInt(TYPE_COLUMN_NAME);
                    typeName = rs.getString(TYPE_NAME_COLUMN_NAME);
                    products.add(new Product(id, thumbnail, name, price, stock, type, typeName));
                    i++;
                }
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw new DAOException(String.format("Error while reading %d popular products", amount), e);
        } finally {
            ConnectionPool.getInstance().returnConnection(conn);
        }
        return products;
    }
}

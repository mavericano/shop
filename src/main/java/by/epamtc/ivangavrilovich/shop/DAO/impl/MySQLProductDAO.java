package by.epamtc.ivangavrilovich.shop.DAO.impl;

import by.epamtc.ivangavrilovich.shop.DAO.ConnectionProvider;
import by.epamtc.ivangavrilovich.shop.DAO.DAOException;
import by.epamtc.ivangavrilovich.shop.DAO.interfaces.ProductDAO;
import by.epamtc.ivangavrilovich.shop.bean.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class MySQLProductDAO implements ProductDAO {
    @Override
    public void addProduct(Product product) throws DAOException {
        Connection conn = ConnectionProvider.getInstance().takeConnection();
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
            ConnectionProvider.getInstance().returnConnection(conn);
        }
    }

    @Override
    public List<Product> readProducts() throws DAOException {
        Connection conn = ConnectionProvider.getInstance().takeConnection();
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
                if (!rs.getBoolean("del")) {
                    id = rs.getInt("product_id");
                    thumbnail = rs.getString("thumbnail");
                    name = rs.getString("name");
                    price = rs.getFloat("price");
                    stock = rs.getInt("stock");
                    type = rs.getInt("type");
                    typeName = rs.getString("type name");
                    products.add(new Product(id, thumbnail, name, price, stock, type, typeName));
                }
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw new DAOException("Error while reading all products", e);
        } finally {
            ConnectionProvider.getInstance().returnConnection(conn);
        }
        return products;
    }

    @Override
    public boolean modifyDelStatus(Product product, boolean newStatus) throws DAOException {
        Connection conn = ConnectionProvider.getInstance().takeConnection();
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
            ConnectionProvider.getInstance().returnConnection(conn);
        }
        return false;
    }

    private String buildSetExpr(Product product) {
        StringJoiner sj = new StringJoiner(", ");
        sj.add("thumbnail=" + product.getThumbnail());
        sj.add("name=" + product.getName());
        sj.add("price=" + product.getPrice());
        sj.add("stock=" + product.getStock());
        sj.add("type=" + product.getType());

        return sj.setEmptyValue("").toString();
    }

    @Override
    public boolean updateProduct(Product product) throws DAOException {
        Connection conn = ConnectionProvider.getInstance().takeConnection();
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
            ConnectionProvider.getInstance().returnConnection(conn);
        }
        return false;
    }

    @Override
    public List<Product> findPopularProducts(int amount) throws DAOException {
        Connection conn = ConnectionProvider.getInstance().takeConnection();
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
                if (!rs.getBoolean("del")) {
                    id = rs.getInt("product_id");
                    thumbnail = rs.getString("thumbnail");
                    name = rs.getString("name");
                    price = rs.getFloat("price");
                    stock = rs.getInt("stock");
                    type = rs.getInt("type");
                    typeName = rs.getString("type name");
                    products.add(new Product(id, thumbnail, name, price, stock, type, typeName));
                    i++;
                }
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw new DAOException(String.format("Error while reading %d popular products", amount), e);
        } finally {
            ConnectionProvider.getInstance().returnConnection(conn);
        }
        return products;
    }
}

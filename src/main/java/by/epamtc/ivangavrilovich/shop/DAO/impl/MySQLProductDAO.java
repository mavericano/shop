package by.epamtc.ivangavrilovich.shop.DAO.impl;

import by.epamtc.ivangavrilovich.shop.DAO.ConnectionProvider;
import by.epamtc.ivangavrilovich.shop.DAO.DAOException;
import by.epamtc.ivangavrilovich.shop.DAO.interfaces.ProductDAO;
import by.epamtc.ivangavrilovich.shop.bean.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLProductDAO implements ProductDAO {
    @Override
    public void addProduct(Product product) throws DAOException {
        Connection conn = ConnectionProvider.getInstance().takeConnection();
        String sql = "INSERT INTO products(thumbnail,name,price,stock,type) VALUES(?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, product.getThumbnail());
            ps.setString(2, product.getName());
            ps.setFloat(3, product.getPrice());
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
}

package by.epamtc.ivangavrilovich.shop.DAO.impl;

import by.epamtc.ivangavrilovich.shop.DAO.ConnectionProvider;
import by.epamtc.ivangavrilovich.shop.DAO.DAOException;
import by.epamtc.ivangavrilovich.shop.DAO.interfaces.UserDAO;
import by.epamtc.ivangavrilovich.shop.bean.User;

import java.sql.*;
import java.util.List;

public class MySQLUserDAO implements UserDAO {

    @Override
    public void addUser(User user) throws DAOException {
        Connection conn = ConnectionProvider.getInstance().takeConnection();
        String sql = "INSERT INTO users(email,password,`default address`,role,banned) VALUES(?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getDefaultAddress());
            ps.setInt(4, user.getRole());
            ps.setBoolean(5, user.isBanned());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            //TODO fix
            e.printStackTrace();
        } finally {
            ConnectionProvider.getInstance().returnConnection(conn);
        }
    }

    @Override
    public boolean removeUser(User user) throws DAOException {
        return false;
    }

    @Override
    public boolean updateUser(User user) throws DAOException {
        return false;
    }

    @Override
    public List<User> readUsers() throws DAOException {
        Connection conn = ConnectionProvider.getInstance().takeConnection();
        String sql = "SELECT * FROM users";
        //Statement st = conn.createStatement();
        //st.executeQuery(sql);
        ConnectionProvider.getInstance().returnConnection(conn);
        return null;
    }

    @Override
    public User readUserByEmail(String email) throws DAOException {
        Connection conn = ConnectionProvider.getInstance().takeConnection();
        String sql = String.format("SELECT * FROM users WHERE email='%s'", email);
        Statement st;
        ResultSet rs = null;
        int id = 0;
        String password = null;
        String defaultAddress = null;
        int role = 0;
        boolean banned = false;
        boolean wasFound = false;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                wasFound = true;
                id = rs.getInt("user_id");
                password = rs.getString("password");
                defaultAddress = rs.getString("default address");
                role = rs.getInt("role");
                banned = rs.getBoolean("banned");
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            //TODO fix
            e.printStackTrace();
        } finally {
            ConnectionProvider.getInstance().returnConnection(conn);
        }

        return wasFound ? new User(id, email, password, defaultAddress, role, banned) : null;
    }

    @Override
    public boolean hasUserWithEmail(String email) throws DAOException {
        Connection conn = ConnectionProvider.getInstance().takeConnection();
        String sql = String.format("SELECT email FROM users WHERE email='%s'", email);
        Statement st;
        ResultSet rs;
        boolean result;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            result = rs.next();
            rs.close();
            st.close();
        } catch (SQLException e) {
            //TODO fix
            e.printStackTrace();
            throw new DAOException("", e);
        } finally {
            ConnectionProvider.getInstance().returnConnection(conn);
        }
        return result;
    }
}

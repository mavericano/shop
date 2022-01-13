package by.epamtc.ivangavrilovich.shop.DAO.impl;

import by.epamtc.ivangavrilovich.shop.DAO.ConnectionProvider;
import by.epamtc.ivangavrilovich.shop.DAO.DAOException;
import by.epamtc.ivangavrilovich.shop.DAO.interfaces.UserDAO;
import by.epamtc.ivangavrilovich.shop.bean.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

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
            throw new DAOException("Error while adding user", e);
        } finally {
            ConnectionProvider.getInstance().returnConnection(conn);
        }
    }

    @Override
    public boolean modifyDelStatus(User user, boolean newStatus) throws DAOException {
        Connection conn = ConnectionProvider.getInstance().takeConnection();
        String sql = "UPDATE users SET del=? WHERE user_id = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, newStatus);
            ps.setInt(2, user.getUserId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new DAOException("Error while modifying user del status", e);
        } finally {
            ConnectionProvider.getInstance().returnConnection(conn);
        }
        return false;
    }

//    @Override
//    public boolean restoreUser(User user) throws DAOException {
//        return false;
//    }

    private String buildSetExpr(User user) {
        StringJoiner sj = new StringJoiner(", ");
        sj.add("password=" + user.getPassword());
        sj.add("`default address`=" + user.getDefaultAddress());
        sj.add("role=" + user.getRole());
        sj.add("banned=" + user.isBanned());

        return sj.setEmptyValue("").toString();
    }

//    private boolean toChangeBanState(int userId, boolean newState, Connection conn) {
//        String sql = "SELECT banned FROM users WHERE user_id=?";
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        boolean ret = false;
//        try {
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1, userId);
//            rs = ps.executeQuery();
//            ret = newState != rs.getBoolean("banned");
//            rs.close();
//            ps.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            //TODO fix
//        }
//        return ret;
//    }

    @Override
    public boolean updateUser(User user) throws DAOException {
        Connection conn = ConnectionProvider.getInstance().takeConnection();
        String setExpr = buildSetExpr(user);
        String sql = "UPDATE users SET " +
                setExpr +
                " WHERE user_id = ?";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getUserId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new DAOException("Error while updating user", e);
        } finally {
            ConnectionProvider.getInstance().returnConnection(conn);
        }
        return false;
    }

    @Override
    public List<User> readUsers() throws DAOException {
        Connection conn = ConnectionProvider.getInstance().takeConnection();
        String sql = "SELECT * FROM users JOIN roles ON users.role = roles.role_id";
        Statement st;
        ResultSet rs;
        List<User> users = new ArrayList<>();
        int id;
        String email;
        String password;
        String defaultAddress;
        String roleName;
        int role;
        boolean banned;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if (!rs.getBoolean("del")) {
                    id = rs.getInt("user_id");
                    email = rs.getString("email");
                    password = rs.getString("password");
                    defaultAddress = rs.getString("default address");
                    roleName = rs.getString("name");
                    role = rs.getInt("role");
                    banned = rs.getBoolean("banned");
                    users.add(new User(id, email, password, defaultAddress, role, roleName, banned));
                }
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw new DAOException("Error while reading all users", e);
        } finally {
            ConnectionProvider.getInstance().returnConnection(conn);
        }
        return users;
    }

    @Override
    public User readUserByEmail(String email) throws DAOException {
        Connection conn = ConnectionProvider.getInstance().takeConnection();
        String sql = String.format("SELECT * FROM users JOIN roles ON users.role = roles.role_id WHERE email='%s'", email);
        Statement st;
        ResultSet rs = null;
        int id = 0;
        String password = null;
        String defaultAddress = null;
        String roleName = null;
        int role = 0;
        boolean banned = false;
        boolean del = false;
        boolean wasFound = false;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                del = rs.getBoolean("del");
                if (!del) {
                    wasFound = true;
                    id = rs.getInt("user_id");
                    password = rs.getString("password");
                    defaultAddress = rs.getString("default address");
                    roleName = rs.getString("name");
                    role = rs.getInt("role");
                    banned = rs.getBoolean("banned");
                }
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw new DAOException("Error while reading user by email", e);
        } finally {
            ConnectionProvider.getInstance().returnConnection(conn);
        }

        return wasFound ? new User(id, email, password, defaultAddress, role, roleName, banned) : null;
    }

    @Override
    public boolean hasUserWithEmail(String email) throws DAOException {
        Connection conn = ConnectionProvider.getInstance().takeConnection();
        String sql = String.format("SELECT email FROM users WHERE email='%s'", email);
        Statement st;
        ResultSet rs;
        boolean result = false;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next())
                if (!rs.getBoolean("del"))
                    result = true;
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw new DAOException("Error while fetching user", e);
        } finally {
            ConnectionProvider.getInstance().returnConnection(conn);
        }

        return result;
    }
}

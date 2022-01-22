package by.epamtc.ivangavrilovich.shop.DAO.impl;

import by.epamtc.ivangavrilovich.shop.DAO.ConnectionPool;
import by.epamtc.ivangavrilovich.shop.DAO.DAOException;
import by.epamtc.ivangavrilovich.shop.DAO.interfaces.UserDAO;
import by.epamtc.ivangavrilovich.shop.bean.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class MySQLUserDAO implements UserDAO {

    public static final String PASSWORD_COLUMN_NAME = "password";
    public static final String NUMBER_COLUMN_NAME = "number";
    public static final String ROLE_COLUMN_NAME = "role";
    public static final String ROLE = "role";
    public static final String BANNED_COLUMN_NAME = "banned";
    public static final String USER_ID_COLUMN_NAME = "user_id";
    public static final String EMAIL_COLUMN_NAME = "email";
    public static final String ROLE_NAME_COLUMN_NAME = "name";
    public static final String DEL = "del";

    @Override
    public void addUser(User user) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "INSERT INTO users(email,password,number,role,banned) VALUES(?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getNumber());
            ps.setInt(4, user.getRole());
            ps.setBoolean(5, user.isBanned());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new DAOException("Error while adding user", e);
        } finally {
            ConnectionPool.getInstance().returnConnection(conn);
        }
    }

    @Override
    public boolean modifyDelStatus(User user, boolean newStatus) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
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
            ConnectionPool.getInstance().returnConnection(conn);
        }
        return false;
    }

//    @Override
//    public boolean restoreUser(User user) throws DAOException {
//        return false;
//    }

    private String buildSetExpr(User user) {
        StringJoiner sj = new StringJoiner(", ");
        sj.add(PASSWORD_COLUMN_NAME + "=" + user.getPassword());
        sj.add(NUMBER_COLUMN_NAME + "=" + user.getNumber());
        sj.add(ROLE_COLUMN_NAME + "=" + user.getRole());
        sj.add(BANNED_COLUMN_NAME + "=" + user.isBanned());

        return sj.setEmptyValue("").toString();
    }

    @Override
    public boolean updateUser(User user) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
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
            ConnectionPool.getInstance().returnConnection(conn);
        }
        return false;
    }

    @Override
    public List<User> readUsers() throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "SELECT * FROM users JOIN roles ON users.role = roles.role_id";
        Statement st;
        ResultSet rs;
        List<User> users = new ArrayList<>();
        int id;
        String email;
        String password;
        String number;
        String roleName;
        int role;
        boolean banned;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if (!rs.getBoolean(DEL)) {
                    id = rs.getInt(USER_ID_COLUMN_NAME);
                    email = rs.getString(EMAIL_COLUMN_NAME);
                    password = rs.getString(PASSWORD_COLUMN_NAME);
                    number = rs.getString(NUMBER_COLUMN_NAME);
                    roleName = rs.getString(ROLE_NAME_COLUMN_NAME);
                    role = rs.getInt(ROLE_COLUMN_NAME);
                    banned = rs.getBoolean(BANNED_COLUMN_NAME);
                    users.add(new User(id, email, password, number, role, roleName, banned));
                }
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw new DAOException("Error while reading all users", e);
        } finally {
            ConnectionPool.getInstance().returnConnection(conn);
        }
        return users;
    }

    @Override
    public User readUserByEmail(String email) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = String.format("SELECT * FROM users JOIN roles ON users.role = roles.role_id WHERE email='%s'", email);
        Statement st;
        ResultSet rs = null;
        int id = 0;
        String password = null;
        String number = null;
        String roleName = null;
        int role = 0;
        boolean banned = false;
        boolean del = false;
        boolean wasFound = false;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                del = rs.getBoolean(DEL);
                if (!del) {
                    wasFound = true;
                    id = rs.getInt(USER_ID_COLUMN_NAME);
                    password = rs.getString(PASSWORD_COLUMN_NAME);
                    number = rs.getString(NUMBER_COLUMN_NAME);
                    roleName = rs.getString(ROLE_NAME_COLUMN_NAME);
                    role = rs.getInt(ROLE);
                    banned = rs.getBoolean(BANNED_COLUMN_NAME);
                }
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw new DAOException("Error while reading user by email", e);
        } finally {
            ConnectionPool.getInstance().returnConnection(conn);
        }

        return wasFound ? new User(id, email, password, number, role, roleName, banned) : null;
    }

    @Override
    public boolean hasUserWithEmail(String email) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = String.format("SELECT email FROM users WHERE email='%s'", email);
        Statement st;
        ResultSet rs;
        boolean result = false;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next())
                if (!rs.getBoolean(DEL))
                    result = true;
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw new DAOException("Error while fetching user", e);
        } finally {
            ConnectionPool.getInstance().returnConnection(conn);
        }

        return result;
    }
}

package by.epamtc.ivangavrilovich.shop.DAO.impl;

import by.epamtc.ivangavrilovich.shop.DAO.ConnectionPool;
import by.epamtc.ivangavrilovich.shop.DAO.exceptions.DAOException;
import by.epamtc.ivangavrilovich.shop.DAO.interfaces.UserDAO;
import by.epamtc.ivangavrilovich.shop.bean.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

//TODO optional maybe
public class MySQLUserDAO implements UserDAO {
    private final static Logger logger = LogManager.getLogger();

    public static final String PASSWORD_COLUMN_NAME = "password";
    public static final String NUMBER_COLUMN_NAME = "number";
    public static final String ROLE_COLUMN_NAME = "role";
    public static final String ROLE = "role";
    public static final String BANNED_COLUMN_NAME = "banned";
    public static final String USER_ID_COLUMN_NAME = "user_id";
    public static final String EMAIL_COLUMN_NAME = "email";
    public static final String ROLE_NAME_COLUMN_NAME = "name";
//    public static final String DEL = "del";

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
    public List<User> viewPageUsers(int offset, int recsPerPage) throws DAOException {
        return viewPageUsers(offset, recsPerPage, false);
    }

    @Override
    public List<User> viewPageUsers(int offset, int recsPerPage, boolean viewDel) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "SELECT * FROM users JOIN roles ON users.role = roles.role_id ";
        if (!viewDel) {
            sql += "WHERE del=0";
        }
        sql += "ORDER BY user_id LIMIT ? OFFSET ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id;
        String email;
        String password;
        String number;
        String roleName;
        int role;
        boolean banned;
        List<User> users = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, recsPerPage);
            ps.setInt(2, offset);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(USER_ID_COLUMN_NAME);
                email = rs.getString(EMAIL_COLUMN_NAME);
                password = rs.getString(PASSWORD_COLUMN_NAME);
                number = rs.getString(NUMBER_COLUMN_NAME);
                roleName = rs.getString(ROLE_NAME_COLUMN_NAME);
                role = rs.getInt(ROLE_COLUMN_NAME);
                banned = rs.getBoolean(BANNED_COLUMN_NAME);
                users.add(new User(id, email, password, number, role, roleName, banned));
            }
        } catch (SQLException e) {
            logger.error(String.format("Error while reading page users for offset %d recsPerPage %d", offset, recsPerPage), e);
            throw new DAOException(String.format("Error while reading page users for offset %d recsPerPage %d", offset, recsPerPage), e);
        } finally {
            close(rs, ps);
            ConnectionPool.getInstance().returnConnection(conn);
        }

        return users;
    }

    @Override
    public int numberOfUsers() throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "SELECT count(*) FROM users WHERE del=0";
        int numberOfUsers;
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            if (rs.next()) {
                numberOfUsers = rs.getInt(1);
            } else {
                logger.error("Result set for number of products is empty");
                throw new DAOException("Result set for number of products is empty");
            }
        } catch (SQLException e) {
            logger.error("Error while reading number of products", e);
            throw new DAOException("Error while reading number of products", e);
        } finally {
            close(rs, st);
            ConnectionPool.getInstance().returnConnection(conn);
        }

        return numberOfUsers;
    }

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
        } catch (SQLException e) {
            logger.error("Error while adding user", e);
            throw new DAOException("Error while adding user", e);
        } finally {
            close(ps);
            ConnectionPool.getInstance().returnConnection(conn);
        }
    }

    @Override
    public void changeRole(int userId, int newRole) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "UPDATE users SET role=? WHERE user_id = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, newRole);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error while modifying user role", e);
            throw new DAOException("Error while modifying user role", e);
        } finally {
            close(ps);
            ConnectionPool.getInstance().returnConnection(conn);
        }
    }

    @Override
    public void changeBannedStatus(int userId, boolean newStatus) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "UPDATE users SET banned=? WHERE user_id = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, newStatus);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error while modifying user banned status", e);
            throw new DAOException("Error while modifying user banned status", e);
        } finally {
            close(ps);
            ConnectionPool.getInstance().returnConnection(conn);
        }
    }

    @Override
    public void changeDelStatus(int userId, boolean newStatus) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "UPDATE users SET del=? WHERE user_id = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, newStatus);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error while modifying user del status", e);
            throw new DAOException("Error while modifying user del status", e);
        } finally {
            close(ps);
            ConnectionPool.getInstance().returnConnection(conn);
        }
    }

    private String buildSetExpr(User user) {
        StringJoiner sj = new StringJoiner(", ");
        sj.add(PASSWORD_COLUMN_NAME + "=" + user.getPassword());
        sj.add(NUMBER_COLUMN_NAME + "=" + user.getNumber());
        sj.add(ROLE_COLUMN_NAME + "=" + user.getRole());
        sj.add(BANNED_COLUMN_NAME + "=" + user.isBanned());

        return sj.setEmptyValue("").toString();
    }

    @Override
    public void updateUser(User user) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String setExpr = buildSetExpr(user);
        String sql = "UPDATE users SET " +
                setExpr +
                " WHERE user_id = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error while updating user", e);
            throw new DAOException("Error while updating user", e);
        } finally {
            close(ps);
            ConnectionPool.getInstance().returnConnection(conn);
        }
    }

    @Override
    public List<User> readUsers() throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "SELECT * FROM users JOIN roles ON users.role = roles.role_id WHERE del=0";
        Statement st = null;
        ResultSet rs = null;
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
                id = rs.getInt(USER_ID_COLUMN_NAME);
                email = rs.getString(EMAIL_COLUMN_NAME);
                password = rs.getString(PASSWORD_COLUMN_NAME);
                number = rs.getString(NUMBER_COLUMN_NAME);
                roleName = rs.getString(ROLE_NAME_COLUMN_NAME);
                role = rs.getInt(ROLE_COLUMN_NAME);
                banned = rs.getBoolean(BANNED_COLUMN_NAME);
                users.add(new User(id, email, password, number, role, roleName, banned));
            }
        } catch (SQLException e) {
            logger.error("Error while reading all users", e);
            throw new DAOException("Error while reading all users", e);
        } finally {
            close(rs, st);
            ConnectionPool.getInstance().returnConnection(conn);
        }
        return users;
    }

    @Override
    public User readUserByEmail(String email) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "SELECT * FROM users JOIN roles ON users.role = roles.role_id WHERE email=? and del=0";
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;
        String password = null;
        String number = null;
        String roleName = null;
        int role = 0;
        boolean banned = false;
        boolean wasFound = false;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                wasFound = true;
                id = rs.getInt(USER_ID_COLUMN_NAME);
                password = rs.getString(PASSWORD_COLUMN_NAME);
                number = rs.getString(NUMBER_COLUMN_NAME);
                roleName = rs.getString(ROLE_NAME_COLUMN_NAME);
                role = rs.getInt(ROLE);
                banned = rs.getBoolean(BANNED_COLUMN_NAME);
            }
        } catch (SQLException e) {
            logger.error(String.format("Error while reading user by email %s", email), e);
            throw new DAOException(String.format("Error while reading user by email %s", email), e);
        } finally {
            close(rs, ps);
            ConnectionPool.getInstance().returnConnection(conn);
        }

        return wasFound ? new User(id, email, password, number, role, roleName, banned) : null;
    }

    @Override
    public User readUserById(int userId) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "SELECT * FROM users JOIN roles ON users.role = roles.role_id WHERE user_id=? and del=0";
        PreparedStatement ps = null;
        ResultSet rs = null;
        String email = null;
        String password = null;
        String number = null;
        String roleName = null;
        int role = 0;
        boolean banned = false;
        boolean wasFound = false;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                wasFound = true;
                email = rs.getString(EMAIL_COLUMN_NAME);
                password = rs.getString(PASSWORD_COLUMN_NAME);
                number = rs.getString(NUMBER_COLUMN_NAME);
                roleName = rs.getString(ROLE_NAME_COLUMN_NAME);
                role = rs.getInt(ROLE);
                banned = rs.getBoolean(BANNED_COLUMN_NAME);
            }
        } catch (SQLException e) {
            logger.error(String.format("Error while reading user by id %d", userId), e);
            throw new DAOException(String.format("Error while reading user by email %d", userId), e);
        } finally {
            close(rs, ps);
            ConnectionPool.getInstance().returnConnection(conn);
        }

        return wasFound ? new User(userId, email, password, number, role, roleName, banned) : null;
    }

    @Override
    public boolean hasUserWithEmail(String email) throws DAOException {
        Connection conn = ConnectionPool.getInstance().takeConnection();
        String sql = "SELECT email FROM users WHERE email=? and del=0";
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next())
                result = true;
            rs.close();
            ps.close();
        } catch (SQLException e) {
            logger.error(String.format("Error while fetching user by email %s", email), e);
            throw new DAOException(String.format("Error while fetching user by email %s", email), e);
        } finally {
            close(rs, ps);
            ConnectionPool.getInstance().returnConnection(conn);
        }

        return result;
    }
}

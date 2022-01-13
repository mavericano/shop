package by.epamtc.ivangavrilovich.shop.DAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionProvider {
    private static final ConnectionProvider instance = new ConnectionProvider();
    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;
    private BlockingQueue<Connection> givenConnections;
    private BlockingQueue<Connection> freeConnections;

    public static ConnectionProvider getInstance() {
        return instance;
    }

    private ConnectionProvider() {
        //ResourceBundle bundle = ResourceBundle.getBundle("db.properties", new Locale("en", "US"));
        InputStream a = this.getClass().getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        try {
            properties.load(a);
        } catch (IOException e) {
            throw new ConnectionPoolException("Error loading property files for database. Name for the file should be \"db.properties\"", e);
        }
        this.driverName = properties.getProperty("driverName");
        this.url = properties.getProperty("url");
        this.user = properties.getProperty("user");
        this.password = properties.getProperty("password");
        try {
            this.poolSize = Integer.parseInt(properties.getProperty("poolSize"));
        } catch (NumberFormatException e) {
            this.poolSize = 5;
        }

        try {
            Class.forName(driverName);
            givenConnections = new ArrayBlockingQueue<>(poolSize);
            freeConnections = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                //PooledConnection pooledConnection = new PooledConnection(connection);
                freeConnections.add(connection);
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException("SQLException in ConnectionPool", e);
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("Can't find database driver class", e);
        }
    }

    public void dispose() {
        clearConnectionQueue();
    }

    private void clearConnectionQueue() {
        try {
            closeConnectionsQueue(givenConnections);
            closeConnectionsQueue(freeConnections);
        } catch (SQLException e) {
//            logger.log(Level.ERROR, "Error closing the connection.", e);
        }
    }
    public Connection takeConnection() throws ConnectionPoolException {
        Connection connection;
        try {
            connection = freeConnections.take();
            givenConnections.add(connection);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Error connecting to the data source.", e);
        }
        return connection;
    }

    public void returnConnection(Connection connection) {
        givenConnections.remove(connection);
        freeConnections.add(connection);
    }

    public void closeConnection(Connection con, Statement st, ResultSet rs) {
        try {
            con.close();
        } catch (SQLException e) {
//            logger.log(Level.ERROR, "Connection isn't return to the pool.");
        }
        try {
            rs.close();
        } catch (SQLException e) {
//            logger.log(Level.ERROR, "ResultSet isn't closed.");
        }
        try {
            st.close();
        } catch (SQLException e) {
//            logger.log(Level.ERROR, "Statement isn't closed.");
        }
    }

    public void closeConnection(Connection con, Statement st) {
        try {
            con.close();
        } catch (SQLException e) {
//            logger.log(Level.ERROR, "Connection isn't return to the pool.");
        }
        try {
            st.close();
        } catch (SQLException e) {
//            logger.log(Level.ERROR, "Statement isn't closed.");
        }
    }
    private void closeConnectionsQueue(BlockingQueue<Connection> queue)
            throws SQLException {
        Connection connection;
        while ((connection = queue.poll()) != null) {
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
            connection.close();
        }
    }
}

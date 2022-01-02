package by.epamtc.ivangavrilovich.shop.DAO;

import by.epamtc.ivangavrilovich.shop.DAO.impl.MySQLUserDAO;
import by.epamtc.ivangavrilovich.shop.DAO.interfaces.UserDAO;

public class DAOProvider {
    private static DAOProvider instance = new DAOProvider();

    public UserDAO getUserDAOImpl() {
        return userDAOImpl;
    }

    public void setUserDAOImpl(UserDAO userDAOImpl) {
        this.userDAOImpl = userDAOImpl;
    }

    private UserDAO userDAOImpl;


    private DAOProvider() {
        userDAOImpl = new MySQLUserDAO();
    }

    public static DAOProvider getInstance() {
        return instance;
    }
}

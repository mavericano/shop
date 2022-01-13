package by.epamtc.ivangavrilovich.shop.DAO;

import by.epamtc.ivangavrilovich.shop.DAO.impl.MySQLProductDAO;
import by.epamtc.ivangavrilovich.shop.DAO.impl.MySQLUserDAO;
import by.epamtc.ivangavrilovich.shop.DAO.interfaces.ProductDAO;
import by.epamtc.ivangavrilovich.shop.DAO.interfaces.UserDAO;

public class DAOProvider {
    private final static DAOProvider INSTANCE = new DAOProvider();
    private UserDAO userDAOImpl;
    private ProductDAO productDAOImpl;

    private DAOProvider() {
        userDAOImpl = new MySQLUserDAO();
        productDAOImpl = new MySQLProductDAO();
    }

    public static DAOProvider getInstance() {
        return INSTANCE;
    }

    public UserDAO getUserDAOImpl() {
        return userDAOImpl;
    }

    public void setUserDAOImpl(UserDAO userDAOImpl) {
        this.userDAOImpl = userDAOImpl;
    }

    public ProductDAO getProductDAOImpl() {
        return productDAOImpl;
    }

    public void setProductDAOImpl(ProductDAO productDAOImpl) {
        this.productDAOImpl = productDAOImpl;
    }
}

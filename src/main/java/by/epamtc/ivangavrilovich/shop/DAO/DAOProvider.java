package by.epamtc.ivangavrilovich.shop.DAO;

import by.epamtc.ivangavrilovich.shop.DAO.impl.MySQLCartDAO;
import by.epamtc.ivangavrilovich.shop.DAO.impl.MySQLProductDAO;
import by.epamtc.ivangavrilovich.shop.DAO.impl.MySQLUserDAO;
import by.epamtc.ivangavrilovich.shop.DAO.interfaces.CartDAO;
import by.epamtc.ivangavrilovich.shop.DAO.interfaces.ProductDAO;
import by.epamtc.ivangavrilovich.shop.DAO.interfaces.UserDAO;

public class DAOProvider {
    private UserDAO userDAOImpl;
    private ProductDAO productDAOImpl;
    private CartDAO cartDAOImpl;

    private static class InstanceHolder {
        private final static DAOProvider INSTANCE = new DAOProvider();
    }

    private DAOProvider() {
        userDAOImpl = new MySQLUserDAO();
        productDAOImpl = new MySQLProductDAO();
        cartDAOImpl = new MySQLCartDAO();
    }

    public static DAOProvider getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public CartDAO getCartDAOImpl() {
        return cartDAOImpl;
    }

    public void setCartDAOImpl(CartDAO cartDAOImpl) {
        this.cartDAOImpl = cartDAOImpl;
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

package by.epamtc.ivangavrilovich.shop.service.impl;

import by.epamtc.ivangavrilovich.shop.DAO.DAOProvider;
import by.epamtc.ivangavrilovich.shop.DAO.exceptions.DAOException;
import by.epamtc.ivangavrilovich.shop.DAO.interfaces.CartDAO;
import by.epamtc.ivangavrilovich.shop.bean.CartedProduct;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;
import by.epamtc.ivangavrilovich.shop.service.interfaces.CartService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CartServiceImpl implements CartService {
    private final static Logger logger = LogManager.getLogger();

    @Override
    public void addItemToCart(int userId, int productId) throws ServiceException {
        CartDAO dao = DAOProvider.getInstance().getCartDAOImpl();

        try {
            dao.addProductToUserById(userId, productId);
        } catch (DAOException e) {
            logger.error(String.format("Error while adding item to cart with userId %d and productId %d", userId, productId), e);
            throw new ServiceException(String.format("Error while adding item to cart with userId %d and productId %d", userId, productId), e);
        }
    }

    @Override
    public void removeItemFromCart(int userId, int productId) throws ServiceException {
        CartDAO dao = DAOProvider.getInstance().getCartDAOImpl();

        try {
            dao.removeProductFromUserById(userId, productId);
        } catch (DAOException e) {
            logger.error(String.format("Error while removing item from cart with userId %d and productId %d", userId, productId), e);
            throw new ServiceException(String.format("Error while removing item from cart with userId %d and productId %d", userId, productId), e);
        }
    }

    @Override
    public List<CartedProduct> retrieveProductsInCart(int userId) throws ServiceException {
        CartDAO dao = DAOProvider.getInstance().getCartDAOImpl();
        List<CartedProduct> result;

        try{
            result = dao.retrieveProductsForUserById(userId);
        } catch (DAOException e) {
            logger.error(String.format("Error while retrieving all products from cart with userId %d", userId), e);
            throw new ServiceException(String.format("Error while retrieving all products from cart with userId %d", userId), e);
        }

        return result;
    }

    @Override
    public void increaseQuantity(int userId, int productId) throws ServiceException {
        CartDAO dao = DAOProvider.getInstance().getCartDAOImpl();

        try {
            dao.increaseQuantity(userId, productId);
        } catch (DAOException e) {
            logger.error(String.format("Error while increasing quantity for userId %d and productId %d", userId, productId), e);
            throw new ServiceException(String.format("Error while increasing quantity for userId %d and productId %d", userId, productId), e);
        }
    }

    @Override
    public void decreaseQuantity(int userId, int productId) throws ServiceException {
        CartDAO dao = DAOProvider.getInstance().getCartDAOImpl();

        try {
            dao.decreaseQuantity(userId, productId);
        } catch (DAOException e) {
            logger.error(String.format("Error while decreasing quantity for userId %d and productId %d", userId, productId), e);
            throw new ServiceException(String.format("Error while decreasing quantity for userId %d and productId %d", userId, productId), e);
        }
    }

    @Override
    public boolean checkPresence(int userId, int productId) throws ServiceException {
        CartDAO dao = DAOProvider.getInstance().getCartDAOImpl();

        try {
            return dao.checkPresence(userId, productId);
        } catch (DAOException e) {
            logger.error(String.format("Error while checking presence for userId %d and productId %d", userId, productId), e);
            throw new ServiceException(String.format("Error while checking presence for userId %d and productId %d", userId, productId), e);
        }
    }

    @Override
    public int numberOfProducts(int userId) throws ServiceException {
        CartDAO dao = DAOProvider.getInstance().getCartDAOImpl();

        try {
            return dao.numberOfProducts(userId);
        } catch (DAOException e) {
            logger.error(String.format("Error while counting products for userId %d", userId), e);
            throw new ServiceException(String.format("Error while counting products for userId %d", userId), e);
        }
    }

    @Override
    public double countTotalPrice(int userId) throws ServiceException {
        CartDAO dao = DAOProvider.getInstance().getCartDAOImpl();

        try {
            return dao.countTotalPrice(userId);
        } catch (DAOException e) {
            logger.error(String.format("Error while counting total sum for userId %d", userId), e);
            throw new ServiceException(String.format("Error while counting total sum for userId %d", userId), e);
        }
    }

    @Override
    public List<CartedProduct> viewPageProducts(int userId, int offset, int recsPerPage) throws ServiceException {
        CartDAO dao = DAOProvider.getInstance().getCartDAOImpl();
        List<CartedProduct> result;

        try{
            result = dao.viewPageProducts(userId, offset, recsPerPage);
        } catch (DAOException e) {
            logger.error(String.format("Error while retrieving page products from cart with userId %d", userId), e);
            throw new ServiceException(String.format("Error while retrieving page products from cart with userId %d", userId), e);
        }

        return result;
    }
}

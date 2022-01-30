package by.epamtc.ivangavrilovich.shop.service.impl;

import by.epamtc.ivangavrilovich.shop.DAO.DAOProvider;
import by.epamtc.ivangavrilovich.shop.DAO.exceptions.DAOException;
import by.epamtc.ivangavrilovich.shop.DAO.interfaces.CartDAO;
import by.epamtc.ivangavrilovich.shop.DAO.interfaces.OrderDAO;
import by.epamtc.ivangavrilovich.shop.bean.AdminOrder;
import by.epamtc.ivangavrilovich.shop.bean.CartedProduct;
import by.epamtc.ivangavrilovich.shop.bean.Order;
import by.epamtc.ivangavrilovich.shop.service.ServiceProvider;
import by.epamtc.ivangavrilovich.shop.service.exceptions.InvalidInputsException;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;
import by.epamtc.ivangavrilovich.shop.service.interfaces.OrderService;
import by.epamtc.ivangavrilovich.shop.service.interfaces.ValidationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final static Logger logger = LogManager.getLogger();

    @Override
    public void submitOrder(int userId, String address, String info) throws ServiceException, InvalidInputsException {
        ValidationService validator = ServiceProvider.getInstance().getValidationServiceImpl();
        if (!validator.validateEmptiness(address)) {
            throw new InvalidInputsException("This information should be filled");
        }
        CartDAO cartDAO = DAOProvider.getInstance().getCartDAOImpl();
        OrderDAO orderDAO = DAOProvider.getInstance().getOrderDAOImpl();
        double totalPrice;
        List<CartedProduct> products;
        try {
            totalPrice = cartDAO.countTotalPrice(userId);
            products = cartDAO.retrieveProductsForUserById(userId);
            Order order = new Order(userId, totalPrice, address, info);
            orderDAO.addOrder(order, products);
        } catch (DAOException e) {
            logger.error(String.format("Error while submitting order for userId %d", userId), e);
            throw new ServiceException(String.format("Error while submitting order for userId %d", userId), e);
        }
    }

    @Override
    public List<AdminOrder> viewPageAdminOrders(int offset, int recsPerPage) throws ServiceException {
        OrderDAO dao = DAOProvider.getInstance().getOrderDAOImpl();
        List<AdminOrder> thisPageOrders;

        try {
            thisPageOrders = dao.viewPageAdminOrders(offset, recsPerPage);
        } catch (DAOException e) {
            logger.error("Error while retrieving view page products in wrapping method", e);
            throw new ServiceException("Error while retrieving view page products in wrapping method", e);
        }

        return thisPageOrders;
    }

    @Override
    public List<Order> viewPageOrders(int offset, int recsPerPage) throws ServiceException {
        OrderDAO dao = DAOProvider.getInstance().getOrderDAOImpl();
        List<Order> thisPageOrders;

        try {
            thisPageOrders = dao.viewPageOrders(offset, recsPerPage);
        } catch (DAOException e) {
            logger.error("Error while retrieving view page products in wrapping method", e);
            throw new ServiceException("Error while retrieving view page products in wrapping method", e);
        }

        return thisPageOrders;
    }

    @Override
    public int retrieveNumberOfOrders() throws ServiceException {
        OrderDAO dao = DAOProvider.getInstance().getOrderDAOImpl();
        int numberOfOrders;
        try {
            numberOfOrders = dao.retrieveNumberOfOrders();
        } catch (DAOException e) {
            logger.error("Error while retrieving number of orders", e);
            throw new ServiceException("Error while retrieving number of orders", e);
        }

        return numberOfOrders;
    }
}

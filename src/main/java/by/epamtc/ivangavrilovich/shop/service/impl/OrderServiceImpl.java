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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private Map<Integer, Integer> statusesToMap(List<String> statuses) {
        Map<Integer, Integer> result = new HashMap<>();
        int spaceIndex;
        for (String pair : statuses) {
            spaceIndex = pair.indexOf(" ");
            result.put(Integer.parseInt(pair.substring(0, spaceIndex)), Integer.parseInt(pair.substring(spaceIndex + 1)));
        }

        return result;
    }

    @Override
    public void submitAdminChanges(List<String> statusNew, Map<Integer, String> addresses, List<AdminOrder> orders) throws ServiceException {
        OrderDAO dao = DAOProvider.getInstance().getOrderDAOImpl();
        Map<Integer, Integer> statusMap = statusesToMap(statusNew);

        try {
            for (AdminOrder order : orders) {
                int newStatus = statusMap.get(order.getOrder().getOrderId());
                if (order.getOrder().getStatus() != newStatus) {
                    dao.changeStatus(order.getOrder().getOrderId(), newStatus);
                }

                String newAddress = addresses.get(order.getOrder().getOrderId());
                if (!order.getOrder().getAddress().equals(newAddress)) {
                    dao.changeAddress(order.getOrder().getOrderId(), newAddress);
                }
            }
        } catch (DAOException e) {
            logger.error("Error while submitting admin changes", e);
            throw new ServiceException("Error while submitting admin changes", e);
        }
    }

    @Override
    public boolean validateCartByStock(int userId) throws ServiceException {
        OrderDAO dao = DAOProvider.getInstance().getOrderDAOImpl();
        boolean fullyValid;

        try {
            fullyValid = dao.validateCartByStock(userId);
        } catch (DAOException e) {
            logger.error(String.format("Error while validating cart for user %d", userId), e);
            throw new ServiceException(String.format("Error while validating cart for user %d", userId), e);
        }
        return fullyValid;
    }
}

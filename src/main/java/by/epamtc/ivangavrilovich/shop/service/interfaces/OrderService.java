package by.epamtc.ivangavrilovich.shop.service.interfaces;

import by.epamtc.ivangavrilovich.shop.bean.AdminOrder;
import by.epamtc.ivangavrilovich.shop.bean.Order;
import by.epamtc.ivangavrilovich.shop.service.exceptions.InvalidInputsException;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;

import java.util.List;
import java.util.Map;

public interface OrderService {

    void submitOrder(int userId, String address, String info) throws ServiceException, InvalidInputsException;

    List<AdminOrder> viewPageAdminOrders(int offset, int recsPerPage) throws ServiceException;

    List<Order> viewPageOrders(int offset, int recsPerPage) throws ServiceException;

    int retrieveNumberOfOrders() throws ServiceException;

    void submitAdminChanges(List<String> statusNew, Map<Integer, String> addresses, List<AdminOrder> orders) throws ServiceException;

    boolean validateCartByStock(int userId) throws ServiceException;
}

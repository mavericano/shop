package by.epamtc.ivangavrilovich.shop.DAO.interfaces;

import by.epamtc.ivangavrilovich.shop.DAO.exceptions.DAOException;
import by.epamtc.ivangavrilovich.shop.bean.AdminOrder;
import by.epamtc.ivangavrilovich.shop.bean.CartedProduct;
import by.epamtc.ivangavrilovich.shop.bean.Order;

import java.util.List;

public interface OrderDAO {
    void addOrder(Order order, List<CartedProduct> products) throws DAOException;
    List<AdminOrder> viewPageAdminOrders(int offset, int recsPerPage) throws DAOException;
    List<Order> viewPageOrders(int offset, int recsPerPage) throws DAOException;
    int retrieveNumberOfOrders() throws DAOException;
    void changeStatus(int orderId, int newStatus) throws DAOException;
    void changeAddress(int orderId, String newAddress) throws DAOException;
    boolean validateCartByStock(int userId) throws DAOException;
}

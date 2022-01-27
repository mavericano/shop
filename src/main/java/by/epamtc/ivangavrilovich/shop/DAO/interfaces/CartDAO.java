package by.epamtc.ivangavrilovich.shop.DAO.interfaces;

import by.epamtc.ivangavrilovich.shop.DAO.exceptions.DAOException;
import by.epamtc.ivangavrilovich.shop.bean.CartedProduct;

import java.util.List;

public interface CartDAO {
    void addProductToUserById(int userId, int productId) throws DAOException;

    void removeProductFromUserById(int userId, int productId) throws DAOException;

    double countTotalPrice(int userId) throws DAOException;

    int numberOfProducts(int userId) throws DAOException;

    List<CartedProduct> viewPageProducts(int userId, int offset, int recsPerPage) throws DAOException;

    List<CartedProduct> retrieveProductsForUserById(int userId) throws DAOException;

    void increaseQuantity(int userId, int productId) throws DAOException;

    void decreaseQuantity(int userId, int productId) throws DAOException;

    boolean checkPresence(int userId, int productId) throws DAOException;
}

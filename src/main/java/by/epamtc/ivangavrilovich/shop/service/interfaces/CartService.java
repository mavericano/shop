package by.epamtc.ivangavrilovich.shop.service.interfaces;

import by.epamtc.ivangavrilovich.shop.bean.CartedProduct;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;

import java.util.List;

public interface CartService {
    void addItemToCart(int userId, int productId) throws ServiceException;

    void removeItemFromCart(int userId, int productId) throws ServiceException;

    List<CartedProduct> retrieveProductsInCart(int userId) throws ServiceException;

    void increaseQuantity(int userId, int productId) throws ServiceException;

    void decreaseQuantity(int userId, int productId) throws ServiceException;

    boolean checkPresence(int userId, int productId) throws ServiceException;

    int numberOfProducts(int userId) throws ServiceException;

    double countTotalPrice(int userId) throws ServiceException;

    List<CartedProduct> viewPageProducts(int userId, int offset, int recsPerPage) throws ServiceException;
}

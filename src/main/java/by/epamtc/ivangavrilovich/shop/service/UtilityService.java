package by.epamtc.ivangavrilovich.shop.service;

import by.epamtc.ivangavrilovich.shop.bean.Product;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;

import java.util.List;

public interface UtilityService {
    void clearConnectionPool();

    List<Product> findPopularProducts(int amount) throws ServiceException;
}

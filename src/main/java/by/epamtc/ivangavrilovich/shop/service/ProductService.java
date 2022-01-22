package by.epamtc.ivangavrilovich.shop.service;

import by.epamtc.ivangavrilovich.shop.bean.Product;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;

import java.util.HashMap;
import java.util.List;

public interface ProductService {
    //TODO add sorting if enough time
    List<Product> viewPageProducts(int offset, int recsPerPage) throws ServiceException;
    int retrieveNumberOfProducts() throws ServiceException;
}

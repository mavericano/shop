package by.epamtc.ivangavrilovich.shop.service.interfaces;

import by.epamtc.ivangavrilovich.shop.bean.Product;
import by.epamtc.ivangavrilovich.shop.service.exceptions.InvalidInputsException;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<Product> viewPageProducts(int offset, int recsPerPage, boolean viewDel) throws ServiceException;
    List<Product> viewPageProducts(int offset, int recsPerPage) throws ServiceException;
    List<Product> viewPageProducts(int offset, int recsPerPage, int type) throws ServiceException;
    List<Product> viewPageProducts(int offset, int recsPerPage, String query) throws ServiceException;
    int retrieveNumberOfProducts() throws ServiceException;
    int retrieveNumberOfProducts(int type) throws ServiceException;
    int retrieveNumberOfProducts(String query) throws ServiceException;
    Product retrieveProductById(int id) throws ServiceException;
    void updateProduct(int id, String name, String maker, String body, String fret, String scale, String fretAmount, String picks, String beltButton, String price) throws ServiceException, InvalidInputsException;
    void submitAdminChanges(List<String> deletedNew, Map<Integer, Integer> addedStocks, List<Product> products) throws ServiceException;
}

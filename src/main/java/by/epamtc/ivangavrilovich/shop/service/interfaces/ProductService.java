package by.epamtc.ivangavrilovich.shop.service.interfaces;

import by.epamtc.ivangavrilovich.shop.bean.Product;
import by.epamtc.ivangavrilovich.shop.service.exceptions.InvalidInputsException;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;

import java.util.List;

public interface ProductService {
    //TODO add sorting if enough time
    List<Product> viewPageProducts(int offset, int recsPerPage) throws ServiceException;
    int retrieveNumberOfProducts() throws ServiceException;

    Product retrieveProductById(int id) throws ServiceException;

    void updateProduct(int id, String name, String maker, String body, String fret, String scale, String fretAmount, String picks, String beltButton, String price) throws ServiceException, InvalidInputsException;
}

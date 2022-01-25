package by.epamtc.ivangavrilovich.shop.DAO.interfaces;

import by.epamtc.ivangavrilovich.shop.DAO.DAOException;
import by.epamtc.ivangavrilovich.shop.bean.Product;

import java.util.List;

public interface ProductDAO {
    int numberOfProducts() throws DAOException;

    List<Product> viewPageProducts(int offset, int recsPerPage) throws DAOException;

    void addProduct(Product product) throws DAOException;
    List<Product> readProducts() throws DAOException;
    boolean modifyDelStatus(Product product, boolean newStatus) throws DAOException;

    boolean updateProduct(Product product) throws DAOException;

    List<Product> findPopularProducts(int amount) throws DAOException;

    Product retrieveProductById(int id) throws DAOException;
}

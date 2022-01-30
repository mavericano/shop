package by.epamtc.ivangavrilovich.shop.DAO.interfaces;

import by.epamtc.ivangavrilovich.shop.DAO.exceptions.DAOException;
import by.epamtc.ivangavrilovich.shop.bean.Product;

import java.util.List;

public interface ProductDAO {
    int numberOfProducts() throws DAOException;

    int numberOfProducts(String query) throws DAOException;

    List<Product> viewPageProducts(int offset, int recsPerPage) throws DAOException;

    List<Product> viewPageProducts(int offset, int recsPerPage, String query) throws DAOException;

    void addProduct(Product product) throws DAOException;
    List<Product> readProducts() throws DAOException;
    void modifyDelStatus(Product product, boolean newStatus) throws DAOException;

    void updateProduct(Product product) throws DAOException;

    List<Product> findPopularProducts(int amount) throws DAOException;

    Product retrieveProductById(int id) throws DAOException;

    void changeDelStatus(int productId, boolean newStatus) throws DAOException;
}

package by.epamtc.ivangavrilovich.shop.DAO.interfaces;

import by.epamtc.ivangavrilovich.shop.DAO.exceptions.DAOException;
import by.epamtc.ivangavrilovich.shop.bean.Product;

import java.util.List;

public interface ProductDAO {
    int numberOfProducts() throws DAOException;
    int numberOfProducts(String query) throws DAOException;
    List<Product> viewPageProducts(int offset, int recsPerPage, boolean viewDel) throws DAOException;
    List<Product> viewPageProducts(int offset, int recsPerPage, String query) throws DAOException;
    //TODO implement
    void addProduct(Product product) throws DAOException;
    void updateProduct(Product product) throws DAOException;
    List<Product> findPopularProducts(int amount) throws DAOException;
    Product retrieveProductById(int id) throws DAOException;
    Product retrieveProductById(int id, boolean viewDel) throws DAOException;
    void changeDelStatus(int productId, boolean newStatus) throws DAOException;
    void addStock(int productId, int toAdd) throws DAOException;
}

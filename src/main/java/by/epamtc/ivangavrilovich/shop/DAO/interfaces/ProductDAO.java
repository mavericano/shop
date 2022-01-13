package by.epamtc.ivangavrilovich.shop.DAO.interfaces;

import by.epamtc.ivangavrilovich.shop.DAO.DAOException;
import by.epamtc.ivangavrilovich.shop.bean.Product;

import java.util.List;

public interface ProductDAO {
    void addProduct(Product product) throws DAOException;
    List<Product> readProducts() throws DAOException;
    boolean modifyDelStatus(Product product, boolean newStatus) throws DAOException;

    boolean updateProduct(Product product) throws DAOException;

    List<Product> findPopularProducts(int amount) throws DAOException;
}

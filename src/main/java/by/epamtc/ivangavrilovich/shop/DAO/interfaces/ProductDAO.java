package by.epamtc.ivangavrilovich.shop.DAO.interfaces;

import by.epamtc.ivangavrilovich.shop.DAO.DAOException;
import by.epamtc.ivangavrilovich.shop.bean.Product;

public interface ProductDAO {
    void addProduct(Product product) throws DAOException;
}

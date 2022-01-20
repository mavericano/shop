package by.epamtc.ivangavrilovich.shop.service.impl;

import by.epamtc.ivangavrilovich.shop.DAO.ConnectionPool;
import by.epamtc.ivangavrilovich.shop.DAO.DAOException;
import by.epamtc.ivangavrilovich.shop.DAO.DAOProvider;
import by.epamtc.ivangavrilovich.shop.bean.Product;
import by.epamtc.ivangavrilovich.shop.service.UtilityService;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;

import java.util.List;

public class UtilityServiceImpl implements UtilityService {
    @Override
    public void clearConnectionPool() {
        ConnectionPool.getInstance().dispose();
    }

    @Override
    public List<Product> findPopularProducts(int amount) throws ServiceException {
        try {
            return DAOProvider.getInstance().getProductDAOImpl().findPopularProducts(amount);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void initConnectionPool() {
        ConnectionPool.getInstance();
    }
}

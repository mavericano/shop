package by.epamtc.ivangavrilovich.shop.service.impl;

import by.epamtc.ivangavrilovich.shop.DAO.ConnectionPool;
import by.epamtc.ivangavrilovich.shop.DAO.exceptions.DAOException;
import by.epamtc.ivangavrilovich.shop.DAO.DAOProvider;
import by.epamtc.ivangavrilovich.shop.bean.Product;
import by.epamtc.ivangavrilovich.shop.service.interfaces.UtilityService;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

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
    public void updateLocaleInSession(HttpSession session, Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("bundles/text", locale);

        for (Enumeration<String> e = bundle.getKeys(); e.hasMoreElements();) {
            String key = e.nextElement();
            String s = bundle.getString(key);
            session.setAttribute(key, s);
        }
    }

    @Override
    public void initConnectionPool() {
        ConnectionPool.getInstance();
    }
}

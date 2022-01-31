package by.epamtc.ivangavrilovich.shop.service.interfaces;

import by.epamtc.ivangavrilovich.shop.bean.Product;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

public interface UtilityService {
    void clearConnectionPool();

    void updateLocaleInSession(HttpSession session, Locale locale);

    void initConnectionPool();
    List<Product> findPopularProducts(int amount) throws ServiceException;

    String cropSalt(String hashWithSalt);
}

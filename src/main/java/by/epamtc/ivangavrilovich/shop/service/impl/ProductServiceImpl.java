package by.epamtc.ivangavrilovich.shop.service.impl;

import by.epamtc.ivangavrilovich.shop.DAO.DAOException;
import by.epamtc.ivangavrilovich.shop.DAO.DAOProvider;
import by.epamtc.ivangavrilovich.shop.DAO.interfaces.ProductDAO;
import by.epamtc.ivangavrilovich.shop.bean.Product;
import by.epamtc.ivangavrilovich.shop.service.ProductService;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final static Logger logger = LogManager.getLogger();

    //TODO add sorting if enough time
    @Override
    public List<Product> viewPageProducts(int offset, int recsPerPage) throws ServiceException {
        ProductDAO dao = DAOProvider.getInstance().getProductDAOImpl();
        List<Product> thisPageProducts;

        try {
            thisPageProducts = dao.viewPageProducts(offset, recsPerPage);
        } catch (DAOException e) {
            logger.error("Error while retrieving view page products in wrapping method", e);
            throw new ServiceException("Error while retrieving view page products in wrapping method", e);
        }

        return thisPageProducts;
    }

    @Override
    public int retrieveNumberOfProducts() throws ServiceException {
        ProductDAO dao = DAOProvider.getInstance().getProductDAOImpl();
        int numberOfProducts;
        try {
            numberOfProducts = dao.numberOfProducts();
        } catch (DAOException e) {
            logger.error("Error while retrieving number of products", e);
            throw new ServiceException("Error while retrieving number of products", e);
        }

        return numberOfProducts;
    }
}

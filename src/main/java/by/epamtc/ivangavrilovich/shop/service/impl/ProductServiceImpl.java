package by.epamtc.ivangavrilovich.shop.service.impl;

import by.epamtc.ivangavrilovich.shop.DAO.exceptions.DAOException;
import by.epamtc.ivangavrilovich.shop.DAO.DAOProvider;
import by.epamtc.ivangavrilovich.shop.DAO.interfaces.ProductDAO;
import by.epamtc.ivangavrilovich.shop.bean.Product;
import by.epamtc.ivangavrilovich.shop.service.ServiceProvider;
import by.epamtc.ivangavrilovich.shop.service.exceptions.InvalidInputsException;
import by.epamtc.ivangavrilovich.shop.service.interfaces.ProductService;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;
import by.epamtc.ivangavrilovich.shop.service.interfaces.ValidationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

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
    public List<Product> viewPageProducts(int offset, int recsPerPage, String query) throws ServiceException {
        ProductDAO dao = DAOProvider.getInstance().getProductDAOImpl();
        List<Product> thisPageProducts;

        try {
            thisPageProducts = dao.viewPageProducts(offset, recsPerPage, query);
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

    @Override
    public int retrieveNumberOfProducts(String query) throws ServiceException {
        ProductDAO dao = DAOProvider.getInstance().getProductDAOImpl();
        int numberOfProducts;
        try {
            numberOfProducts = dao.numberOfProducts(query);
        } catch (DAOException e) {
            logger.error("Error while retrieving number of products", e);
            throw new ServiceException("Error while retrieving number of products", e);
        }

        return numberOfProducts;
    }

    @Override
    public Product retrieveProductById(int id) throws ServiceException  {
        ProductDAO dao = DAOProvider.getInstance().getProductDAOImpl();
        Product result;
        try {
            result = dao.retrieveProductById(id);
        } catch (DAOException e) {
            logger.error(String.format("Error while retrieving product by id %d", id), e);
            throw new ServiceException(String.format("Error while retrieving product by id %d", id), e);
        }

        return result;
    }

    @Override
    public void updateProduct(int id, String name, String maker, String body, String fret, String scale, String fretAmount, String picks, String beltButton, String price) throws ServiceException, InvalidInputsException {
        ValidationService validator = ServiceProvider.getInstance().getValidationServiceImpl();
        if (!validator.validateEmptiness(name, maker, body, fret, scale, fretAmount, picks, beltButton, price)) {
            throw new InvalidInputsException("One of fields is empty");
        }
        if (!validator.validateInt(scale)) {
            throw new InvalidInputsException("Scale must be a number");
        }
        if (!validator.validateInt(fretAmount)) {
            throw new InvalidInputsException("Fret amount must be a number");
        }
        if (!validator.validateBeltButton(beltButton)) {
            throw new InvalidInputsException("\"Belt button\" must contain either \"yes\" or \"no\"");
        }
        if (!validator.validateFloat(price)) {
            throw new InvalidInputsException("Price must be a number (x.xx or xxx)");
        }

        int scaleInt = Integer.parseInt(scale);
        int amountInt = Integer.parseInt(fretAmount);
        boolean beltButtonBool = "yes".equals(beltButton);
        double priceDouble = Double.parseDouble(price);
        Product product = new Product(id, name, priceDouble, maker, body, fret, scaleInt, amountInt, picks, beltButtonBool);
        ProductDAO dao = DAOProvider.getInstance().getProductDAOImpl();
        try {
            dao.updateProduct(product);
        } catch (DAOException e) {
            logger.error(String.format("Error while updating product by id %d", id), e);
            throw new ServiceException(String.format("Error while updating product by id %d", id), e);
        }
    }

    @Override
    public void submitAdminChanges(List<String> deletedNew, Map<Integer, Integer> addedStocks, List<Product> products) throws ServiceException {
        ProductDAO dao = DAOProvider.getInstance().getProductDAOImpl();
        boolean deletedNewBool;

        try {
            for (Product product : products) {
                deletedNewBool = deletedNew.contains(String.valueOf(product.getProductId()));

                if (product.isDeleted() != deletedNewBool) {
                    dao.changeDelStatus(product.getProductId(), deletedNewBool);
                }

                if (addedStocks.get(product.getProductId()) != 0) {
                    //TODO implement
//                    dao.changeRole(user.getUserId(), roleMap.get(user.getUserId()));
                }
            }
        } catch (DAOException e) {
            logger.error("Error while submitting admin changes", e);
            throw new ServiceException("Error while submitting admin changes", e);
        }
    }
}

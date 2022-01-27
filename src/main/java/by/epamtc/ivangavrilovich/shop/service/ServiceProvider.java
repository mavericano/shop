package by.epamtc.ivangavrilovich.shop.service;

import by.epamtc.ivangavrilovich.shop.service.impl.*;
import by.epamtc.ivangavrilovich.shop.service.interfaces.*;

public class ServiceProvider {
    private UserService userServiceImpl;
    private UtilityService utilityServiceImpl;
    private ProductService productServiceImpl;
    private ValidationService validationServiceImpl;
    private CartService cartServiceImpl;

    private final static class InstanceHolder {
        private final static ServiceProvider INSTANCE = new ServiceProvider();
    }

    private ServiceProvider() {
        userServiceImpl = new UserServiceImpl();
        utilityServiceImpl = new UtilityServiceImpl();
        productServiceImpl = new ProductServiceImpl();
        validationServiceImpl = new ValidationServiceImpl();
        cartServiceImpl = new CartServiceImpl();
    }

    public static ServiceProvider getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public UserService getUserServiceImpl() {
        return userServiceImpl;
    }

    public void setUserServiceImpl(UserService userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    public UtilityService getUtilityServiceImpl() {
        return utilityServiceImpl;
    }

    public void setUtilityServiceImpl(UtilityService utilityServiceImpl) {
        this.utilityServiceImpl = utilityServiceImpl;
    }

    public ProductService getProductServiceImpl() {
        return productServiceImpl;
    }

    public void setProductServiceImpl(ProductService productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    public ValidationService getValidationServiceImpl() {
        return validationServiceImpl;
    }

    public void setValidationServiceImpl(ValidationService validationServiceImpl) {
        this.validationServiceImpl = validationServiceImpl;
    }

    public CartService getCartServiceImpl() {
        return cartServiceImpl;
    }

    public void setCartServiceImpl(CartService cartServiceImpl) {
        this.cartServiceImpl = cartServiceImpl;
    }
}

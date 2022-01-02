package by.epamtc.ivangavrilovich.shop.service;

import by.epamtc.ivangavrilovich.shop.service.impl.UserServiceImpl;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();

    public UserService getUserServiceImpl() {
        return userServiceImpl;
    }

    public void setUserServiceImpl(UserService userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    private UserService userServiceImpl;


    private ServiceProvider() {
        userServiceImpl = new UserServiceImpl();
    }

    public static ServiceProvider getInstance() {
        return instance;
    }

}

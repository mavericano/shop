package by.epamtc.ivangavrilovich.shop.service;

import by.epamtc.ivangavrilovich.shop.service.impl.UserServiceImpl;
import by.epamtc.ivangavrilovich.shop.service.impl.UtilityServiceImpl;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();
    private UserService userServiceImpl;
    private UtilityService utilityServiceImpl;

    private ServiceProvider() {
        userServiceImpl = new UserServiceImpl();
        utilityServiceImpl = new UtilityServiceImpl();
    }

    public static ServiceProvider getInstance() {
        return instance;
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
}

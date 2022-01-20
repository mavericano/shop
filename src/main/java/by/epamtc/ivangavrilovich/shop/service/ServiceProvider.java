package by.epamtc.ivangavrilovich.shop.service;

import by.epamtc.ivangavrilovich.shop.service.impl.UserServiceImpl;
import by.epamtc.ivangavrilovich.shop.service.impl.UtilityServiceImpl;

public class ServiceProvider {
    private UserService userServiceImpl;
    private UtilityService utilityServiceImpl;

    private final static class InstanceHolder {
        private final static ServiceProvider INSTANCE = new ServiceProvider();
    }

    private ServiceProvider() {
        userServiceImpl = new UserServiceImpl();
        utilityServiceImpl = new UtilityServiceImpl();
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
}

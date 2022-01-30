package by.epamtc.ivangavrilovich.shop.controller.listeners;

import by.epamtc.ivangavrilovich.shop.service.ServiceProvider;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServiceProvider.getInstance().getUtilityServiceImpl().initConnectionPool();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServiceProvider.getInstance().getUtilityServiceImpl().clearConnectionPool();
    }
}

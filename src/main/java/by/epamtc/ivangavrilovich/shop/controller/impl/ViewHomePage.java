package by.epamtc.ivangavrilovich.shop.controller.impl;

import by.epamtc.ivangavrilovich.shop.bean.Product;
import by.epamtc.ivangavrilovich.shop.controller.Command;
import by.epamtc.ivangavrilovich.shop.service.ServiceProvider;
import by.epamtc.ivangavrilovich.shop.service.UtilityService;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewHomePage implements Command {
    private final static Logger logger = LogManager.getLogger();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UtilityService service = ServiceProvider.getInstance().getUtilityServiceImpl();
        try {
            List<Product> products = service.findPopularProducts(3);
            request.getSession(true).setAttribute("popularProducts", products);
        } catch (ServiceException e) {
            logger.error("Error while redirecting to home page", e);
            response.sendRedirect(request.getContextPath() + "/pages/serverException.jsp");
        }
        response.sendRedirect(request.getContextPath() + "/pages/main.jsp");
    }
}

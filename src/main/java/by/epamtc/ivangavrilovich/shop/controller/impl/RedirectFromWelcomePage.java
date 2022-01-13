package by.epamtc.ivangavrilovich.shop.controller.impl;

import by.epamtc.ivangavrilovich.shop.bean.Product;
import by.epamtc.ivangavrilovich.shop.controller.Command;
import by.epamtc.ivangavrilovich.shop.service.ServiceProvider;
import by.epamtc.ivangavrilovich.shop.service.UserService;
import by.epamtc.ivangavrilovich.shop.service.UtilityService;
import by.epamtc.ivangavrilovich.shop.service.exceptions.AlreadyRegisteredException;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RedirectFromWelcomePage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UtilityService service = ServiceProvider.getInstance().getUtilityServiceImpl();
        try {
            List<Product> products = service.findPopularProducts(3);
            request.getSession(true).setAttribute("popularProducts", products);
        } catch (ServiceException e) {
            //TODO add redirection to err page
        }
        response.sendRedirect("main.jsp");
    }
}

package by.epamtc.ivangavrilovich.shop.controller.impl;

import by.epamtc.ivangavrilovich.shop.bean.User;
import by.epamtc.ivangavrilovich.shop.controller.Command;
import by.epamtc.ivangavrilovich.shop.service.ServiceProvider;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;
import by.epamtc.ivangavrilovich.shop.service.interfaces.CartService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RemoveFromCart implements Command {
    private final static Logger logger = LogManager.getLogger();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CartService service = ServiceProvider.getInstance().getCartServiceImpl();
        int productId = Integer.parseInt(request.getParameter("id"));
        int userId = ((User)request.getSession(true).getAttribute("user")).getUserId();
        try {
            service.removeItemFromCart(userId, productId);
            response.sendRedirect(request.getContextPath() + "/pages/controller?" + request.getSession(true).getAttribute("lastAction"));
        } catch (ServiceException e) {
            logger.error("Error while removing from cart", e);
            response.sendRedirect(request.getContextPath() + "/pages/serverException.jsp");
        }
    }

    @Override
    public List<Integer> getAppropriateRoles() {
        return Arrays.asList(1);
    }
}

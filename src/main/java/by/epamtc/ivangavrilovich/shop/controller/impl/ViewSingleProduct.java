package by.epamtc.ivangavrilovich.shop.controller.impl;

import by.epamtc.ivangavrilovich.shop.bean.Product;
import by.epamtc.ivangavrilovich.shop.bean.User;
import by.epamtc.ivangavrilovich.shop.controller.Command;
import by.epamtc.ivangavrilovich.shop.service.interfaces.CartService;
import by.epamtc.ivangavrilovich.shop.service.interfaces.ProductService;
import by.epamtc.ivangavrilovich.shop.service.ServiceProvider;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ViewSingleProduct implements Command {
    private final static Logger logger = LogManager.getLogger();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession(true).setAttribute("lastAction", request.getQueryString());
        ProductService service = ServiceProvider.getInstance().getProductServiceImpl();
        CartService cartService = ServiceProvider.getInstance().getCartServiceImpl();
        int productId = Integer.parseInt(request.getParameter("id"));
        int userId = ((User)request.getSession(true).getAttribute("user")).getUserId();
        Product product;
        boolean alreadyInCart = false;
        try {
            product = service.retrieveProductById(productId);
            alreadyInCart = cartService.checkPresence(userId, productId);
            request.setAttribute("product", product);
            request.setAttribute("alreadyInCart", alreadyInCart);
            request.getRequestDispatcher("product.jsp").forward(request, response);
        } catch (ServiceException e) {
            logger.error("Error while retrieving all products with wrap", e);
            response.sendRedirect(request.getContextPath() + "/pages/serverException.jsp");
        }
    }

    @Override
    public List<Integer> getAppropriateRoles() {
        return Arrays.asList(1, 2, 3, 4);
    }
}

package by.epamtc.ivangavrilovich.shop.controller.impl;

import by.epamtc.ivangavrilovich.shop.bean.CartedProduct;
import by.epamtc.ivangavrilovich.shop.bean.Product;
import by.epamtc.ivangavrilovich.shop.bean.User;
import by.epamtc.ivangavrilovich.shop.controller.Command;
import by.epamtc.ivangavrilovich.shop.service.ServiceProvider;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;
import by.epamtc.ivangavrilovich.shop.service.interfaces.CartService;
import by.epamtc.ivangavrilovich.shop.service.interfaces.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ViewCart implements Command {
    private final static int RECS_PER_PAGE = 4;
    private final static Logger logger = LogManager.getLogger();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession(true).setAttribute("lastAction", request.getQueryString());
        CartService service = ServiceProvider.getInstance().getCartServiceImpl();

        String page = request.getParameter("page");
        if (page == null) {
            page = String.valueOf(1);
        }
        request.setAttribute("page", page);
        int offset = (Integer.parseInt(page) - 1) * RECS_PER_PAGE;
        int id = ((User)request.getSession(true).getAttribute("user")).getUserId();
        List<CartedProduct> cartedProducts;
        try {
            cartedProducts = service.viewPageProducts(id, offset, RECS_PER_PAGE);
            int numberOfProducts = service.numberOfProducts(id);
            double totalPrice = service.countTotalPrice(id);
            int numberOfPages = (int) Math.ceil(numberOfProducts * 1.0 / RECS_PER_PAGE);
            request.setAttribute("products", cartedProducts);
            request.setAttribute("numberOfPages", numberOfPages);
            request.setAttribute("totalPrice", totalPrice);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        } catch (ServiceException e) {
            logger.error("Error while viewing cart", e);
            response.sendRedirect(request.getContextPath() + "/pages/serverException.jsp");
        }
    }

    @Override
    public List<Integer> getAppropriateRoles() {
        return Arrays.asList(1);
    }
}

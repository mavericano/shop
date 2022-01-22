package by.epamtc.ivangavrilovich.shop.controller.impl;

import by.epamtc.ivangavrilovich.shop.bean.Product;
import by.epamtc.ivangavrilovich.shop.controller.Command;
import by.epamtc.ivangavrilovich.shop.service.ProductService;
import by.epamtc.ivangavrilovich.shop.service.ServiceProvider;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewAllProducts implements Command {
    private final static int RECS_PER_PAGE = 9;
    private final static Logger logger = LogManager.getLogger();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ProductService service = ServiceProvider.getInstance().getProductServiceImpl();

        String page = request.getParameter("page");
        if (page == null) {
            page = String.valueOf(1);
        }
        request.setAttribute("page", page);
        int offset = (Integer.parseInt(page) - 1) * RECS_PER_PAGE;

        try {
            List<Product> products = service.viewPageProducts(offset, RECS_PER_PAGE);
            int numberOfProducts = service.retrieveNumberOfProducts();
            int numberOfPages = (int) Math.ceil(numberOfProducts * 1.0 / RECS_PER_PAGE);
            request.setAttribute("products", products);
            request.setAttribute("numberOfPages", numberOfPages);
            request.getRequestDispatcher("catalogue.jsp").forward(request, response);
        } catch (ServiceException e) {
            logger.error("Error while retrieving all products with wrap", e);
            response.sendRedirect(request.getContextPath() + "/pages/serverException.jsp");
        }
    }
}

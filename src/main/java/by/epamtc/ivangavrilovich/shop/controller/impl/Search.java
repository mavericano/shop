package by.epamtc.ivangavrilovich.shop.controller.impl;

import by.epamtc.ivangavrilovich.shop.bean.Product;
import by.epamtc.ivangavrilovich.shop.controller.Command;
import by.epamtc.ivangavrilovich.shop.service.ServiceProvider;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;
import by.epamtc.ivangavrilovich.shop.service.interfaces.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Search implements Command {
    private final static Logger logger = LogManager.getLogger();
    private final static int RECS_PER_PAGE = 9;
    public static final String PAGES_CONTROLLER_COMMAND_SEARCH_SEARCH_TEXT = "/pages/controller?command=SEARCH&searchText=";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession(true).setAttribute("lastAction", request.getQueryString());
        ProductService service = ServiceProvider.getInstance().getProductServiceImpl();
        String query = request.getParameter("searchText");

        String page = request.getParameter("page");
        if (page == null) {
            page = String.valueOf(1);
        }
        request.setAttribute("page", page);
        int offset = (Integer.parseInt(page) - 1) * RECS_PER_PAGE;

        try {
            List<Product> products = service.viewPageProducts(offset, RECS_PER_PAGE, query);
            int numberOfProducts = service.retrieveNumberOfProducts(query);
            int numberOfPages = (int) Math.ceil(numberOfProducts * 1.0 / RECS_PER_PAGE);
            request.setAttribute("products", products);
            request.setAttribute("numberOfPages", numberOfPages);
            request.setAttribute("path", PAGES_CONTROLLER_COMMAND_SEARCH_SEARCH_TEXT + query + "&page=");
            request.getRequestDispatcher("catalogue.jsp").forward(request, response);
        } catch (ServiceException e) {
            logger.error("Error while retrieving searched products with wrap", e);
            response.sendRedirect(request.getContextPath() + "/pages/serverException.jsp");
        }
    }
}

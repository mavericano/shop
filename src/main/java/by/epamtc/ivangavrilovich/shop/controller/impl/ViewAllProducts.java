package by.epamtc.ivangavrilovich.shop.controller.impl;

import by.epamtc.ivangavrilovich.shop.bean.Product;
import by.epamtc.ivangavrilovich.shop.controller.Command;
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

public class ViewAllProducts implements Command {
    private final static int RECS_PER_PAGE = 9;
    private final static Logger logger = LogManager.getLogger();
    public static final String PAGES_CONTROLLER_COMMAND_VIEW_ALL_PRODUCTS_PAGE = "/pages/controller?command=VIEW_ALL_PRODUCTS&page=";
    public static final String PAGES_CONTROLLER_COMMAND_VIEW_ALL_PRODUCTS_PAGE_TYPE = "/pages/controller?command=VIEW_ALL_PRODUCTS&type=";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession(true).setAttribute("lastAction", request.getQueryString());
        ProductService service = ServiceProvider.getInstance().getProductServiceImpl();

        String page = request.getParameter("page");
        if (page == null) {
            page = String.valueOf(1);
        }
        request.setAttribute("page", page);
        int offset = (Integer.parseInt(page) - 1) * RECS_PER_PAGE;

        String type = request.getParameter("type");
        boolean showType = type != null;
        List<Product> products;
        int numberOfProducts;
        try {
            if (showType) {
                int typeInt = Integer.parseInt(type);
                products = service.viewPageProducts(offset, RECS_PER_PAGE, typeInt);
                numberOfProducts = service.retrieveNumberOfProducts(typeInt);
                request.setAttribute("path", createPathForType(type));
            } else {
                products = service.viewPageProducts(offset, RECS_PER_PAGE);
                numberOfProducts = service.retrieveNumberOfProducts();
                request.setAttribute("path", PAGES_CONTROLLER_COMMAND_VIEW_ALL_PRODUCTS_PAGE);
            }

            int numberOfPages = (int) Math.ceil(numberOfProducts * 1.0 / RECS_PER_PAGE);
            request.setAttribute("products", products);
            request.setAttribute("numberOfPages", numberOfPages);
            request.getRequestDispatcher("catalogue.jsp").forward(request, response);
        } catch (ServiceException e) {
            logger.error("Error while retrieving all products with wrap", e);
            response.sendRedirect(request.getContextPath() + "/pages/serverException.jsp");
        }
    }

    @Override
    public List<Integer> getAppropriateRoles() {
        return Arrays.asList(1, 2, 3, 4);
    }

    private String createPathForType(String type) {
        return PAGES_CONTROLLER_COMMAND_VIEW_ALL_PRODUCTS_PAGE_TYPE + type + "&page=";
    }
}

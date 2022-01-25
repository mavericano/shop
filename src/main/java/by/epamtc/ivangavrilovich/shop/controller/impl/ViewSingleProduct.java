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

public class ViewSingleProduct implements Command {
    private final static Logger logger = LogManager.getLogger();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession(true).setAttribute("lastAction", request.getQueryString());
        ProductService service = ServiceProvider.getInstance().getProductServiceImpl();
        //TODO add wrapping
        int id = Integer.parseInt(request.getParameter("id"));
        Product product;
        try {
            product = service.retrieveProductById(id);
            request.setAttribute("product", product);
            request.getRequestDispatcher("product.jsp").forward(request, response);
        } catch (ServiceException e) {
            logger.error("Error while retrieving all products with wrap", e);
            response.sendRedirect(request.getContextPath() + "/pages/serverException.jsp");
        }
    }
}

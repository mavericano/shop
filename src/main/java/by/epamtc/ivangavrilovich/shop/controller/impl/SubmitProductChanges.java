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
import java.util.*;

public class SubmitProductChanges implements Command {
    private final static Logger logger = LogManager.getLogger();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ProductService service = ServiceProvider.getInstance().getProductServiceImpl();
        List<String> deletedNew = request.getParameterValues("deleted") == null ?
                new ArrayList<>() :
                Arrays.asList(request.getParameterValues("deleted"));
        List<Product> products = (List<Product>) request.getSession(true).getAttribute("products");
        request.getSession(true).removeAttribute("products");

        String toAdd;
        Map<Integer, Integer> addedStocks = new HashMap<>();
        for (Product product : products) {
            toAdd = request.getParameter(product.getProductId() + "toAddToStock");
            addedStocks.put(product.getProductId(), Integer.parseInt(toAdd));
        }

        try {
            service.submitAdminChanges(deletedNew, addedStocks, products);
            //TODO add redirect
        } catch (ServiceException e) {
            logger.error("Error while submitting product changes", e);
            response.sendRedirect(request.getContextPath() + "/pages/serverException.jsp");
        }
    }
}

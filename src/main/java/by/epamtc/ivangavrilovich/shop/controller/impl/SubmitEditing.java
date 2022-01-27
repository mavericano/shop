package by.epamtc.ivangavrilovich.shop.controller.impl;

import by.epamtc.ivangavrilovich.shop.controller.Command;
import by.epamtc.ivangavrilovich.shop.service.ServiceProvider;
import by.epamtc.ivangavrilovich.shop.service.exceptions.InvalidInputsException;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;
import by.epamtc.ivangavrilovich.shop.service.interfaces.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SubmitEditing implements Command {
    private final static Logger logger = LogManager.getLogger();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ProductService service = ServiceProvider.getInstance().getProductServiceImpl();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String maker = request.getParameter("maker");
        String body = request.getParameter("body");
        String fret = request.getParameter("fret");
        String scale = request.getParameter("scale");
        String fretAmount = request.getParameter("fretAmount");
        String picks = request.getParameter("picks");
        String beltButton = request.getParameter("beltButton");
        String price = request.getParameter("price");
        try {
            service.updateProduct(id, name, maker, body, fret, scale, fretAmount, picks, beltButton, price);
            response.sendRedirect(request.getContextPath() + "/pages/controller?command=VIEW_SINGLE_PRODUCT&id=" + id);
        } catch (InvalidInputsException e) {
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/pages/controller?command=VIEW_SINGLE_PRODUCT&id=" + id).forward(request, response);
        } catch (ServiceException e) {
            logger.error("Error while retrieving all products with wrap", e);
            response.sendRedirect(request.getContextPath() + "/pages/serverException.jsp");
        }
    }
}

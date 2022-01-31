package by.epamtc.ivangavrilovich.shop.controller.impl;

import by.epamtc.ivangavrilovich.shop.bean.CartedProduct;
import by.epamtc.ivangavrilovich.shop.bean.User;
import by.epamtc.ivangavrilovich.shop.controller.Command;
import by.epamtc.ivangavrilovich.shop.service.ServiceProvider;
import by.epamtc.ivangavrilovich.shop.service.exceptions.InvalidInputsException;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;
import by.epamtc.ivangavrilovich.shop.service.interfaces.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SubmitOrder implements Command {
    private final static Logger logger = LogManager.getLogger();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        OrderService service = ServiceProvider.getInstance().getOrderServiceImpl();
        int userId = ((User) request.getSession(true).getAttribute("user")).getUserId();
        String address = request.getParameter("address");
        String info = request.getParameter("info");
        boolean fullyValid;
        request.setAttribute("viewModal", true);
        try {
            fullyValid = service.validateCartByStock(userId);
            if (fullyValid) {
                service.submitOrder(userId, address, info);
                request.setAttribute("modalMessage", request.getSession(true).getAttribute("successfulSubmission"));
                request.getRequestDispatcher("/pages/controller?command=VIEW_HOME_PAGE").forward(request, response);
            } else {
                request.setAttribute("modalMessage", request.getSession(true).getAttribute("cartInvalid"));
                request.getRequestDispatcher("/pages/controller?command=VIEW_CART").forward(request, response);
            }
        } catch (InvalidInputsException e) {
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/pages/controller?command=VIEW_CART").forward(request, response);
        } catch (ServiceException e) {
            logger.error("Error while submitting order", e);
            request.setAttribute("modalMessage", request.getSession(true).getAttribute("unsuccessfulSubmission"));
            request.getRequestDispatcher("/pages/controller?command=VIEW_CART").forward(request, response);
        }
    }
}

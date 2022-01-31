package by.epamtc.ivangavrilovich.shop.controller.impl;

import by.epamtc.ivangavrilovich.shop.bean.AdminOrder;
import by.epamtc.ivangavrilovich.shop.controller.Command;
import by.epamtc.ivangavrilovich.shop.service.ServiceProvider;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;
import by.epamtc.ivangavrilovich.shop.service.interfaces.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class SubmitOrderChanges implements Command {
    private final static Logger logger = LogManager.getLogger();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        OrderService service = ServiceProvider.getInstance().getOrderServiceImpl();
        List<String> statusNew = request.getParameterValues("status") == null ?
                new ArrayList<>() :
                Arrays.asList(request.getParameterValues("status"));
        List<AdminOrder> orders = (List<AdminOrder>) request.getSession(true).getAttribute("orders");

        String address;
        Map<Integer, String> addresses = new HashMap<>();
        for (AdminOrder order : orders) {
            address = request.getParameter(order.getOrder().getOrderId() + "address").trim();
            addresses.put(order.getOrder().getOrderId(), address);
        }

        try {
            service.submitAdminChanges(statusNew, addresses, orders);
            request.getSession(true).removeAttribute("orders");
            response.sendRedirect(request.getContextPath() + "/pages/controller?command=VIEW_ADMIN_SCREEN");
        } catch (ServiceException e) {
            logger.error("Error while submitting product changes", e);
            response.sendRedirect(request.getContextPath() + "/pages/serverException.jsp");
        }
    }
}

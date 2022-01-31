package by.epamtc.ivangavrilovich.shop.controller.impl;

import by.epamtc.ivangavrilovich.shop.bean.AdminOrder;
import by.epamtc.ivangavrilovich.shop.bean.Order;
import by.epamtc.ivangavrilovich.shop.bean.Product;
import by.epamtc.ivangavrilovich.shop.bean.User;
import by.epamtc.ivangavrilovich.shop.controller.Command;
import by.epamtc.ivangavrilovich.shop.service.ServiceProvider;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;
import by.epamtc.ivangavrilovich.shop.service.interfaces.OrderService;
import by.epamtc.ivangavrilovich.shop.service.interfaces.ProductService;
import by.epamtc.ivangavrilovich.shop.service.interfaces.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewAdminScreen implements Command {
    private final static Logger logger = LogManager.getLogger();
    private final static int RECS_PER_PAGE = 9;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession(true).setAttribute("lastAction", request.getQueryString());
        if (((User)request.getSession(true).getAttribute("user")).getRole() != 3) response.sendRedirect(request.getContextPath() + "/pages/controller?command=VIEW_SIGN_IN");
        UserService userService = ServiceProvider.getInstance().getUserServiceImpl();
        ProductService productService = ServiceProvider.getInstance().getProductServiceImpl();
        OrderService orderService = ServiceProvider.getInstance().getOrderServiceImpl();
        List<User> users = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        List<AdminOrder> orders = new ArrayList<>();

        String pageUsers = request.getParameter("pageUsers");
        if (pageUsers == null) {
            pageUsers = String.valueOf(1);
        }
        request.setAttribute("pageUsers", pageUsers);
        int offsetUsers = (Integer.parseInt(pageUsers) - 1) * RECS_PER_PAGE;

        String pageProducts = request.getParameter("pageProducts");
        if (pageProducts == null) {
            pageProducts = String.valueOf(1);
        }
        request.setAttribute("pageProducts", pageProducts);
        int offsetProducts = (Integer.parseInt(pageProducts) - 1) * RECS_PER_PAGE;

        String pageOrders = request.getParameter("pageOrders");
        if (pageOrders == null) {
            pageOrders = String.valueOf(1);
        }
        request.setAttribute("pageOrders", pageOrders);
        int offsetOrders = (Integer.parseInt(pageOrders) - 1) * RECS_PER_PAGE;

        try {
            users = userService.viewPageUsers(offsetUsers, RECS_PER_PAGE, true);
            products = productService.viewPageProducts(offsetProducts, RECS_PER_PAGE, true);
            orders = orderService.viewPageAdminOrders(offsetOrders, RECS_PER_PAGE);
            int numberOfUsers = userService.retrieveNumberOfUsers();
            int numberOfUserPages = (int) Math.ceil(numberOfUsers * 1.0 / RECS_PER_PAGE);
            int numberOfProducts = productService.retrieveNumberOfProducts();
            int numberOfProductPages = (int) Math.ceil(numberOfProducts * 1.0 / RECS_PER_PAGE);
            int numberOfOrders = orderService.retrieveNumberOfOrders();
            int numberOfOrderPages = (int) Math.ceil(numberOfOrders * 1.0 / RECS_PER_PAGE);
            request.setAttribute("numberOfUserPages", numberOfUserPages);
            request.setAttribute("numberOfProductPages", numberOfProductPages);
            request.setAttribute("numberOfOrderPages", numberOfOrderPages);
            request.setAttribute("users", users);
            request.setAttribute("products", products);
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("adminPage.jsp").forward(request, response);
        } catch (ServiceException e) {
            logger.error("Error while loading admin screen", e);
            response.sendRedirect(request.getContextPath() + "/pages/serverException.jsp");
        }
    }
}

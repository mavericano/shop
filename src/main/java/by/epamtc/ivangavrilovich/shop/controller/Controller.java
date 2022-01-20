package by.epamtc.ivangavrilovich.shop.controller;

import by.epamtc.ivangavrilovich.shop.service.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

//@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    public void init() {
        ServiceProvider.getInstance().getUtilityServiceImpl().initConnectionPool();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //TODO remove
        //request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        System.out.println(request.getParameter("command"));
        CommandProvider.getInstance().provideCommand(request.getParameter("command")).execute(request, response);
        logger.debug(request.getParameter("command"));
    }

    public void destroy() {
        ServiceProvider.getInstance().getUtilityServiceImpl().clearConnectionPool();
    }
}
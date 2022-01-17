package by.epamtc.ivangavrilovich.shop.controller;

import by.epamtc.ivangavrilovich.shop.bean.User;
import by.epamtc.ivangavrilovich.shop.service.ServiceProvider;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

//@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class Controller extends HttpServlet {
    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        setUpSession(request);
        CommandProvider.getInstance().provideCommand(request.getParameter("command")).execute(request, response);
        System.out.println(request.getParameter("command"));
    }

    private void setUpSession(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("user") == null) {
            User user = new User();
            user.setRole(4); // guest role id
            session.setAttribute("user", user);
        }
        if (session.getAttribute("language") == null) {
            session.setAttribute("language", "russian");
        }
    }

    public void destroy() {
        ServiceProvider.getInstance().getUtilityServiceImpl().clearConnectionPool();
    }
}
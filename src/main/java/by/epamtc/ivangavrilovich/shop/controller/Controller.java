package by.epamtc.ivangavrilovich.shop.controller;

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
        CommandProvider.getInstance().provideCommand(request.getParameter("command")).execute(request, response);
    }

    public void destroy() {
    }
}
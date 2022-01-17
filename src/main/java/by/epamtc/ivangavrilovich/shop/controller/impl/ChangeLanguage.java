package by.epamtc.ivangavrilovich.shop.controller.impl;

import by.epamtc.ivangavrilovich.shop.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLanguage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession(true)
                .setAttribute("language", request.getParameter("language"));
//        System.out.println(request.getSession().getAttribute("language"));
        response.sendRedirect(request.getParameter("address"));
//        request.getRequestDispatcher(
//                request.getParameter("address"))
//                        .forward(request, response);
    }
}

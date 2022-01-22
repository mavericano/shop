package by.epamtc.ivangavrilovich.shop.controller.impl;

import by.epamtc.ivangavrilovich.shop.controller.Command;
import by.epamtc.ivangavrilovich.shop.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class ChangeLanguage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(true);
        session.setAttribute("language", request.getParameter("language"));

        String lang = (request.getParameter("language")).substring(0, 2);
        String country = (request.getParameter("language")).substring(3, 5);
        Locale locale = new Locale(lang, country);

        ServiceProvider.getInstance().getUtilityServiceImpl().updateLocaleInSession(session, locale);

        String address = request.getParameter("address").substring(1);
        address = address.substring(address.indexOf("/"));
        request.getRequestDispatcher(
                address)
                        .forward(request, response);
    }
}

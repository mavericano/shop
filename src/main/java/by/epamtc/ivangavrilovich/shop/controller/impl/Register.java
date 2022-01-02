package by.epamtc.ivangavrilovich.shop.controller.impl;

import by.epamtc.ivangavrilovich.shop.controller.Command;
import by.epamtc.ivangavrilovich.shop.service.exceptions.AlreadyRegisteredException;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;
import by.epamtc.ivangavrilovich.shop.service.ServiceProvider;
import by.epamtc.ivangavrilovich.shop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Register implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("message", "");
        UserService service = ServiceProvider.getInstance().getUserServiceImpl();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        //System.out.println(password);
        String defaultAddress = request.getParameter("defaultAddress");
        int role = 1;
                //(int) request.getParameter("role");
        boolean banned = false;
                //(boolean) request.getParameter("banned");
        try {
            service.register(email, password, defaultAddress, role, banned);
        } catch (AlreadyRegisteredException e) {
            request.setAttribute("message", "User with such email already registered");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } catch (ServiceException e) {
            //TODO add redirection to err page
        }
        response.sendRedirect("main.jsp");
    }
}

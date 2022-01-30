package by.epamtc.ivangavrilovich.shop.controller.impl;

import by.epamtc.ivangavrilovich.shop.controller.Command;
import by.epamtc.ivangavrilovich.shop.service.exceptions.AlreadyRegisteredException;
import by.epamtc.ivangavrilovich.shop.service.exceptions.InvalidInputsException;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;
import by.epamtc.ivangavrilovich.shop.service.ServiceProvider;
import by.epamtc.ivangavrilovich.shop.service.interfaces.UserService;

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
        String firstPassword = request.getParameter("password");
        String secondPassword = request.getParameter("second-password");
        String number = request.getParameter("number");
        int role = 1;
                //(int) request.getParameter("role");
        boolean banned = false;
                //(boolean) request.getParameter("banned");
        try {
            service.register(email, firstPassword, secondPassword, number, role, banned);
            response.sendRedirect(request.getContextPath() + "/pages/controller?command=VIEW_HOME_PAGE");
        } catch (AlreadyRegisteredException e) {
            request.setAttribute("message", request.getSession(true).getAttribute("duplicateEmailLabel"));
            request.getRequestDispatcher("/pages/controller?command=VIEW_REGISTER").forward(request, response);
        } catch (InvalidInputsException e) {
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/pages/controller?command=VIEW_REGISTER").forward(request, response);
        } catch (ServiceException e) {
            response.sendRedirect(request.getContextPath() + "/pages/serverException.jsp");
        }
    }
}

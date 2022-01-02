package by.epamtc.ivangavrilovich.shop.controller.impl;

import by.epamtc.ivangavrilovich.shop.bean.User;
import by.epamtc.ivangavrilovich.shop.controller.Command;
import by.epamtc.ivangavrilovich.shop.service.exceptions.AlreadyRegisteredException;
import by.epamtc.ivangavrilovich.shop.service.exceptions.InvalidPasswordException;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;
import by.epamtc.ivangavrilovich.shop.service.ServiceProvider;
import by.epamtc.ivangavrilovich.shop.service.UserService;
import by.epamtc.ivangavrilovich.shop.service.exceptions.UserNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignIn implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        UserService service = ServiceProvider.getInstance().getUserServiceImpl();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User currentUser = null;

        try {
            currentUser = service.login(email, password);
        } catch (UserNotFoundException e) {
            request.setAttribute("message", "No user with such email");
            request.getRequestDispatcher("sign_in.jsp").forward(request, response);
        } catch (InvalidPasswordException e) {
            request.setAttribute("message", "Invalid password");
            request.getRequestDispatcher("sign_in.jsp").forward(request, response);
        } catch (ServiceException e) {
            //TODO add redirection to err page
        }
        //TODO totally fix
        session.setAttribute("currentUser", currentUser);
        response.sendRedirect("main.jsp");
    }
}

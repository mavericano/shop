package by.epamtc.ivangavrilovich.shop.controller.impl;

import by.epamtc.ivangavrilovich.shop.bean.User;
import by.epamtc.ivangavrilovich.shop.controller.Command;
import by.epamtc.ivangavrilovich.shop.service.exceptions.AlreadyRegisteredException;
import by.epamtc.ivangavrilovich.shop.service.exceptions.InvalidPasswordException;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;
import by.epamtc.ivangavrilovich.shop.service.ServiceProvider;
import by.epamtc.ivangavrilovich.shop.service.UserService;
import by.epamtc.ivangavrilovich.shop.service.exceptions.UserNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignIn implements Command {
    private final static Logger logger = LogManager.getLogger();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        UserService service = ServiceProvider.getInstance().getUserServiceImpl();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User currentUser = null;

        try {
            currentUser = service.login(email, password);
            session.setAttribute("user", currentUser);
            request.getRequestDispatcher("/pages/controller?command=VIEW_HOME_PAGE").forward(request, response);
        } catch (UserNotFoundException e) {
            request.setAttribute("message", "No user with such email");
            request.getRequestDispatcher("sign_in.jsp").forward(request, response);
        } catch (InvalidPasswordException e) {
            request.setAttribute("message", "Invalid password");
            request.getRequestDispatcher("sign_in.jsp").forward(request, response);
        } catch (ServiceException e) {
            logger.error("Error while signing in");
            response.sendRedirect(request.getContextPath() + "/pages/serverException.jsp");
        }
    }
}

package by.epamtc.ivangavrilovich.shop.controller.impl;

import by.epamtc.ivangavrilovich.shop.bean.User;
import by.epamtc.ivangavrilovich.shop.controller.Command;
import by.epamtc.ivangavrilovich.shop.service.exceptions.InvalidPasswordException;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;
import by.epamtc.ivangavrilovich.shop.service.ServiceProvider;
import by.epamtc.ivangavrilovich.shop.service.interfaces.UserService;
import by.epamtc.ivangavrilovich.shop.service.exceptions.UserNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
        User currentUser;
//        System.out.println(email);
//        System.out.println(password);
        try {
            currentUser = service.login(email, password);
            session.setAttribute("user", currentUser);
            if (request.getParameterValues("rememberMe") != null) {
                handleCookies(request, response);
            }
            response.sendRedirect(request.getContextPath() + "/pages/controller?command=VIEW_HOME_PAGE");
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

    private void handleCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        boolean alreadyRemembered = false;
        for (Cookie c : cookies) {
            if (c.getName().equals("rememberMe")) {
                alreadyRemembered = true;
            }
        }

        if(!alreadyRemembered) {
            response.addCookie(new Cookie("rememberMe", request.getParameter("email")));
        }
    }
}

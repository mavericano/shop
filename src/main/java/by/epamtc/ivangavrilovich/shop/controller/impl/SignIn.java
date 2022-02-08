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
import java.util.Arrays;
import java.util.List;

public class SignIn implements Command {
    private final static Logger logger = LogManager.getLogger();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        UserService service = ServiceProvider.getInstance().getUserServiceImpl();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User currentUser;
        try {
            currentUser = service.login(email, password);
            session.setAttribute("user", currentUser);
            if (request.getParameterValues("rememberMe") != null) {
                handleCookies(request, response, currentUser);
            }
            response.sendRedirect(request.getContextPath() + "/pages/controller?command=VIEW_HOME_PAGE");
        } catch (UserNotFoundException e) {
            request.setAttribute("message", request.getSession(true).getAttribute("emailNotFoundLabel"));
            request.getRequestDispatcher("/pages/controller?command=VIEW_SIGN_IN").forward(request, response);
        } catch (InvalidPasswordException e) {
            request.setAttribute("message", request.getSession(true).getAttribute("invalidPasswordLabel"));
            request.getRequestDispatcher("/pages/controller?command=VIEW_SIGN_IN").forward(request, response);
        } catch (ServiceException e) {
            logger.error("Error while signing in");
            response.sendRedirect(request.getContextPath() + "/pages/serverException.jsp");
        }
    }

    @Override
    public List<Integer> getAppropriateRoles() {
        return Arrays.asList(1, 2, 3, 4);
    }

    private void handleCookies(HttpServletRequest request, HttpServletResponse response, User currentUser) {
        Cookie[] cookies = request.getCookies();
        boolean alreadyRemembered = false;
        for (Cookie c : cookies) {
            if (c.getName().equals("rememberMeEmail")) {
                alreadyRemembered = true;
                c.setValue(currentUser.getEmail());
                response.addCookie(c);
            }
            if (c.getName().equals("rememberMeHash")) {
                alreadyRemembered = true;
                c.setValue(ServiceProvider.getInstance().getUtilityServiceImpl().cropSalt(currentUser.getPassword()));
                response.addCookie(c);
            }
        }

        if(!alreadyRemembered) {
            Cookie c = new Cookie("rememberMeEmail", request.getParameter("email"));
            c.setMaxAge(365*24*60*60);
            response.addCookie(c);
            c = new Cookie("rememberMeHash", ServiceProvider.getInstance().getUtilityServiceImpl().cropSalt(currentUser.getPassword()));
            c.setMaxAge(365*24*60*60);
            response.addCookie(c);
        }

    }
}

package by.epamtc.ivangavrilovich.shop.controller.filters;

import by.epamtc.ivangavrilovich.shop.bean.User;
import by.epamtc.ivangavrilovich.shop.service.ServiceProvider;
import by.epamtc.ivangavrilovich.shop.service.exceptions.InvalidPasswordException;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;
import by.epamtc.ivangavrilovich.shop.service.exceptions.UserNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class SessionFilter implements Filter {
    private final static Logger logger = LogManager.getLogger();
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(true);
        Locale locale;
        if (!(boolean)session.getAttribute("language_chosen")) {
            System.out.println("detecting");
            //TODO replace with props
            List<Locale> supportedLocales = new ArrayList<>();
            supportedLocales.add(Locale.US);
            supportedLocales.add(new Locale("ru", "RU"));
            boolean foundSupported = false;

            locale = null;
            Enumeration<Locale> locales = req.getLocales();
            while (locales.hasMoreElements() && (!foundSupported)) {
                locale = locales.nextElement();
                if (supportedLocales.contains(locale)) {
                    foundSupported = true;
                }
            }

            if (!foundSupported) {
                locale = Locale.US;
            }

            session.setAttribute("language", locale.toString());
            session.setAttribute("language_chosen", true);
            ServiceProvider.getInstance().getUtilityServiceImpl().updateLocaleInSession(session, locale);
        }

        User user = (User) req.getSession(true).getAttribute("user");
        boolean triedToLogIn = req.getSession(true).getAttribute("triedToLogIn") != null;
        Cookie[] cookies = req.getCookies();
        String email = null;
        String hash = null;
        for (Cookie c : cookies) {
            if (c.getName().equals("rememberMeEmail")) {
                email = c.getValue();
            }
            if (c.getName().equals("rememberMeHash")) {
                hash = c.getValue();
            }
        }
        if (!triedToLogIn && user.getRole() == 4 && email != null && hash != null) {
            req.getSession(true).setAttribute("triedToLogIn", true);
            try {
                user = ServiceProvider.getInstance().getUserServiceImpl().loginFromCookies(email, hash);
                req.getSession(true).setAttribute("user", user);
            } catch (ServiceException | UserNotFoundException | InvalidPasswordException e) {
                logger.error("Error while logging in from cookies", e);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

}

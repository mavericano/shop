package by.epamtc.ivangavrilovich.shop.controller.filters;

import by.epamtc.ivangavrilovich.shop.service.ServiceProvider;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class SessionFilter implements Filter {
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

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

}

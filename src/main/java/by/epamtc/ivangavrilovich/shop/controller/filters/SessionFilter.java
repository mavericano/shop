package by.epamtc.ivangavrilovich.shop.controller.filters;

import by.epamtc.ivangavrilovich.shop.bean.User;

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
            //TODO replace with props
            List<Locale> supportedLocales = new ArrayList<>();
            supportedLocales.add(Locale.US);
            supportedLocales.add(new Locale("ru", "RU"));
            //supportedLocales.add(new Locale("en", "GB"));
            boolean foundSupported = false;

            locale = null;
            Enumeration<Locale> locales = req.getLocales();
            while (locales.hasMoreElements() && (!foundSupported)) {
                locale = locales.nextElement();
                if (supportedLocales.contains(locale)) {
                    session.setAttribute("language", locale);
                    foundSupported = true;
                }
            }

            if (!foundSupported) {
                locale = Locale.US;
            }

            System.out.println(locale);

            //TODO add locale detection
            //this.getClass().getResource("text.properties");
        } else {
            String lang = ((String)session.getAttribute("language")).substring(0, 2);
            String country = ((String)session.getAttribute("language")).substring(3, 5);
            locale = new Locale(lang, country);
        }

        ResourceBundle bundle = ResourceBundle.getBundle("bundles/text", locale);

        for (Enumeration<String> e = bundle.getKeys(); e.hasMoreElements();) {
            String key = e.nextElement();
            String s = bundle.getString(key);
            session.setAttribute(key, s);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

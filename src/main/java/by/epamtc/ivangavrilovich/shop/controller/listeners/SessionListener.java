package by.epamtc.ivangavrilovich.shop.controller.listeners;

import by.epamtc.ivangavrilovich.shop.bean.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();

        User user = new User();
        user.setRole(4); // guest role id
        session.setAttribute("user", user);

        //session.setAttribute("language", "russian");

        session.setAttribute("language_chosen", false);

        System.out.println("Session listener");
//        Locale locale;
//        if ("russian".equals(session.getAttribute("language"))) {
//            locale = new Locale("ru", "RU");
//        } else {
//            locale = new Locale("en", "US");
//        }
//        //TODO add locale detection
//        ResourceBundle bundle = ResourceBundle.getBundle("bundle", locale);
//
//        for (Enumeration<String> e = bundle.getKeys(); e.hasMoreElements();) {
//            String key = e.nextElement();
//            String s = bundle.getString(key);
//            session.setAttribute(key, s);
//        }
    }
}

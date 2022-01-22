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
        session.setAttribute("language_chosen", false);
    }
}

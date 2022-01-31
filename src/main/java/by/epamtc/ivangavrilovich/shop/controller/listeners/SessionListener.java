package by.epamtc.ivangavrilovich.shop.controller.listeners;

import by.epamtc.ivangavrilovich.shop.bean.User;
import by.epamtc.ivangavrilovich.shop.service.ServiceProvider;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    private String lastAction = "VIEW_HOME_PAGE";
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        session.setAttribute("lastAction", lastAction);

        User user = new User();
        user.setRole(4); // guest role id
        session.setAttribute("user", user);
        session.setAttribute("language_chosen", false);

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        lastAction = (String)se.getSession().getAttribute("lastAction");
    }
}

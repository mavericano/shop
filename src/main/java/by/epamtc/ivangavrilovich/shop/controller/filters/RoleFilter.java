package by.epamtc.ivangavrilovich.shop.controller.filters;

import by.epamtc.ivangavrilovich.shop.bean.User;
import by.epamtc.ivangavrilovich.shop.controller.Command;
import by.epamtc.ivangavrilovich.shop.controller.CommandProvider;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RoleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String command = req.getParameter("command");

        if (command != null) {
            int currentRole = ( (User) req.getSession(true).getAttribute("user")).getRole();
            Command commandImpl = CommandProvider.getInstance().provideCommand(command);
            List<Integer> appropriateRoles = commandImpl.getAppropriateRoles();
            if (!appropriateRoles.contains(currentRole)) {
                req.getRequestDispatcher("/pages/controller?command=VIEW_SIGN_IN").forward(request, response);
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

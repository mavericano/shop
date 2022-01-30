package by.epamtc.ivangavrilovich.shop.controller.impl;

import by.epamtc.ivangavrilovich.shop.bean.User;
import by.epamtc.ivangavrilovich.shop.controller.Command;
import by.epamtc.ivangavrilovich.shop.service.ServiceProvider;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;
import by.epamtc.ivangavrilovich.shop.service.interfaces.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubmitUserChanges implements Command {
    private final static Logger logger = LogManager.getLogger();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserService service = ServiceProvider.getInstance().getUserServiceImpl();
        List<String> bannedNew = request.getParameterValues("banned") == null ?
                new ArrayList<>() :
                Arrays.asList(request.getParameterValues("banned"));
        List<String> deletedNew = request.getParameterValues("deleted") == null ?
                new ArrayList<>() :
                Arrays.asList(request.getParameterValues("deleted"));
        List<String> roleNew = request.getParameterValues("role") == null ?
                new ArrayList<>() :
                Arrays.asList(request.getParameterValues("role"));
        List<User> users = (List<User>) request.getSession(true).getAttribute("users");
        request.getSession(true).removeAttribute("users");

        try {
            service.submitAdminChanges(roleNew, bannedNew, deletedNew, users);
            response.sendRedirect(request.getContextPath() + "/pages/controller?command=VIEW_ADMIN_SCREEN");
        } catch (ServiceException e) {
            logger.error("Error while submitting user changes", e);
            response.sendRedirect(request.getContextPath() + "/pages/serverException.jsp");
        }
    }
}

package by.epamtc.ivangavrilovich.shop.controller.impl;

import by.epamtc.ivangavrilovich.shop.bean.User;
import by.epamtc.ivangavrilovich.shop.controller.Command;
import by.epamtc.ivangavrilovich.shop.service.exceptions.AlreadyRegisteredException;
import by.epamtc.ivangavrilovich.shop.service.exceptions.InvalidPasswordException;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;
import by.epamtc.ivangavrilovich.shop.service.ServiceProvider;
import by.epamtc.ivangavrilovich.shop.service.UserService;
import by.epamtc.ivangavrilovich.shop.service.exceptions.UserNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignIn implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        UserService service = ServiceProvider.getInstance().getUserServiceImpl();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User currentUser = null;

        try {
            currentUser = service.login(email, password);
        } catch (UserNotFoundException e) {
            //TODO add redirection
            request.setAttribute("message", "user not found");
            System.out.println("net emaila");
        } catch (InvalidPasswordException e) {
            request.setAttribute("message", "invalid password");
            System.out.println("paroli ne sovpali");
        } catch (ServiceException e) {
            //TODO add
        }
        //TODO totally fix
        System.out.println("success");
        //response.sendRedirect("main.jsp");
    }
}

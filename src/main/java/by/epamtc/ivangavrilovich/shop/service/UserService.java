package by.epamtc.ivangavrilovich.shop.service;

import by.epamtc.ivangavrilovich.shop.bean.User;
import by.epamtc.ivangavrilovich.shop.service.exceptions.AlreadyRegisteredException;
import by.epamtc.ivangavrilovich.shop.service.exceptions.InvalidPasswordException;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;
import by.epamtc.ivangavrilovich.shop.service.exceptions.UserNotFoundException;

public interface UserService {
    User logIn(String username, String password);
    boolean register(String email, String password, String defaultAddress, int role, boolean banned) throws ServiceException, AlreadyRegisteredException;

    User login(String email, String password) throws UserNotFoundException, InvalidPasswordException, ServiceException;
}

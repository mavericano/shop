package by.epamtc.ivangavrilovich.shop.service.interfaces;

import by.epamtc.ivangavrilovich.shop.bean.User;
import by.epamtc.ivangavrilovich.shop.service.exceptions.*;

public interface UserService {
    void register(String email, String firstPassword, String secondPassword, String defaultAddress, int role, boolean banned) throws ServiceException, AlreadyRegisteredException, InvalidInputsException;
    User login(String email, String password) throws UserNotFoundException, InvalidPasswordException, ServiceException;
}

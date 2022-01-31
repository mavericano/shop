package by.epamtc.ivangavrilovich.shop.service.interfaces;

import by.epamtc.ivangavrilovich.shop.bean.User;
import by.epamtc.ivangavrilovich.shop.service.exceptions.*;

import java.util.List;

public interface UserService {
    void register(String email, String firstPassword, String secondPassword, String defaultAddress, int role, boolean banned) throws ServiceException, AlreadyRegisteredException, InvalidInputsException;
    User login(String email, String password) throws UserNotFoundException, InvalidPasswordException, ServiceException;

    User loginFromCookies(String email, String hashedPassword) throws UserNotFoundException, InvalidPasswordException, ServiceException;

    List<User> viewPageUsers(int offset, int recsPerPage) throws ServiceException;

    List<User> viewPageUsers(int offset, int recsPerPage, boolean viewDel) throws ServiceException;

    int retrieveNumberOfUsers() throws ServiceException;

    void submitAdminChanges(List<String> roleOld, List<String> bannedOld, List<String> deletedOld, List<User> users) throws ServiceException;
}

package by.epamtc.ivangavrilovich.shop.DAO.interfaces;

import by.epamtc.ivangavrilovich.shop.DAO.exceptions.DAOException;
import by.epamtc.ivangavrilovich.shop.bean.User;

import java.util.List;

public interface UserDAO {
    List<User> viewPageUsers(int offset, int recsPerPage, boolean viewDel) throws DAOException;
    int numberOfUsers() throws DAOException;
    void addUser(User user) throws DAOException;
    void changeRole(int userId, int newRole) throws DAOException;
    void changeBannedStatus(int userId, boolean newStatus) throws DAOException;
    void changeDelStatus(int userId, boolean newStatus) throws DAOException;
    User readUserByEmail(String email) throws DAOException;
    User readUserById(int userId) throws DAOException;
    boolean hasUserWithEmail(String email) throws DAOException;
}

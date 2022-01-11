package by.epamtc.ivangavrilovich.shop.DAO.interfaces;

import by.epamtc.ivangavrilovich.shop.DAO.DAOException;
import by.epamtc.ivangavrilovich.shop.bean.User;

import java.util.List;

public interface UserDAO {
    void addUser(User user) throws DAOException;
    boolean modifyDelStatus(User user, boolean newStatus) throws DAOException;
    boolean updateUser(User user) throws DAOException;
    List<User> readUsers() throws DAOException;
    User readUserByEmail(String email) throws DAOException;
    boolean hasUserWithEmail(String email) throws DAOException;
}

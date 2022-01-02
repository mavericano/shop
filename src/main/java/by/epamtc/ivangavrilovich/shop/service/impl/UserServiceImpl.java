package by.epamtc.ivangavrilovich.shop.service.impl;

import by.epamtc.ivangavrilovich.shop.DAO.DAOException;
import by.epamtc.ivangavrilovich.shop.DAO.DAOProvider;
import by.epamtc.ivangavrilovich.shop.DAO.interfaces.UserDAO;
import by.epamtc.ivangavrilovich.shop.bean.User;
import by.epamtc.ivangavrilovich.shop.service.exceptions.AlreadyRegisteredException;
import by.epamtc.ivangavrilovich.shop.service.exceptions.InvalidPasswordException;
import by.epamtc.ivangavrilovich.shop.service.exceptions.UserNotFoundException;
import by.epamtc.ivangavrilovich.shop.service.exceptions.ServiceException;
import by.epamtc.ivangavrilovich.shop.service.UserService;
import by.epamtc.ivangavrilovich.shop.service.hashing.Hasher;

public class UserServiceImpl implements UserService {
    @Override
    public User logIn(String username, String password) {
        return null;
    }

    @Override
    public boolean register(String email, String password, String defaultAddress, int role, boolean banned) throws ServiceException, AlreadyRegisteredException {
        UserDAO dao = DAOProvider.getInstance().getUserDAOImpl();
        try {
            if (dao.hasUserWithEmail(email)) throw new AlreadyRegisteredException("for email " + email);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }

        String salt = Hasher.generateSalt();
        String hash = Hasher.hashPassword(password, salt);
        String storedPassword = Hasher.generateStoredPassword(hash, salt);
        User user = new User(email, storedPassword, defaultAddress, role, banned);
        try {
            dao.addUser(user);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public User login(String email, String password) throws UserNotFoundException, InvalidPasswordException, ServiceException {
        UserDAO dao = DAOProvider.getInstance().getUserDAOImpl();
        User result = null;
        try {
            result = dao.readUserByEmail(email);
            if (result == null) {
                throw new UserNotFoundException("No user with such email");
            }
        } catch (DAOException e) {
            throw new ServiceException("Error while fetching user by email", e);
        }
        String salt = result.getPassword().substring(0, 15);
        String passwordHash = Hasher.hashPassword(password, salt);
        String storedHash = result.getPassword().substring(16);
        if (!passwordHash.equals(storedHash)) throw new InvalidPasswordException("Invalid password");
        return result;
    }
}

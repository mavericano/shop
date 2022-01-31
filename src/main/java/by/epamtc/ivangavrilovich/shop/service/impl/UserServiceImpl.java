package by.epamtc.ivangavrilovich.shop.service.impl;

import by.epamtc.ivangavrilovich.shop.DAO.exceptions.DAOException;
import by.epamtc.ivangavrilovich.shop.DAO.DAOProvider;
import by.epamtc.ivangavrilovich.shop.DAO.interfaces.UserDAO;
import by.epamtc.ivangavrilovich.shop.bean.User;
import by.epamtc.ivangavrilovich.shop.service.ServiceProvider;
import by.epamtc.ivangavrilovich.shop.service.exceptions.*;
import by.epamtc.ivangavrilovich.shop.service.interfaces.UserService;
import by.epamtc.ivangavrilovich.shop.service.hashing.Hasher;
import by.epamtc.ivangavrilovich.shop.service.interfaces.ValidationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private final static Logger logger = LogManager.getLogger();

    @Override
    public void register(String email, String firstPassword, String secondPassword, String phoneNumber, int role, boolean banned) throws ServiceException, AlreadyRegisteredException, InvalidInputsException {
        ValidationService validator = ServiceProvider.getInstance().getValidationServiceImpl();
        if (!validator.validatePhoneNumber(phoneNumber)) {
            throw new InvalidInputsException("Invalid phone number");
        }
        if (!validator.validateEqualPasswords(firstPassword, secondPassword)) {
            throw new InvalidInputsException("Passwords are not equal");
        }
        if (!validator.validatePassword(firstPassword)) {
            throw new InvalidInputsException("Invalid password");
        }

        UserDAO dao = DAOProvider.getInstance().getUserDAOImpl();
        try {
            if (dao.hasUserWithEmail(email)) throw new AlreadyRegisteredException("for email " + email);
        } catch (DAOException e) {
            logger.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }

        String salt = Hasher.generateSalt();
        String hash = Hasher.hashPassword(firstPassword, salt);
        String storedPassword = Hasher.generateStoredPassword(hash, salt);
        User user = new User(email, storedPassword, phoneNumber, role, banned);
        try {
            dao.addUser(user);
        } catch (DAOException e) {
            logger.error(String.format("Error while registering user for email %s and password %s", email, firstPassword), e);
            throw new ServiceException(String.format("Error while registering user for email %s and password %s", email, firstPassword), e);
        }
    }

    @Override
    public User login(String email, String password) throws UserNotFoundException, InvalidPasswordException, ServiceException {
        UserDAO dao = DAOProvider.getInstance().getUserDAOImpl();
        User result;
        try {
            result = dao.readUserByEmail(email);
            if (result == null) {
                throw new UserNotFoundException("No user with such email");
            }
        } catch (DAOException e) {
            logger.error("Error while fetching user by email", e);
            throw new ServiceException("Error while fetching user by email", e);
        }
        int delimPos = result.getPassword().lastIndexOf(":");
        String salt = result.getPassword().substring(0, delimPos);
        String passwordHash = Hasher.hashPassword(password, salt);
        String storedHash = result.getPassword().substring(delimPos + 1);
        if (!passwordHash.equals(storedHash)) throw new InvalidPasswordException("Invalid password");
        return result;
    }

    @Override
    public User loginFromCookies(String email, String hashedPassword) throws UserNotFoundException, InvalidPasswordException, ServiceException {
        UserDAO dao = DAOProvider.getInstance().getUserDAOImpl();
        User result;
        try {
            result = dao.readUserByEmail(email);
            if (result == null) {
                throw new UserNotFoundException("No user with such email");
            }
        } catch (DAOException e) {
            logger.error("Error while fetching user by email", e);
            throw new ServiceException("Error while fetching user by email", e);
        }
        int delimPos = result.getPassword().lastIndexOf(":");
        String storedHash = result.getPassword().substring(delimPos + 1);
        if (!hashedPassword.equals(storedHash)) throw new InvalidPasswordException("Invalid password");
        return result;
    }

    @Override
    public List<User> viewPageUsers(int offset, int recsPerPage) throws ServiceException {
        return viewPageUsers(offset, recsPerPage, false);
    }

    @Override
    public List<User> viewPageUsers(int offset, int recsPerPage, boolean viewDel) throws ServiceException {
        UserDAO dao = DAOProvider.getInstance().getUserDAOImpl();
        List<User> thisPageUsers;

        try {
            thisPageUsers = dao.viewPageUsers(offset, recsPerPage, viewDel);
        } catch (DAOException e) {
            logger.error("Error while retrieving view page users in wrapping method", e);
            throw new ServiceException("Error while retrieving view page users in wrapping method", e);
        }
        return thisPageUsers;
    }

    @Override
    public int retrieveNumberOfUsers() throws ServiceException {
        UserDAO dao = DAOProvider.getInstance().getUserDAOImpl();
        int numberOfUsers;

        try {
            numberOfUsers = dao.numberOfUsers();
        } catch (DAOException e) {
            logger.error("Error while retrieving number of users in wrapping method", e);
            throw new ServiceException("Error while retrieving number of users in wrapping method", e);
        }

        return numberOfUsers;
    }

    private Map<Integer, Integer> rolesToMap(List<String> roles) {
        Map<Integer, Integer> result = new HashMap<>();
        int spaceIndex;
        for (String pair : roles) {
            spaceIndex = pair.indexOf(" ");
            result.put(Integer.parseInt(pair.substring(0, spaceIndex)), Integer.parseInt(pair.substring(spaceIndex + 1)));
        }

        return result;
    }

    @Override
    public void submitAdminChanges(List<String> roleNew, List<String> bannedNew, List<String> deletedNew, List<User> users) throws ServiceException {
        UserDAO dao = DAOProvider.getInstance().getUserDAOImpl();
        boolean bannedNewBool;
        boolean deletedNewBool;
        Map<Integer, Integer> roleMap = rolesToMap(roleNew);

        try {
            for (User user : users) {
                bannedNewBool = bannedNew.contains(String.valueOf(user.getUserId()));
                deletedNewBool = deletedNew.contains(String.valueOf(user.getUserId()));

                if (user.isBanned() != bannedNewBool) {
                    dao.changeBannedStatus(user.getUserId(), bannedNewBool);
                }

                if (user.isDeleted() != deletedNewBool) {
                    dao.changeDelStatus(user.getUserId(), deletedNewBool);
                }

                if (user.getRole() != roleMap.get(user.getUserId())) {
                    dao.changeRole(user.getUserId(), roleMap.get(user.getUserId()));
                }
            }
        } catch (DAOException e) {
            logger.error("Error while submitting admin changes", e);
            throw new ServiceException("Error while submitting admin changes", e);
        }
    }
}

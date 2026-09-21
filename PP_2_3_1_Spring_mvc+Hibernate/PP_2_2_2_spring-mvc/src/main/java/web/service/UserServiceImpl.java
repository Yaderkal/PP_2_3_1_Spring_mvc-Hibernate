package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.DAO.UserDAO;
import web.model.User;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void createUsersTable() {
        try {
            userDAO.createUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropUsersTable()  {
        try {
            userDAO.dropUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Transactional
    @Override
    public void saveUser(String name, String middleName, String surName, String mail) {
        try {
            userDAO.saveUser(name, middleName, surName, mail);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Transactional
    @Override
    public void removeUserById(long id) {
        userDAO.removeUserById(id);
    }
    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers()  {
    try {
        return userDAO.getAllUsers();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
    @Transactional
    @Override
    public void cleanUsersTable() {
        try {
            userDAO.cleanUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Transactional
    @Override
    public void updateUser(Long id, String name, String middleName, String surName, String mail) throws SQLException {
        userDAO.updateUser(id, name, middleName, surName, mail);
    }
}
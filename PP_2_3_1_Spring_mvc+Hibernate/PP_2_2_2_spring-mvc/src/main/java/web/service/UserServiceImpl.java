package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.DAO.UserDAO;
import web.model.User;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void createUsersTable() {
        userDAO.createUsersTable();
    }

    @Override
    public void dropUsersTable() throws SQLException {
        userDAO.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String middleName, String surName, String mail) throws SQLException {
        userDAO.saveUser(name, middleName, surName, mail);
    }

    @Override
    public void removeUserById(long id) {
        userDAO.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        userDAO.cleanUsersTable();
    }
}
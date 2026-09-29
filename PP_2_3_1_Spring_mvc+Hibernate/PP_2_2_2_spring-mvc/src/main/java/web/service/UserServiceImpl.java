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

    @Transactional
    @Override
    public void createUsersTable() {
        try {
            userDAO.createUsersTable();
        } catch (SQLException e) {
            System.out.println("ошибка при создании таблицы");
            throw new RuntimeException(e);
        }
    }
    @Transactional
    @Override
    public void dropUsersTable()  {
        try {
            userDAO.dropUsersTable();
        } catch (SQLException e) {
            System.out.println("ошибка при удалении таблицы");
            throw new RuntimeException(e);
        }
    }
    @Transactional
    @Override
    public void saveUser(String name, String middleName, String surName, String mail) {
        try {
            userDAO.saveUser(name, middleName, surName, mail);
        } catch (SQLException e) {
            System.out.println("ошибка при добавлении пользователя");
            throw new RuntimeException(e);
        }
    }
    @Transactional(readOnly = true)
    @Override
    public User getUserById(Long id) {
        User user = userDAO.getUserById(id);
        if (user == null) {
            throw new RuntimeException("Пользователь с id=" + id + " не найден");
        }
        return user;
    }

    @Transactional
    @Override
    public void removeUserById(long id) {
        User user = userDAO.getUserById(id);
        if (user == null) {
            throw new RuntimeException("Пользователь с id=" + id + " не найден");
        }
        userDAO.removeUserById(id);
    }
    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers()  {
        try {
            return userDAO.getAllUsers();
        } catch (SQLException e) {
            System.out.println("Ошибка при получении всех пользователей");
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
    public void updateUser(Long id, String name, String middleName, String surName, String mail) {
        try {
            userDAO.updateUser(id, name, middleName, surName, mail);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
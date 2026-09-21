package web.service;

import web.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    void createUsersTable();

    void dropUsersTable();

    void saveUser(String name, String middleName, String surname, String mail);
    User getUserById(Long id);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
    void updateUser(Long id, String name, String middleName, String surName, String mail) throws SQLException;
}

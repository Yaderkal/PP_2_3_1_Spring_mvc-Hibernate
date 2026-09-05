package web.DAO;

import web.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    void createUsersTable();

    void dropUsersTable() throws SQLException;

    void saveUser(String name, String middleName, String surName, String mail) throws SQLException;

    void removeUserById(long id);

    List<User> getAllUsers() throws SQLException;

    void cleanUsersTable();
}
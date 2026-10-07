package web.DAO;

import web.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    void saveUser(String name, String middleName, String surName, String mail) throws SQLException;

    User getUserById(long id);

    void removeUserById(long id);

    List<User> getAllUsers() throws SQLException;

    void updateUser(Long id, String name, String middleName, String surName, String mail) throws SQLException;
}
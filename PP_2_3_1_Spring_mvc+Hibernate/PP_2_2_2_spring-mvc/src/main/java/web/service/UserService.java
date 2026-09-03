package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import java.sql.SQLException;
import java.util.List;

public interface UserService {
    void createUsersTable();
    void dropUsersTable() throws SQLException;
    void saveUser(String name, String middleName, String surname, String mail) throws SQLException;
    void removeUserById(long id);
    List<User> getAllUsers() throws SQLException;
    void cleanUsersTable();
}

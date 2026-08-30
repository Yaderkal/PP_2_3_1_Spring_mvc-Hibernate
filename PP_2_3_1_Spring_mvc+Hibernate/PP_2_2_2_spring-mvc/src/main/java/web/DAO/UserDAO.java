package web.DAO;

import org.springframework.context.annotation.Bean;
import web.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    void createUsersTable();

    void dropUsersTable() throws SQLException;

    void saveUser(String name, String lastName, byte age) throws SQLException;

    void removeUserById(long id);

    List<User> getAllUsers() throws SQLException;

    void cleanUsersTable();
}

package web.service;

import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {


    void saveUser(String name, String middleName, String surname, String mail) throws SQLException;

    @Transactional(readOnly = true)
    User getUserById(Long id);

    void removeUserById(long id);

    List<User> getAllUsers() throws SQLException;

}

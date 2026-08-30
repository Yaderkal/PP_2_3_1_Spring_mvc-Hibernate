package web.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;
@Repository
public class UserDAOImpl implements UserDAO {
    private final EntityManager entityManager;
    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void createUsersTable() {

    }

    @Override
    public void dropUsersTable() throws SQLException {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        return List.of();
    }

    @Override
    public void cleanUsersTable() {

    }
}

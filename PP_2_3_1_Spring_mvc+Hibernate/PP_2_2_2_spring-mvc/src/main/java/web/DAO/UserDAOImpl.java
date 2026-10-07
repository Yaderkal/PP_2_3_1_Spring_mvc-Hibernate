package web.DAO;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(String name, String middleName, String surName, String mail) {
        User user = new User(name, middleName, surName, mail);
        entityManager.persist(user);
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void removeUserById(long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        return entityManager.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    @Override
    public void updateUser(Long id, String name, String middleName, String surName, String mail) {
        User user = entityManager.find(User.class, id);
        if (user == null) {
            throw new EntityNotFoundException("Пользователь с id=" + id + " не найден");
        }
        user.setName(name);
        user.setMiddleName(middleName);
        user.setSurName(surName);
        user.setMail(mail);
        entityManager.persist(user);
    }
}
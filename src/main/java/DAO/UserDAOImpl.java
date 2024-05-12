package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.users.Users;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO{

    @PersistenceContext
    private EntityManager entityManager;

    public Users createUser(Users user) {
        entityManager.persist(user);
        return user;
    }
}

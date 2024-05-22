package com.example.gestao_fluxos_trabalho.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import com.example.gestao_fluxos_trabalho.model.users.Users;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO{

    @PersistenceContext
    private EntityManager entityManager;

    public Users createUser(Users user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public Users findById(Long id) {
        return entityManager.find(Users.class, id);
    }

    @Override
    public List<Users> findAll() {
        return entityManager.createQuery("SELECT u FROM Users u", Users.class).getResultList();
    }

    @Override
    public Users findByEmailAndPassword(String email, String password) {
        try {
            TypedQuery<Users> query = entityManager.createQuery(
                    "SELECT u FROM Users u WHERE u.email = :email AND u.password = :password", Users.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (Exception e) {
            return null; // or handle the exception as needed
        }
    }
}

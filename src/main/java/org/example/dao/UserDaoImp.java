package org.example.dao;

import org.example.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u from User u", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public void removeUser(Long id) {
        entityManager.createQuery("DELETE FROM User WHERE :id = id").setParameter("id", id).executeUpdate();
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }
}


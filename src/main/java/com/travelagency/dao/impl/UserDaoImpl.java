package com.travelagency.dao.impl;

import com.travelagency.dao.UserDao;
import com.travelagency.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public User getUserById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;
    }

    @Override
    public User createUser(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        User user = (User) entityManager
                .createNativeQuery("SELECT * FROM tb_users WHERE email= :email", User.class)
                .setParameter("email", email).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;
    }

    @Override
    public void deleteUserById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager
                .createNativeQuery("DELETE FROM tb_users WHERE id= :id", User.class)
                .setParameter("id", id);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

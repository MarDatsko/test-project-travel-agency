package com.travelagency.dao.impl;

import com.travelagency.dao.UserDao;
import com.travelagency.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

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

    @Override
    public List<String> getListCountriesWhereWasUser(Long userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<String> listCountries = entityManager
                .createNativeQuery(" SELECT DISTINCT country_name FROM travel.tb_countries " +
                        " LEFT JOIN travel.tb_hotels ON travel.tb_countries.id = travel.tb_hotels.country_id " +
                        " LEFT JOIN travel.tb_rooms ON travel.tb_hotels.id = travel.tb_rooms.hotel_id " +
                        " LEFT JOIN travel.tb_orders ON travel.tb_rooms.id = travel.tb_orders.room_id " +
                        " WHERE user_id = :userId ", String.class)
                .setParameter("userId", userId)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return listCountries;
    }

    @Override
    public List<String> getListVisasWhichHasUser(Long userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<String> listVisas = entityManager
                .createNativeQuery(" SELECT DISTINCT visa_name FROM travel.tb_countries " +
                        " LEFT JOIN travel.tb_hotels ON travel.tb_countries.id = travel.tb_hotels.country_id " +
                        " LEFT JOIN travel.tb_rooms ON travel.tb_hotels.id = travel.tb_rooms.hotel_id " +
                        " LEFT JOIN travel.tb_orders ON travel.tb_rooms.id = travel.tb_orders.room_id " +
                        " LEFT JOIN travel.tb_visas ON travel.tb_countries.visa_id = travel.tb_visas.id " +
                        " WHERE user_id = :userId ", String.class)
                .setParameter("userId", userId)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return listVisas;
    }
}

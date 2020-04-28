package com.travelagency.dao.impl;

import com.travelagency.dao.OrderDao;
import com.travelagency.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
public class OrderDaoImpl implements OrderDao {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public OrderDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Order createOrder(Order order) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();
        entityManager.close();
        return order;
    }
}

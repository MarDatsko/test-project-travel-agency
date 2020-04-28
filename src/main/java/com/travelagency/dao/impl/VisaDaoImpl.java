package com.travelagency.dao.impl;

import com.travelagency.dao.VisaDao;
import com.travelagency.entity.Visa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class VisaDaoImpl implements VisaDao {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public VisaDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Visa getVisaById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Visa visa = entityManager
                .find(Visa.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return visa;
    }

    @Override
    public List<Visa> getAllVisas() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Visa> listVisas = entityManager
                .createNativeQuery("SELECT * FROM tb_visas", Visa.class)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return listVisas;
    }

    @Override
    public void deleteVisaById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager
                .createNativeQuery("DELETE FROM tb_visas WHERE id= :id", Visa.class)
                .setParameter("id", id);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Visa createVisa(Visa visa) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(visa);
        entityManager.getTransaction().commit();
        entityManager.close();
        return visa;
    }
}

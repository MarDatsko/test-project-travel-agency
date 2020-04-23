package com.travelagency.dao.impl;

import com.travelagency.dao.CountryDao;
import com.travelagency.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class CountryDaoImpl implements CountryDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Country createCountry(Country country) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(country);
        entityManager.getTransaction().commit();
        entityManager.close();
        return country;
    }

    @Override
    public List<Country> getAllCountries() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Country> listCountries = entityManager
                .createNativeQuery("SELECT * FROM tb_countries", Country.class)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return listCountries;
    }

    @Override
    public Country getCountryById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Country country = entityManager.find(Country.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return country;
    }

    @Override
    public void deleteCountryById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager
                .createNativeQuery("DELETE FROM tb_countries WHERE id= :id", Country.class)
                .setParameter("id", id);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}

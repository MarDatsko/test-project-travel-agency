package com.travelagency.dao.impl;

import com.travelagency.dao.CountryDao;
import com.travelagency.entity.Country;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
@Log4j2
public class CountryDaoImpl implements CountryDao {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public CountryDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

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
    public Country updateCountry(Country country) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery(
                "UPDATE tb_countries SET country_name = :name, visa_id = :visa_id WHERE id = :id",
                Country.class)
                .setParameter("id", country.getId())
                .setParameter("name", country.getName())
                .setParameter("visa_id", country.getVisa().getId())
                .executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        return country;
    }

    @Override
    public List<Country> getAllCountries() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Country> listCountries = null;
        try {
            listCountries = entityManager
                    .createNativeQuery("SELECT * FROM tb_countries", Country.class)
                    .getResultList();
        } catch (NoResultException e) {
            log.warn("List countries not found");
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return listCountries;
    }

    @Override
    public Country getCountryById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Country country = null;
        try {
            country = entityManager
                    .find(Country.class, id);
        } catch (NoResultException e) {
            log.warn("Country not found");
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return country;
    }

    @Override
    public void deleteCountryById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager
                .createNativeQuery("DELETE FROM tb_countries WHERE id= :id")
                .setParameter("id", id).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

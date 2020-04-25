package com.travelagency.dao.impl;

import com.travelagency.dao.HotelDao;
import com.travelagency.entity.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class HotelDaoImpl implements HotelDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Hotel getHotelById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Hotel hotel = entityManager.find(Hotel.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return hotel;
    }

    @Override
    public List<Hotel> getAllHotel() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Hotel> listHotels = entityManager
                .createNativeQuery("SELECT * FROM tb_hotels", Hotel.class)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return listHotels;
    }

    @Override
    public List<Hotel> getAllHotelsByCountryId(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Hotel> listHotels = entityManager
            .createNativeQuery("SELECT * FROM tb_hotels WHERE country_id = :id", Hotel.class)
            .setParameter("id", id)
            .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return listHotels;
    }

    @Override
    public void deleteHotelById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager
                .createNativeQuery("DELETE FROM tb_hotels WHERE id= :id", Hotel.class)
                .setParameter("id", id);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Hotel createHotel(Hotel hotel) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(hotel);
        entityManager.getTransaction().commit();
        entityManager.close();
        return hotel;
    }
}

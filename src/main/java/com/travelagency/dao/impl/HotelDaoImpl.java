package com.travelagency.dao.impl;

import com.travelagency.dao.HotelDao;
import com.travelagency.entity.Hotel;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Repository
@Log4j2
public class HotelDaoImpl implements HotelDao {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public HotelDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Hotel getHotelById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Hotel hotel = null;
        try {
            hotel = entityManager
                    .find(Hotel.class, id);
        } catch (NoResultException e) {
            log.warn("Hotel not found");
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return hotel;
    }

    @Override
    public List<Hotel> getAllHotel() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Hotel> listHotels = null;
        try {
            listHotels = entityManager
                    .createNativeQuery("SELECT * FROM tb_hotels", Hotel.class)
                    .getResultList();
        } catch (NoResultException e) {
            log.warn("List  hotels not found");
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return listHotels;
    }

    @Override
    public List<Hotel> getAllHotelsByCountryId(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Hotel> listHotels = null;
        try {
            listHotels = entityManager
                    .createNativeQuery("SELECT * FROM tb_hotels WHERE country_id = :id", Hotel.class)
                    .setParameter("id", id)
                    .getResultList();
        } catch (NoResultException e) {
            log.warn("List  hotels not found");
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return listHotels;
    }

    @Override
    public void deleteHotelById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager
                .createNativeQuery("DELETE FROM tb_hotels WHERE id= :id")
                .setParameter("id", id).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Hotel createHotel(Hotel hotel) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery(
                "INSERT INTO tb_hotels (hotel_name, country_id) VALUES (:name, :country_id)",
                Hotel.class)
                .setParameter("name", hotel.getName())
                .setParameter("country_id", hotel.getCountry().getId())
                .executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        return hotel;
    }

    @Override
    public Hotel updateHotel(Hotel hotel) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery(
                "UPDATE tb_hotels SET hotel_name = :name WHERE id = :id",
                Hotel.class)
                .setParameter("id", hotel.getId())
                .setParameter("name", hotel.getName())
                .executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        return hotel;
    }

    @Override
    public List<Hotel> getAllFreeHotelOnCertainPeriod(Long id, LocalDate firstDate, LocalDate secondDate) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Hotel> listHotels = null;
        try {
            listHotels = entityManager
                    .createNativeQuery(" SELECT DISTINCT travel.tb_hotels.id, travel.tb_hotels.hotel_name," +
                            " travel.tb_hotels.country_id FROM travel.tb_hotels " +
                            " LEFT JOIN travel.tb_rooms ON travel.tb_hotels.id = travel.tb_rooms.hotel_id " +
                            " LEFT JOIN travel.tb_orders ON travel.tb_rooms.id = travel.tb_orders.room_id " +
                            " WHERE travel.tb_hotels.country_id = :countryId AND " +
                            " (((CAST(:firstDate AS DATE) BETWEEN CAST(endBooking AS DATE) AND CAST(startBooking AS DATE)) " +
                            " OR (CAST(:secondDate AS DATE) BETWEEN CAST(endBooking AS DATE) AND CAST(startBooking AS DATE)) " +
                            " OR (CAST(:firstDate AS DATE) >= CAST(startBooking AS DATE) " +
                            " AND CAST(:secondDate AS DATE) <= CAST(endBooking AS DATE))));", Hotel.class)
                    .setParameter("countryId", id)
                    .setParameter("firstDate", firstDate)
                    .setParameter("secondDate", secondDate)
                    .getResultList();
        } catch (NoResultException e) {
            log.warn("List free hotels not found");
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return listHotels;
    }

    @Override
    public Long getCountHotelClients(Long hotelId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Long countClients = null;
        try {
            Query query = entityManager
                    .createNativeQuery(" SELECT COUNT(DISTINCT user_id) FROM travel.tb_countries " +
                            " LEFT JOIN travel.tb_hotels ON travel.tb_countries.id = travel.tb_hotels.country_id " +
                            " LEFT JOIN travel.tb_rooms ON travel.tb_hotels.id = travel.tb_rooms.hotel_id " +
                            " LEFT JOIN travel.tb_orders ON travel.tb_rooms.id = travel.tb_orders.room_id " +
                            " LEFT JOIN travel.tb_visas ON travel.tb_countries.visa_id = travel.tb_visas.id " +
                            " WHERE hotel_id = :hotelId ")
                    .setParameter("hotelId", hotelId);
            String string = query.getSingleResult().toString();
            countClients = Long.parseLong(string);
        } catch (NoResultException e) {
            log.warn("Don't calculate hotel clients");
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return countClients;
    }

    @Override
    public Long getAverageReserveTime(Long hotelId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Long averageTime = null;
        try {
            Query query = entityManager
                    .createNativeQuery(" SELECT (SUM(CAST(endBooking AS DATE))-SUM(CAST(startBooking AS DATE)))/COUNT(startBooking) FROM travel.tb_countries " +
                            " LEFT JOIN travel.tb_hotels ON travel.tb_countries.id = travel.tb_hotels.country_id " +
                            " LEFT JOIN travel.tb_rooms ON travel.tb_hotels.id = travel.tb_rooms.hotel_id " +
                            " LEFT JOIN travel.tb_orders ON travel.tb_rooms.id = travel.tb_orders.room_id " +
                            " WHERE hotel_id = :hotelId ")
                    .setParameter("hotelId", hotelId);
            String string = query.getSingleResult().toString();
            Double temp = Double.parseDouble(string);
            averageTime = temp.longValue();
        } catch (NoResultException e) {
            log.warn("Don't calculate average time");
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return averageTime;
    }
}

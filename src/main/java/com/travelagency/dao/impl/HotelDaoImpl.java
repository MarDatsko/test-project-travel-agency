package com.travelagency.dao.impl;

import com.travelagency.dao.HotelDao;
import com.travelagency.entity.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Repository
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
        Hotel hotel = entityManager
                .find(Hotel.class, id);
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
        List<Hotel> listHotels = entityManager
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
        entityManager.getTransaction().commit();
        entityManager.close();
        return listHotels;
    }

    @Override
    public Long getCountHotelClients(Long hotelId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager
                .createNativeQuery(" SELECT COUNT(DISTINCT user_id) FROM travel.tb_countries " +
                        " LEFT JOIN travel.tb_hotels ON travel.tb_countries.id = travel.tb_hotels.country_id " +
                        " LEFT JOIN travel.tb_rooms ON travel.tb_hotels.id = travel.tb_rooms.hotel_id " +
                        " LEFT JOIN travel.tb_orders ON travel.tb_rooms.id = travel.tb_orders.room_id " +
                        " LEFT JOIN travel.tb_visas ON travel.tb_countries.visa_id = travel.tb_visas.id " +
                        " WHERE hotel_id = :hotelId ")
                .setParameter("hotelId", hotelId);
        String string = query.getSingleResult().toString();
        Long countClients = Long.parseLong(string);
        entityManager.getTransaction().commit();
        entityManager.close();
        return countClients;
    }

    @Override
    public Long getAverageReserveTime(Long hotelId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager
                .createNativeQuery(" SELECT (SUM(CAST(endBooking AS DATE))-SUM(CAST(startBooking AS DATE)))/COUNT(startBooking) FROM travel.tb_countries " +
                        " LEFT JOIN travel.tb_hotels ON travel.tb_countries.id = travel.tb_hotels.country_id " +
                        " LEFT JOIN travel.tb_rooms ON travel.tb_hotels.id = travel.tb_rooms.hotel_id " +
                        " LEFT JOIN travel.tb_orders ON travel.tb_rooms.id = travel.tb_orders.room_id " +
                        " WHERE hotel_id = :hotelId ")
                .setParameter("hotelId", hotelId);
        String string = query.getSingleResult().toString();
        Double temp = Double.parseDouble(string);
        Long averageTime = temp.longValue();
        entityManager.getTransaction().commit();
        entityManager.close();
        return averageTime;
    }
}

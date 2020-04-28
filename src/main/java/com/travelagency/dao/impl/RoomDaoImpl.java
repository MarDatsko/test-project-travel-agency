package com.travelagency.dao.impl;

import com.travelagency.dao.RoomDao;
import com.travelagency.entity.Room;
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
public class RoomDaoImpl implements RoomDao {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public RoomDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Room getRoomById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Room room = null;
        try {
            room = entityManager
                    .find(Room.class, id);
        } catch (NoResultException e) {
            log.warn("Room by id not found");
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return room;
    }

    @Override
    public List<Room> getAllRooms() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Room> listRooms = null;
        try {
            listRooms = entityManager
                    .createNativeQuery("SELECT * FROM tb_rooms", Room.class)
                    .getResultList();
        } catch (NoResultException e) {
            log.warn("List rooms not found");
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return listRooms;
    }

    @Override
    public void deleteRoomById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager
                .createNativeQuery("DELETE FROM tb_rooms WHERE id= :id", Room.class)
                .setParameter("id", id);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Room createRoom(Room room) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(room);
        entityManager.getTransaction().commit();
        entityManager.close();
        return room;
    }

    @Override
    public List<Room> getAllRoomsByHotelId(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Room> listRooms = null;
        try {
            listRooms = entityManager
                    .createNativeQuery("SELECT * FROM tb_rooms WHERE hotel_id = :id", Room.class)
                    .setParameter("id", id)
                    .getResultList();
        } catch (NoResultException e) {
            log.warn("List room not found");
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return listRooms;
    }

    @Override
    public Long getRoomOccupancy(Long roomId, LocalDate firstDate, LocalDate secondDate) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Long roomOccupancy = null;
        try {
            Query query = entityManager
                    .createNativeQuery(" SELECT SUM(CAST(endBooking AS DATE))-SUM(CAST(startBooking AS DATE)) FROM travel.tb_countries " +
                            " LEFT JOIN travel.tb_hotels ON travel.tb_countries.id = travel.tb_hotels.country_id " +
                            " LEFT JOIN travel.tb_rooms ON travel.tb_hotels.id = travel.tb_rooms.hotel_id " +
                            " LEFT JOIN travel.tb_orders ON travel.tb_rooms.id = travel.tb_orders.room_id " +
                            " WHERE room_id = :roomId AND (CAST(:firstDate AS DATE) < CAST(startBooking AS DATE) " +
                            " AND CAST(:secondDate AS DATE) > CAST(endBooking AS DATE)) ")
                    .setParameter("roomId", roomId)
                    .setParameter("firstDate", firstDate)
                    .setParameter("secondDate", secondDate);
            String string = query.getSingleResult().toString();
            Double temp = Double.parseDouble(string);
            roomOccupancy = temp.longValue();
        } catch (NoResultException e) {
            log.warn("Don't calculate room occupancy");
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return roomOccupancy;
    }
}

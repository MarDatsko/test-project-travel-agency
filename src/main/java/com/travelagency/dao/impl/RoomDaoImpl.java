package com.travelagency.dao.impl;

import com.travelagency.dao.RoomDao;
import com.travelagency.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class RoomDaoImpl implements RoomDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Room getRoomById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Room room = entityManager.find(Room.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return room;
    }

    @Override
    public List<Room> getAllRooms() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Room> listRooms = entityManager
                .createNativeQuery("SELECT * FROM tb_rooms", Room.class)
                .getResultList();
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
        List<Room> listRooms = entityManager
                .createNativeQuery("SELECT * FROM tb_rooms WHERE hotel_id = :id", Room.class)
                .setParameter("id", id)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return listRooms;
    }
}

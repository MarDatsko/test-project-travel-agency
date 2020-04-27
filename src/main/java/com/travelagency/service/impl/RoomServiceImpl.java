package com.travelagency.service.impl;

import com.travelagency.dao.RoomDao;
import com.travelagency.entity.Room;
import com.travelagency.exceptions.ResourceNotFoundException;
import com.travelagency.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomDao roomDao;

    @Override
    public Room getRoomById(Long id) {
        Room roomById = roomDao.getRoomById(id);
        if (roomById == null) {
            throw new ResourceNotFoundException(id.toString());
        }
        return roomById;
    }

    @Override
    public List<Room> getAllRooms() {
        List<Room> allRooms = roomDao.getAllRooms();
        if (allRooms == null || allRooms.isEmpty()) {
            throw new ResourceNotFoundException("Didn't found any rooms");
        }
        return allRooms;
    }

    @Override
    public void deleteRoomById(Long id) {
        roomDao.deleteRoomById(id);
    }

    @Override
    public Room createRoom(Room room) {
        roomDao.createRoom(room);
        return room;
    }

    @Override
    public List<Room> getAllRoomsByHotelId(Long id) {
        List<Room> allRooms = roomDao.getAllRoomsByHotelId(id);
        if (allRooms == null || allRooms.isEmpty()) {
            throw new ResourceNotFoundException("Didn't found any rooms");
        }
        return allRooms;
    }
}

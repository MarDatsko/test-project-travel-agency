package com.travelagency.service;

import com.travelagency.entity.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {

    Room getRoomById(Long id);

    List<Room> getAllRooms();

    void deleteRoomById(Long id);

    Room createRoom(Room room);

    List<Room> getAllRoomsByHotelId(Long id);

    Long getRoomOccupancy(Long roomId, LocalDate firstDate, LocalDate secondDate);
}

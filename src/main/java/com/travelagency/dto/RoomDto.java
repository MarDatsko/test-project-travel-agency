package com.travelagency.dto;


import com.travelagency.entity.Hotel;
import com.travelagency.entity.Room;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class RoomDto {

    private Long id;
    private String name;
    private String hotel;
    private boolean isFree;
    private Date startBooking;
    private Date endBooking;

    public RoomDto mapRoomToRoomDto (Room room){
        RoomDto roomDto = new RoomDto();

        roomDto.setId(room.getId());
        roomDto.setName(room.getName());
        roomDto.setHotel(room.getHotel().getName());
        roomDto.setFree(room.isFree());
        roomDto.setStartBooking(room.getStartBooking());
        roomDto.setEndBooking(room.getEndBooking());

        return roomDto;
    }
}

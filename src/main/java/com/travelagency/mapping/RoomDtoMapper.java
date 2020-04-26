package com.travelagency.mapping;

import com.travelagency.dto.RoomDto;
import com.travelagency.entity.Room;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class RoomDtoMapper extends AbstractConverter<Room, RoomDto> {

    @Override
    protected RoomDto convert(Room room) {
        RoomDto roomDto = new RoomDto();
        roomDto.setId(room.getId());
        roomDto.setName(room.getName());
        return roomDto;
    }
}

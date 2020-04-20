package com.travelagency.dto;

import com.travelagency.entity.Country;
import com.travelagency.entity.Hotel;
import com.travelagency.entity.Room;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class HotelDto {

    private Long id;
    private String name;
    private String  country;
    private List<RoomDto> rooms;

    public HotelDto mapHotelToHotelDto (Hotel hotel){
        HotelDto hotelDto = new HotelDto();
        hotelDto.setId(hotel.getId());
        hotelDto.setName(hotel.getName());
        hotelDto.setCountry(hotel.getCountry().getName());
        hotelDto.setRooms(hotel.getRooms().stream().map(room -> new RoomDto().mapRoomToRoomDto(room)).collect(Collectors.toList()));

        return hotelDto;
    }
}

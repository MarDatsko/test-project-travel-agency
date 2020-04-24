package com.travelagency.mapping;

import com.travelagency.dto.HotelDto;
import com.travelagency.entity.Hotel;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class HotelDtoMapper extends AbstractConverter<Hotel, HotelDto> {

    @Override
    protected HotelDto convert(Hotel hotel) {
        HotelDto hotelDto = new HotelDto();
        hotelDto.setId(hotel.getId());
        hotelDto.setName(hotel.getName());


        return hotelDto;
    }
}

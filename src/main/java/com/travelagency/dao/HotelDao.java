package com.travelagency.dao;

import com.travelagency.entity.Hotel;

import java.util.List;

public interface HotelDao {

    Hotel getHotelById(Long id);

    List<Hotel> getAllHotel();

    void deleteHotelById(Long id);

    Hotel createHotel(Hotel hotel);

}

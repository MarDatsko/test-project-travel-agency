package com.travelagency.dao;

import com.travelagency.entity.Hotel;

import java.time.LocalDate;
import java.util.List;

public interface HotelDao {

    Hotel getHotelById(Long id);

    List<Hotel> getAllHotel();

    List<Hotel> getAllHotelsByCountryId(Long id);

    void deleteHotelById(Long id);

    Hotel createHotel(Hotel hotel);

    List<Hotel> getAllFreeHotelOnCertainPeriod(Long id, LocalDate firstDate, LocalDate secondDate);
}

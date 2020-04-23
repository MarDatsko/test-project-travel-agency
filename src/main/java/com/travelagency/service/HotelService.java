package com.travelagency.service;

import com.travelagency.entity.Hotel;
import java.util.List;

public interface HotelService {

  Hotel getHotelById(Long id);

  List<Hotel> getAllHotel();

  void deleteHotelById(Long id);

  Hotel createHotel(Hotel hotel);
}

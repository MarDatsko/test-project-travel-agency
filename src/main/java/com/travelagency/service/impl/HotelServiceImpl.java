package com.travelagency.service.impl;

import com.travelagency.entity.Hotel;
import com.travelagency.repository.HotelRepository;
import com.travelagency.service.HotelService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl implements HotelService {
  @Autowired
  private HotelRepository hotelRepository;

  @Override
  public List<Hotel> findAllCountries() {
    return (List<Hotel>) hotelRepository.findAll();
  }
}

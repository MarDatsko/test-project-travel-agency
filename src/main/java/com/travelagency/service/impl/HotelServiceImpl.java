package com.travelagency.service.impl;

import com.travelagency.dao.HotelDao;
import com.travelagency.entity.Hotel;
import com.travelagency.exceptions.ResourceNotFoundException;
import com.travelagency.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelDao hotelDao;

    @Override
    public Hotel getHotelById(Long id) {
        Hotel hotelById = hotelDao.getHotelById(id);
        if (hotelById == null) {
            throw new ResourceNotFoundException(id.toString());
        }
        return hotelById;
    }

    @Override
    public List<Hotel> getAllHotel() {
        List<Hotel> allHotel = hotelDao.getAllHotel();
        if (allHotel == null || allHotel.isEmpty()) {
            throw new ResourceNotFoundException("Didn't find any hotels");
        }
        return allHotel;
    }

    @Override
    public List<Hotel> getAllHotelsByCountryId(Long id) {
        List<Hotel> allHotel = hotelDao.getAllHotelsByCountryId(id);
        if (allHotel == null || allHotel.isEmpty()) {
            throw new ResourceNotFoundException("Didn't find any hotels");
        }
        return allHotel;
    }

    @Override
    public void deleteHotelById(Long id) {
        hotelDao.deleteHotelById(id);
    }

    @Override
    public Hotel createHotel(Hotel hotel) {
        hotelDao.createHotel(hotel);
        return hotel;
    }

    @Override
    public List<Hotel> getAllFreeHotelOnCertainPeriod(Long id, LocalDate firstDate, LocalDate secondDate) {
        List<Hotel> allHotel = hotelDao.getAllFreeHotelOnCertainPeriod(id, firstDate, secondDate);
        if (allHotel == null || allHotel.isEmpty()) {
            throw new ResourceNotFoundException("Didn't find any hotels");
        }
        return allHotel;
    }
}

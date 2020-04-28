package com.travelagency.controller;

import com.travelagency.dto.CountryDto;
import com.travelagency.dto.DateAndCountryDto;
import com.travelagency.dto.HotelDto;
import com.travelagency.dto.RoomDto;
import com.travelagency.service.CountryService;
import com.travelagency.service.HotelService;
import java.util.ArrayList;
import java.util.List;

import com.travelagency.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HotelController {
  @Autowired
  private CountryService countryService;

  @Autowired
  private HotelService hotelService;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private RoomService roomService;

  @RequestMapping("/country")
  public ModelAndView findCountryById(@RequestParam long id){
    List<HotelDto> listHotels = new ArrayList<>();
    CountryDto country = modelMapper.map(countryService.getCountryById(id), CountryDto.class);
    hotelService.getAllHotelsByCountryId(id).forEach(hotel -> listHotels.add(modelMapper.map(hotel, HotelDto.class)));
    ModelAndView mav = new ModelAndView("country_info");
    mav.addObject("listHotels", listHotels);
    mav.addObject("country", country);
    return mav;
  }

  @GetMapping("/hotelStatistic/{id}")
  public String hotelStatistic(@PathVariable(name = "id") long id, Model model) {
    Long numberOfCustomers = hotelService.getCountHotelClient(id);
    Long averageReserveTime = hotelService.getAverageReserveTime(id);
    HotelDto hotelDto = modelMapper.map(hotelService.getHotelById(id), HotelDto.class);
    List <RoomDto> listRooms = new ArrayList<>();
    roomService.getAllRoomsByHotelId(id).forEach(room -> listRooms.add(modelMapper.map(room,RoomDto.class)));
    DateAndCountryDto room = new DateAndCountryDto();
    model.addAttribute("numberOfCustomers", numberOfCustomers);
    model.addAttribute("averageReserveTime", averageReserveTime);
    model.addAttribute("hotel", hotelDto);
    model.addAttribute("listRooms",listRooms);
    model.addAttribute("room",room);
    return "hotelStatistic";
  }

  @PostMapping("/roomStatistic/{id}")
  public String roomStatistic (@PathVariable(name = "id") long id, Model model, DateAndCountryDto dateAndCountryDto){
    Long roomOccupancy = roomService.getRoomOccupancy(id, dateAndCountryDto.getFirstDate(), dateAndCountryDto.getSecondDate());
    Long numberOfCustomers = hotelService.getCountHotelClient(id);
    Long averageReserveTime = hotelService.getAverageReserveTime(id);
    HotelDto hotelDto = modelMapper.map(hotelService.getHotelById(id), HotelDto.class);
    List <RoomDto> listRooms = new ArrayList<>();
    roomService.getAllRoomsByHotelId(id).forEach(room -> listRooms.add(modelMapper.map(room,RoomDto.class)));
    DateAndCountryDto room = new DateAndCountryDto();
    model.addAttribute("numberOfCustomers", numberOfCustomers);
    model.addAttribute("averageReserveTime", averageReserveTime);
    model.addAttribute("hotel", hotelDto);
    model.addAttribute("listRooms",listRooms);
    model.addAttribute("room",room);
    model.addAttribute("day","days");
    model.addAttribute("logo","Room occupancy over a period of time");
    model.addAttribute("statistic",roomOccupancy);
    return "hotelStatistic";
  }
}

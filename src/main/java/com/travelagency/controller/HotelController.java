package com.travelagency.controller;

import com.travelagency.dto.CountryDto;
import com.travelagency.dto.HotelDto;
import com.travelagency.service.CountryService;
import com.travelagency.service.HotelService;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HotelController {
  @Autowired
  private CountryService countryService;

  @Autowired
  private HotelService hotelService;

  @Autowired
  private ModelMapper modelMapper;

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
}

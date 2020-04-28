package com.travelagency.controller;

import com.travelagency.dto.CountryDto;
import com.travelagency.dto.HotelDto;
import com.travelagency.dto.VisaDto;
import com.travelagency.entity.Country;
import com.travelagency.entity.Hotel;
import com.travelagency.service.CountryService;
import com.travelagency.service.HotelService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    Long avarageReserveTime = hotelService.getAverageReserveTime(id);
    HotelDto hotelDto = modelMapper.map(hotelService.getHotelById(id), HotelDto.class);
    model.addAttribute("numberOfCustomers", numberOfCustomers);
    model.addAttribute("avarageReserveTime", avarageReserveTime);
    model.addAttribute("hotel", hotelDto);
    return "hotelStatistic";
  }

  @RequestMapping(value = "/saveHotel", method = RequestMethod.POST)
  public String saveHotel(@ModelAttribute("hotel") HotelDto hotelDto) {
    hotelService.createHotel(modelMapper.map(hotelDto, Hotel.class));
    long id = hotelDto.getId();
    return "redirect:/country?id=" + id;
  }

  @RequestMapping("/new_hotel/country/{country_id}")
  public ModelAndView newHotelForm(@PathVariable(name = "country_id") Long country_id) {
    ModelAndView model = new ModelAndView("create_hotel");
    HotelDto hotelDto = new HotelDto();
    model.addObject("hotel", hotelDto);
    model.addObject("country_id", country_id);
    return model;
  }

  @RequestMapping(value = "/saveHotel/{country_id}", method = RequestMethod.POST)
  public String saveHotel(@ModelAttribute("hotel") HotelDto hotelDto,
                            @PathVariable(name = "country_id") Long country_id){
    Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
    Country country = new Country();
    country.setId(country_id);
    hotel.setCountry(country);
    hotelService.createHotel(hotel);
    return "redirect:/country?id=" + country_id;
  }


  @RequestMapping("/editHotel/{hotel_id}/country/{country_id}")
  public ModelAndView editHotelForm(@PathVariable(name = "hotel_id") Long hotel_id,
                                    @PathVariable(name = "country_id") Long country_id) {
    ModelAndView mav = new ModelAndView("edit_hotel");
    HotelDto hotelDto = modelMapper.map(hotelService.getHotelById(hotel_id), HotelDto.class);
    mav.addObject("hotel", hotelDto);
    mav.addObject("country_id", country_id);
    return mav;
  }

  @RequestMapping(value = "/saveHotelEditing/{country_id}", method = RequestMethod.POST)
  public String saveEditing(@ModelAttribute("hotel") HotelDto hotelDto,
                            @PathVariable(name = "country_id") Long country_id){
    hotelService.updateHotel(modelMapper.map(hotelDto, Hotel.class));
    return "redirect:/country?id=" + country_id;
  }

  @RequestMapping("/deleteHotel/{id}/{country_id}")
  public String deleteHotelForm(@PathVariable(name = "id") Long id, @PathVariable(name = "country_id") Long country_id) {
    hotelService.deleteHotelById(id);
    return "redirect:/country?id=" + country_id;
  }
}

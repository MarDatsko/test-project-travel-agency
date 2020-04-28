package com.travelagency.controller;

import com.travelagency.dto.CountryDto;
import com.travelagency.dto.DateAndCountryDto;
import com.travelagency.dto.HotelDto;
import com.travelagency.dto.RoomDto;
import com.travelagency.entity.Country;
import com.travelagency.entity.Hotel;
import com.travelagency.service.CountryService;
import com.travelagency.service.HotelService;
import com.travelagency.service.RoomService;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HotelController {

    private final CountryService countryService;
    private final HotelService hotelService;
    private final ModelMapper modelMapper;
    private final RoomService roomService;

    @Autowired
    public HotelController(CountryService countryService, HotelService hotelService, ModelMapper modelMapper, RoomService roomService) {
        this.countryService = countryService;
        this.hotelService = hotelService;
        this.modelMapper = modelMapper;
        this.roomService = roomService;
    }

    @GetMapping("/hotelStatistic/{id}")
    public String getHotelStatistic(@PathVariable(name = "id") long id, Model model) {
        Long numberOfCustomers = hotelService.getCountHotelClient(id);
        Long averageReserveTime = hotelService.getAverageReserveTime(id);
        HotelDto hotelDto = modelMapper.map(hotelService.getHotelById(id), HotelDto.class);
        List<RoomDto> listRooms = new ArrayList<>();
        roomService.getAllRoomsByHotelId(id).forEach(room -> listRooms.add(modelMapper.map(room, RoomDto.class)));
        DateAndCountryDto room = new DateAndCountryDto();
        model.addAttribute("numberOfCustomers", numberOfCustomers);
        model.addAttribute("averageReserveTime", averageReserveTime);
        model.addAttribute("hotel", hotelDto);
        model.addAttribute("listRooms", listRooms);
        model.addAttribute("room", room);
        return "hotel/statistic_hotel";
    }

    @PostMapping(value = "/saveHotel")
    public String saveHotel(@ModelAttribute("hotel") HotelDto hotelDto) {
        hotelService.createHotel(modelMapper.map(hotelDto, Hotel.class));
        long id = hotelDto.getId();
        return "redirect:/country?id=" + id;
    }

    @GetMapping("/new_hotel/country/{country_id}")
    public String newHotelForm(@PathVariable(name = "country_id") Long country_id, Model model) {
        HotelDto hotelDto = new HotelDto();
        model.addAttribute("hotel", hotelDto);
        model.addAttribute("country_id", country_id);
        return "hotel/create_hotel";
    }

    @PostMapping(value = "/saveHotel/{country_id}")
    public String saveHotel(@ModelAttribute("hotel") HotelDto hotelDto,
                            @PathVariable(name = "country_id") Long country_id) {
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        Country country = new Country();
        country.setId(country_id);
        hotel.setCountry(country);
        hotelService.createHotel(hotel);
        return "redirect:/country?id=" + country_id;
    }


    @GetMapping("/editHotel/{hotel_id}/country/{country_id}")
    public String editHotelForm(@PathVariable(name = "hotel_id") Long hotel_id,
                                @PathVariable(name = "country_id") Long country_id, Model model) {
        HotelDto hotelDto = modelMapper.map(hotelService.getHotelById(hotel_id), HotelDto.class);
        model.addAttribute("hotel", hotelDto);
        model.addAttribute("country_id", country_id);
        return "hotel/edit_hotel";
    }

    @PostMapping(value = "/saveHotelEditing/{country_id}")
    public String saveEditing(@ModelAttribute("hotel") HotelDto hotelDto,
                              @PathVariable(name = "country_id") Long country_id) {
        hotelService.updateHotel(modelMapper.map(hotelDto, Hotel.class));
        return "redirect:/country?id=" + country_id;
    }

    @GetMapping("/deleteHotel/{id}/{country_id}")
    public String deleteHotelForm(@PathVariable(name = "id") Long id, @PathVariable(name = "country_id") Long country_id) {
        hotelService.deleteHotelById(id);
        return "redirect:/country?id=" + country_id;
    }

    @SneakyThrows
    @PostMapping("/freeHotel/{id}")
    public String showFreeHotels(@PathVariable(name = "id") Long id, DateAndCountryDto dateAndCountryDto, Model model) {
        List<HotelDto> listHotels = new ArrayList<>();
        List<HotelDto> list = new ArrayList<>();

        CountryDto country = modelMapper.map(countryService.getCountryById(id), CountryDto.class);

        List<Hotel> allHotelsByCountryId = hotelService.getAllHotelsByCountryId(id);
        allHotelsByCountryId.forEach(hotel -> listHotels.add(modelMapper.map(hotel, HotelDto.class)));

        List<Hotel> allFreeHotelOnCertainPeriod = hotelService.getAllFreeHotelOnCertainPeriod(id, dateAndCountryDto.getFirstDate(), dateAndCountryDto.getSecondDate());
        allFreeHotelOnCertainPeriod.forEach(hotel -> list.add(modelMapper.map(hotel, HotelDto.class)));

        listHotels.removeAll(list);

        model.addAttribute("listHotels", listHotels);
        model.addAttribute("country", country);
        return "hotel/list_hotels";
    }

    @GetMapping("/country")
    public String printListHotels(@RequestParam Long id, Model model) {
        List<HotelDto> listHotels = new ArrayList<>();
        CountryDto country = modelMapper.map(countryService.getCountryById(id), CountryDto.class);
        hotelService.getAllHotelsByCountryId(id).forEach(hotel -> listHotels.add(modelMapper.map(hotel, HotelDto.class)));
        model.addAttribute("listHotels", listHotels);
        model.addAttribute("country", country);
        return "hotel/list_hotels";
    }
}

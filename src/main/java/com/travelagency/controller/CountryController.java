package com.travelagency.controller;

import com.travelagency.dto.CountryDto;
import com.travelagency.dto.HotelDto;
import com.travelagency.dto.VisaDto;
import com.travelagency.entity.Country;
import com.travelagency.service.CountryService;
import com.travelagency.service.HotelService;
import com.travelagency.service.VisaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CountryController {

    private final CountryService countryService;
    private final ModelMapper modelMapper;
    private final VisaService visaService;
    private final HotelService hotelService;

    @Autowired
    public CountryController(CountryService countryService, ModelMapper modelMapper, VisaService visaService, HotelService hotelService) {
        this.countryService = countryService;
        this.modelMapper = modelMapper;
        this.visaService = visaService;
        this.hotelService = hotelService;
    }

    @GetMapping("/countries")
    public String countries(Model model) {
        List<CountryDto> listCountries = new ArrayList<>();
        countryService.getAllCountries().forEach(country -> listCountries.add(
                modelMapper.map(country, CountryDto.class)));
        model.addAttribute("listCountries", listCountries);
        return "country/list_countries";
    }

    @GetMapping("/new_country")
    public String newCountryForm(Model model) {
        CountryDto country = new CountryDto();
        model.addAttribute("country", country);
        return "country/create_country";
    }

    @GetMapping("/country")
    public String findCountryById(@RequestParam Long id, Model model) {
        List<HotelDto> listHotels = new ArrayList<>();
        CountryDto country = modelMapper.map(countryService.getCountryById(id), CountryDto.class);
        hotelService.getAllHotelsByCountryId(id).forEach(hotel -> listHotels.add(modelMapper.map(hotel, HotelDto.class)));
        model.addAttribute("listHotels", listHotels);
        model.addAttribute("country", country);
        return "hotel/list_hotels";
    }

    @PostMapping(value = "/save")
    public String saveCountry(@ModelAttribute("country") CountryDto countryDto) {
        countryService.createCountry(modelMapper.map(countryDto, Country.class));
        return "country/list_countries";
    }

    @GetMapping("/edit")
    public String editCountryForm(@RequestParam long id, Model model) {
        CountryDto country = modelMapper.map(countryService.getCountryById(id), CountryDto.class);
        List<VisaDto> visaList = new ArrayList<>();
        visaService.getAllVisas().forEach(visa -> visaList.add(
                modelMapper.map(visa, VisaDto.class)));
        model.addAttribute("country", country);
        model.addAttribute("visaList", visaList);
        return "country/edit_country";
    }

    @PostMapping(value = "/saveEditing")
    public String saveEditing(@ModelAttribute("country") CountryDto countryDto) {
        countryService.updateCountry(modelMapper.map(countryDto, Country.class));
        return "country/list_countries";
    }

    @DeleteMapping("/delete")
    public String deleteCountryForm(@RequestParam Long id) {
        countryService.deleteCountryById(id);
        return "country/list_countries";
    }
}

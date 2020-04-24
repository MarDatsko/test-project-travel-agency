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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CountryController {
    @Autowired
    private CountryService countryService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VisaService visaService;

    @Autowired
    private HotelService hotelService;

    @RequestMapping("/countries")
    public ModelAndView countries() {
        List<CountryDto> listCountries = new ArrayList<>();
        countryService.getAllCountries().forEach(country -> listCountries.add(
                modelMapper.map(country, CountryDto.class)));
        ModelAndView mav = new ModelAndView("countries");
        mav.addObject("listCountries", listCountries);
        return mav;
    }

    @RequestMapping("/new_country")
    public String newCountryForm(Map<String, Object> model) {
        CountryDto country = new CountryDto();
        model.put("country", country);
        return "new_country";
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCountry(@ModelAttribute("country") CountryDto countryDto) {
        countryService.createCountry(modelMapper.map(countryDto, Country.class));
        return "redirect:/countries";
    }

    @RequestMapping("/edit")
    public ModelAndView editCountryForm(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("edit_country");
        CountryDto country = modelMapper.map(countryService.getCountryById(id), CountryDto.class);
        mav.addObject("country", country);

        return mav;
    }

    @RequestMapping(value = "/saveEditing", method = RequestMethod.POST)
    public String saveEditing(@ModelAttribute("country") CountryDto countryDto){
        countryService.updateCountry(modelMapper.map(countryDto, Country.class));
        return "redirect:/countries";
    }

    @RequestMapping("/delete")
    public String deleteCustomerForm(@RequestParam Long id) {
        countryService.deleteCountryById(id);
        return "redirect:/countries";
    }

    @ModelAttribute("viasaList")
    public List<VisaDto> getVisaList() {
        List<VisaDto> visaList = new ArrayList<>();
        visaService.getAllVisas().forEach(visa -> visaList.add(
                modelMapper.map(visa, VisaDto.class)));
        return visaList;
    }

}

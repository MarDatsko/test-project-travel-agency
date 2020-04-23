package com.travelagency.controller;

import com.travelagency.dto.CountryDto;
import com.travelagency.dto.VisaDto;
import com.travelagency.entity.Country;
import com.travelagency.service.CountryService;
import com.travelagency.service.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CountryController {
    @Autowired
    private CountryService countryService;

    @Autowired
    private VisaService visaService;

    @RequestMapping("/countries")
    public ModelAndView countries() {
        List<CountryDto> listCountries = new ArrayList<>();
        countryService.getAllCountries().forEach(country -> listCountries.add(new CountryDto().mapCountryToCountryDto(country)));
        listCountries.forEach(System.out::println);


        ModelAndView mav = new ModelAndView("countries");
        mav.addObject("listCountries", listCountries);
        return mav;
    }

    @RequestMapping("/new_country")
    public String newCountryForm(Map<String, Object> model) {
        Country country = new Country();
        model.put("country", country);
        System.out.println(country);
        return "new_country";
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCountry(@ModelAttribute("country") Country country) {
        System.out.println(country);
        countryService.createCountry(country);
        System.out.println(country);
        return "redirect:/countries";
    }

    @ModelAttribute("viasaList")
    public List<VisaDto> getVisaList() {
        List<VisaDto> visaList = new ArrayList<>();
        visaService.getAllVisas().forEach(visa -> visaList.add(new VisaDto().mapVisaToVisaDto(visa)));
        return visaList;
    }
}

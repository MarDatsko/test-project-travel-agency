package com.travelagency.controller;

import com.travelagency.entity.Country;
import com.travelagency.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class CountryController {
    @Autowired
    private CountryService countryService;

    @RequestMapping("/countries")
    public ModelAndView countries() {
        List<Country> listCountries = countryService.findAll();
        ModelAndView mav = new ModelAndView("countries");
        mav.addObject("listCountries", listCountries);
        return mav;
    }

    @RequestMapping("/new_country")
    public String newCountryForm(Map<String, Object> model) {
        Country country = new Country();
        model.put("country", country);
        return "new_country";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCountry(@ModelAttribute("country") Country country) {
        countryService.createCountry(country);
        return "redirect:/countries";
    }

}

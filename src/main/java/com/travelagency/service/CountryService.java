package com.travelagency.service;

import com.travelagency.dto.CountryDto;
import com.travelagency.entity.Country;

import java.util.List;

public interface CountryService {

    Country createCountry(Country country);

    List<Country> getAllCountries();

    Country getCountryById(Long id);

    void deleteCountryById(Long id);
}

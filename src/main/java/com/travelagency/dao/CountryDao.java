package com.travelagency.dao;

import com.travelagency.entity.Country;

import java.util.List;

public interface CountryDao {

    Country createCountry(Country country);

    Country updateCountry (Country country);

    List<Country> getAllCountries();

    Country getCountryById(Long id);

    void deleteCountryById(Long id);
}

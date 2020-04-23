package com.travelagency.service.impl;

import com.travelagency.dao.CountryDao;
import com.travelagency.entity.Country;
import com.travelagency.exceptions.ResourceNotFoundException;
import com.travelagency.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryDao countryDao;

    @Override
    public Country createCountry(Country country) {
        countryDao.createCountry(country);
        return country;
    }

    @Override
    public List<Country> getAllCountries() {
        List<Country> allCountries = countryDao.getAllCountries();
        if (allCountries == null || allCountries.isEmpty()) {
            throw new ResourceNotFoundException("Didn't find any countries");
        }
        return allCountries;
    }

    @Override
    public Country getCountryById(Long id) {
        Country countryById = countryDao.getCountryById(id);
        if (countryById == null) {
            throw new ResourceNotFoundException(id.toString());
        }
        return countryById;
    }

    @Override
    public void deleteCountryById(Long id) {
        countryDao.deleteCountryById(id);
    }
}

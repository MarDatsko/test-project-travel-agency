package com.travelagency.service.impl;

import com.travelagency.dto.CountryDto;
import com.travelagency.dto.VisaDto;
import com.travelagency.entity.Country;
import com.travelagency.entity.Visa;
import com.travelagency.exceptions.ResourceNotFoundException;
import com.travelagency.repository.CountryRepository;
import com.travelagency.repository.VisaRepository;
import com.travelagency.service.CountryService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private VisaRepository visaRepository;


    @Override
    public Country createCountry(Country country) {
        return  countryRepository.save(country);
    }

    @Override
    public List<Country> findAll() {
        return (List<Country>) countryRepository.findAll();
    }
}

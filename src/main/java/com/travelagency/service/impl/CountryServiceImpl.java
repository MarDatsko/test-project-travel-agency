package com.travelagency.service.impl;

import com.travelagency.entity.Country;
import com.travelagency.repository.CountryRepository;
import com.travelagency.service.CountryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {

  @Autowired
  private CountryRepository countryRepository;

  @Override
  public Country createCountry(Country country) {
    return countryRepository.save(country);
  }

  @Override
  public List<Country> findAll() {
    return (List<Country>) countryRepository.findAll();
  }
}

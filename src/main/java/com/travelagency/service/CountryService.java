package com.travelagency.service;

import com.travelagency.entity.Country;
import java.util.List;

public interface CountryService {
    Country createCountry (Country country);
    List<Country> findAll ();
}

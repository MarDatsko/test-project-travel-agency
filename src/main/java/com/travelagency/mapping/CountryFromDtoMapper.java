package com.travelagency.mapping;

import com.travelagency.dto.CountryDto;
import com.travelagency.entity.Country;
import com.travelagency.entity.Visa;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class CountryFromDtoMapper extends AbstractConverter<CountryDto, Country> {

  @Override
  protected Country convert(CountryDto countryDto) {
    Country country = new Country();
    country.setId(countryDto.getId());
    country.setName(countryDto.getName());
    Visa visa = new Visa();
    visa.setId(countryDto.getVisa().getId());
    visa.setName(countryDto.getVisa().getName());
    country.setVisa(visa);
    return country;
  }
}

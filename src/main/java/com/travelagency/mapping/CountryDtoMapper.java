package com.travelagency.mapping;

import com.travelagency.dto.CountryDto;
import com.travelagency.dto.VisaDto;
import com.travelagency.entity.Country;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class CountryDtoMapper extends AbstractConverter<Country, CountryDto> {

    @Override
    protected CountryDto convert(Country country) {
        CountryDto countryDto = new CountryDto();
        countryDto.setId(country.getId());
        countryDto.setName(country.getName());
        VisaDto visaDto = new VisaDto();
        visaDto.setName(country.getVisa().getName());
        visaDto.setId(country.getVisa().getId());
        countryDto.setVisa(visaDto);
        return countryDto;
    }
}

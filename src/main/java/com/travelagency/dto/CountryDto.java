package com.travelagency.dto;

import com.travelagency.entity.Country;
import com.travelagency.entity.Visa;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class CountryDto {

    private Long id;
    private String name;
    private String visa;
    private List<HotelDto> hotels;


    public CountryDto mapCountryToCountryDto(Country country){
        CountryDto countryDto = new CountryDto();
            countryDto.setId(country.getId());
            countryDto.setName(country.getName());
            countryDto.setVisa(country.getVisa().getName());
            countryDto.setHotels(country.getHotels().stream()
                    .map(hotel -> new HotelDto().mapHotelToHotelDto(hotel)).collect(Collectors.toList()));
        return countryDto;
    }
}

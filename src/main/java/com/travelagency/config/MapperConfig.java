package com.travelagency.config;

import com.travelagency.mapping.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class MapperConfig {

    private CountryDtoMapper countryDtoMapper;
    private HotelDtoMapper hotelDtoMapper;
    private RoomDtoMapper roomDtoMapper;
    private VisaDtoMapper visaDtoMapper;
    private CountryFromDtoMapper countryFromDtoMapper;
    private UserDtoMapper userDtoMapper;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        mapper.addConverter(countryDtoMapper);
        mapper.addConverter(hotelDtoMapper);
        mapper.addConverter(roomDtoMapper);
        mapper.addConverter(visaDtoMapper);
        mapper.addConverter(countryFromDtoMapper);
        mapper.addConverter(userDtoMapper);
        return mapper;
    }
}

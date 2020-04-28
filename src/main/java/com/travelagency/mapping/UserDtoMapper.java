package com.travelagency.mapping;

import com.travelagency.dto.UserDto;
import com.travelagency.entity.User;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper extends AbstractConverter<User, UserDto> {

    @Override
    protected UserDto convert(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUserRole(user.getUserRole());
        return userDto;
    }
}

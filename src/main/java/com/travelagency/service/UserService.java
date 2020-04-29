package com.travelagency.service;

import com.travelagency.dto.UserRegisterDto;
import com.travelagency.entity.User;

import java.util.List;

public interface UserService {

    User getUserById(Long id);

    User createUser(User user);

    User getUserByEmail(String email);

    void deleteById(Long id);

    List<String> getListCountriesWhereWasUser(Long userId);

    List<String> getListVisasWhichHasUser(Long userId);

    List<User> getAllUsers();

    void saveUser(UserRegisterDto userRegisterDto);
}

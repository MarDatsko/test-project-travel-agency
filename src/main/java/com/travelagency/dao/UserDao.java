package com.travelagency.dao;

import com.travelagency.entity.User;

import java.util.List;

public interface UserDao {

    User getUserById(Long id);

    User createUser(User user);

    User getUserByEmail(String email);

    void deleteUserById(Long id);

    List<String> getListCountriesWhereWasUser(Long userId);

    List<String> getListVisasWhichHasUser(Long userId);
}

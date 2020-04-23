package com.travelagency.dao;

import com.travelagency.entity.User;

public interface UserDao {

    User getUserById(Long id);

    User createUser(User user);

    User getUserByEmail(String email);

    void deleteUserById(Long id);
}

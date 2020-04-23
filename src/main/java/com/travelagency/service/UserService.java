package com.travelagency.service;

import com.travelagency.entity.User;

public interface UserService {

    User getUserById (Long id);

    User createUser (User user);

    User getUserByEmail(String email);

    void deleteById (Long id);
}

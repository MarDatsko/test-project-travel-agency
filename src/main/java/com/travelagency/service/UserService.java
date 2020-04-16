package com.travelagency.service;

import com.travelagency.entity.User;

public interface UserService {

    User create(User user);

    User getByID(Long id);

    User getByEmail(String email);
}

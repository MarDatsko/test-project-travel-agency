package com.travelagency.service.impl;

import com.travelagency.entity.User;
import com.travelagency.exceptions.ResourceNotFoundException;
import com.travelagency.repository.UserRepository;
import com.travelagency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getByID(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id.toString()));
    }

    @Override
    public User getByEmail(String email) {
        User userByEmail = userRepository
                .getUserByEmail(email);
        if (userByEmail == null) {
            throw new ResourceNotFoundException(email);
        }
        return userByEmail;
    }
}

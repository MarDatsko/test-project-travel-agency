package com.travelagency.service.impl;

import com.travelagency.dao.UserDao;
import com.travelagency.entity.User;
import com.travelagency.exceptions.ResourceNotFoundException;
import com.travelagency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Long id) {
        User userById = userDao.getUserById(id);
        if (userById == null) {
            throw new ResourceNotFoundException(id.toString());
        }
        return userById;
    }

    @Override
    public User createUser(User user) {
        return userDao.createUser(user);
    }

    @Override
    public User getUserByEmail(String email) {
        User userByEmail = userDao.getUserByEmail(email);
        if (userByEmail == null) {
            throw new ResourceNotFoundException(email);
        }
        return userByEmail;
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteUserById(id);
    }
}

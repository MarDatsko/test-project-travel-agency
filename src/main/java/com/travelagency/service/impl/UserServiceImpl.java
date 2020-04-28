package com.travelagency.service.impl;

import com.travelagency.dao.UserDao;
import com.travelagency.entity.User;
import com.travelagency.exceptions.ResourceNotFoundException;
import com.travelagency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            //    throw new ResourceNotFoundException(email);
        }
        return userByEmail;
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteUserById(id);
    }

    @Override
    public List<String> getListCountriesWhereWasUser(Long userId) {
        List<String> listCountries = userDao.getListCountriesWhereWasUser(userId);
        if (listCountries == null || listCountries.isEmpty()) {
            throw new ResourceNotFoundException("Didn't find any countries");
        }
        return listCountries;
    }

    @Override
    public List<String> getListVisasWhichHasUser(Long userId) {
        List<String> listVisas = userDao.getListVisasWhichHasUser(userId);
        if (listVisas == null || listVisas.isEmpty()) {
            throw new ResourceNotFoundException("Didn't find any visas");
        }
        return listVisas;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> listUsers = userDao.getAllUsers();
        if (listUsers == null || listUsers.isEmpty()) {
            throw new ResourceNotFoundException("Didn't find any visas");
        }
        return listUsers;
    }
}

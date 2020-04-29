package com.travelagency.service.impl;

import com.travelagency.dao.UserDao;
import com.travelagency.dto.UserRegisterDto;
import com.travelagency.entity.User;
import com.travelagency.enums.UserRole;
import com.travelagency.exceptions.ResourceNotFoundException;
import com.travelagency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

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

    @Override
    public void saveUser(UserRegisterDto userRegisterDto) {
        User user = new User();
        user.setFirstName(userRegisterDto.getFirstName());
        user.setLastName(userRegisterDto.getLastName());
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(userRegisterDto.getPassword());
        user.setUserRole(UserRole.ROLE_USER);
        createUser(user);
    }
}

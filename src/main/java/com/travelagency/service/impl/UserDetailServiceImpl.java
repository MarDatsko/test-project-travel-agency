package com.travelagency.service.impl;

import com.travelagency.entity.User;
import com.travelagency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) {

        User user = userService.getByEmail(email);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user.getUserRole().toString());
        grantedAuthorities.add(simpleGrantedAuthority);

        return new org.springframework.security.core.userdetails
                .User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}

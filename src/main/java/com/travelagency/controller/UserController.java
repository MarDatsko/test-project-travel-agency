package com.travelagency.controller;

import com.travelagency.dto.UserDto;
import com.travelagency.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/listUsers")
    public String userList(Model model) {
        List<UserDto> listUserDto = new ArrayList<>();
        userService.getAllUsers().forEach(user -> listUserDto.add(modelMapper.map(user, UserDto.class)));
        System.out.println(listUserDto);
        model.addAttribute("listUserDto", listUserDto);
        return "user/list_users";
    }

    @GetMapping("/userStatistic/{id}")
    public String userStatistic(@PathVariable(name = "id") Long id, Model model) {
        List<String> listCountriesWhereWasUser = userService.getListCountriesWhereWasUser(id);
        List<String> listVisasWhichHasUser = userService.getListVisasWhichHasUser(id);
        model.addAttribute("listCountriesWhereWasUser", listCountriesWhereWasUser);
        model.addAttribute("listVisasWhichHasUser", listVisasWhichHasUser);
        return "user/statistic_user";
    }

    @DeleteMapping("/delete/user/{id}")
    public String deleteCustomerForm(@PathVariable(name = "id") Long id) {
        userService.deleteById(id);
        return "redirect:/listUsers";
    }
}

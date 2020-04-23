package com.travelagency.controller;

import com.travelagency.dto.UserRegisterDto;
import com.travelagency.entity.User;
import com.travelagency.enums.UserRole;
import com.travelagency.exceptions.ResourceNotFoundException;
import com.travelagency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String printLogo() {
        return "index";
    }

    @GetMapping("/register")
    public String printRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(UserRegisterDto userRegisterDto, Model model) {

        try {
            userService.getUserByEmail(userRegisterDto.getEmail());
        } catch (ResourceNotFoundException e) {
            User user = new User();

            user.setFirstName(userRegisterDto.getFirstName());
            user.setLastName(userRegisterDto.getLastName());
            user.setEmail(userRegisterDto.getEmail());
            user.setPassword(userRegisterDto.getPassword());
            user.setUserRole(UserRole.USER);

            userService.createUser(user);

            return "redirect:/login";
        }

        model.addAttribute("message", "User with this email "
                + userRegisterDto.getEmail() + " already created");
        return "infoPage";
    }

    @GetMapping("/mainPage")
    public String sendUser(Model model, HttpSession session) {
        System.out.println(session.getAttributeNames());
        return "userMainPage";
    }

    @PostMapping("/mainPage")
    public String sendUsers(Model model, HttpSession session) {
        System.out.println(session.getAttributeNames());
        return "userMainPage";
    }

}

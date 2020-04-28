package com.travelagency.controller;

import com.travelagency.dto.CountryDto;
import com.travelagency.dto.DateAndCountryDto;
import com.travelagency.dto.UserRegisterDto;
import com.travelagency.entity.User;
import com.travelagency.enums.UserRole;
import com.travelagency.service.CountryService;
import com.travelagency.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private final UserService userService;
    private final CountryService countryService;
    private final ModelMapper modelMapper;

    @Autowired
    public MainController(UserService userService, CountryService countryService, ModelMapper modelMapper) {
        this.userService = userService;
        this.countryService = countryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public String printLogo() {
        return "main/index";
    }

    @GetMapping("/register")
    public String printRegisterForm() {
        return "main/register";
    }

    @PostMapping("/register")
    public String registerUser(UserRegisterDto userRegisterDto, Model model) {
        User userByEmail = userService.getUserByEmail(userRegisterDto.getEmail());
        if (userByEmail == null) {
            User user = new User();
            user.setFirstName(userRegisterDto.getFirstName());
            user.setLastName(userRegisterDto.getLastName());
            user.setEmail(userRegisterDto.getEmail());
            user.setPassword(userRegisterDto.getPassword());
            user.setUserRole(UserRole.ROLE_USER);
            userService.createUser(user);
            return "main/login";
        }

        model.addAttribute("message", "User with this email "
                + userRegisterDto.getEmail() + " already created");
        return "main/exception_page";
    }

    @GetMapping("/mainPage")
    public String sendUser(Model model, HttpSession session) {
        DateAndCountryDto dateAndCountryDto = new DateAndCountryDto();
        List<CountryDto> countryDtos = new ArrayList<>();
        countryService.getAllCountries().forEach(country -> countryDtos.add(
                modelMapper.map(country, CountryDto.class)));
        model.addAttribute("countryList", countryDtos);
        model.addAttribute("dateAndCountryDto", dateAndCountryDto);
        return "main/main_page";
    }
}

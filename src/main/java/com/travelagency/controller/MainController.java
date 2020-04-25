package com.travelagency.controller;

import com.travelagency.dto.CountryDto;
import com.travelagency.dto.DateAndCountryDto;
import com.travelagency.dto.UserRegisterDto;
import com.travelagency.dto.VisaDto;
import com.travelagency.entity.User;
import com.travelagency.enums.UserRole;
import com.travelagency.exceptions.ResourceNotFoundException;
import com.travelagency.service.CountryService;
import com.travelagency.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private ModelMapper modelMapper;

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
        DateAndCountryDto dateAndCountryDto = new DateAndCountryDto();
        model.addAttribute("dateAndCountryDto",dateAndCountryDto);
        return "userMainPage";
    }

    @PostMapping("/mainPage")
    public String sendUsers(Model model, HttpSession session) {
        System.out.println(session.getAttributeNames());
        return "userMainPage";
    }

    @PostMapping("/freeHotel/{name}")
    public String showHotel(@PathVariable(name = "name")String name,DateAndCountryDto dateAndCountryDto){
        System.out.println(name);
        System.out.println(dateAndCountryDto);
        return "listFreeHotel";
    }

    @GetMapping("/freeHotel/{name}")
    public String showHotesl(@PathVariable(name = "name")String name,DateAndCountryDto dateAndCountryDto){
        System.out.println(name);
        System.out.println(dateAndCountryDto);
        return "listFreeHotel";
    }


    @PostMapping("/freeHotel")
    public String showHlotel(DateAndCountryDto dateAndCountryDto){
        System.out.println(dateAndCountryDto);
        return "listFreeHotel";
    }

    @GetMapping("/freeHotel")
    public String showHotesll(DateAndCountryDto dateAndCountryDto){
        System.out.println(dateAndCountryDto);
        return "listFreeHotel";
    }


    @ModelAttribute("countryList")
    public List<CountryDto> getVisaList() {
        List<CountryDto> countryDtos = new ArrayList<>();
        countryService.getAllCountries().forEach(country -> countryDtos.add(
                modelMapper.map(country, CountryDto.class)));
        return countryDtos;
    }

}

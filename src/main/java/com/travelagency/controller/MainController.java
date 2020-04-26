package com.travelagency.controller;

import com.travelagency.dto.CountryDto;
import com.travelagency.dto.DateAndCountryDto;
import com.travelagency.dto.HotelDto;
import com.travelagency.dto.UserRegisterDto;
import com.travelagency.entity.User;
import com.travelagency.enums.UserRole;
import com.travelagency.exceptions.ResourceNotFoundException;
import com.travelagency.service.CountryService;
import com.travelagency.service.HotelService;
import com.travelagency.service.UserService;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private HotelService hotelService;

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
        model.addAttribute("dateAndCountryDto", dateAndCountryDto);
        return "userMainPage";
    }

    @SneakyThrows
    @PostMapping("/freeHotel/{id}")
    public ModelAndView showFreeHotels(@PathVariable(name = "id") Long id, DateAndCountryDto dateAndCountryDto) {
        List<HotelDto> listHotels = new ArrayList<>();
        CountryDto country = modelMapper.map(countryService.getCountryById(id), CountryDto.class);

        System.out.println(dateAndCountryDto.getSecondDate());
        System.out.println(dateAndCountryDto.getFirstDate());

        LocalDate localDate = dateAndCountryDto.getFirstDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate1 = dateAndCountryDto.getSecondDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println(localDate);
        System.out.println(localDate1);


        hotelService.getAllFreeHotelOnCertainPeriod(id, localDate, localDate1)
                .forEach(hotel -> listHotels.add(modelMapper.map(hotel, HotelDto.class)));

        listHotels.forEach(System.out::println);


        ModelAndView mav = new ModelAndView("country_info");
        mav.addObject("listHotels", listHotels);
        mav.addObject("country", country);
        return mav;
    }

    @ModelAttribute("countryList")
    public List<CountryDto> getVisaList() {
        List<CountryDto> countryDtos = new ArrayList<>();
        countryService.getAllCountries().forEach(country -> countryDtos.add(
                modelMapper.map(country, CountryDto.class)));
        return countryDtos;
    }

}

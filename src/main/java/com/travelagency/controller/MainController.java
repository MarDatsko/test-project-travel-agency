package com.travelagency.controller;

import com.travelagency.dto.*;
import com.travelagency.entity.*;
import com.travelagency.enums.UserRole;
import com.travelagency.exceptions.ResourceNotFoundException;
import com.travelagency.service.*;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
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
    private RoomService roomService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderService orderService;

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
            user.setUserRole(UserRole.ROLE_USER);

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
        List<CountryDto> countryDtos = new ArrayList<>();
        countryService.getAllCountries().forEach(country -> countryDtos.add(
                modelMapper.map(country, CountryDto.class)));
        model.addAttribute("countryList", countryDtos);
        model.addAttribute("dateAndCountryDto", dateAndCountryDto);
        return "userMainPage";
    }

    @SneakyThrows
    @PostMapping("/freeHotel/{id}")
    public ModelAndView showFreeHotels(@PathVariable(name = "id") Long id, DateAndCountryDto dateAndCountryDto) {
        List<HotelDto> listHotels = new ArrayList<>();
        List<HotelDto> list = new ArrayList<>();

        CountryDto country = modelMapper.map(countryService.getCountryById(id), CountryDto.class);

        List<Hotel> allHotelsByCountryId = hotelService.getAllHotelsByCountryId(id);
        allHotelsByCountryId.forEach(hotel -> listHotels.add(modelMapper.map(hotel, HotelDto.class)));

        List<Hotel> allFreeHotelOnCertainPeriod = hotelService.getAllFreeHotelOnCertainPeriod(id, dateAndCountryDto.getFirstDate(), dateAndCountryDto.getSecondDate());
        allFreeHotelOnCertainPeriod.forEach(hotel -> list.add(modelMapper.map(hotel, HotelDto.class)));

        listHotels.removeAll(list);

        ModelAndView mav = new ModelAndView("country_info");
        mav.addObject("listHotels", listHotels);
        mav.addObject("country", country);
        return mav;
    }

    @GetMapping("/reserveRoom/{id}")
    public String reserveRoom(@PathVariable(name = "id") Long id, Model model) {
        ReserveRoom reserveRoom = new ReserveRoom();
        List<RoomDto> roomList = new ArrayList<>();
        roomService.getAllRoomsByHotelId(id).forEach(room -> roomList.add(
                modelMapper.map(room, RoomDto.class)));
        model.addAttribute("roomList", roomList);
        model.addAttribute("hotel", "hotel");
        model.addAttribute("reserveRoom", reserveRoom);
        return "reserveRoom";
    }

    @PostMapping("/reserveRoom/{id}")
    public String reserveRoomSave(@PathVariable(name = "id") Long id, ReserveRoom reserveRoom, Authentication authentication) {

        User userByEmail = userService.getUserByEmail(authentication.getName());
        Order order = new Order();
        order.setStartBooking(reserveRoom.getFirstDate());
        order.setEndBooking(reserveRoom.getSecondDate());
        Room room = new Room();
        room.setId(id);
        order.setRoom(room);
        order.setUser(userByEmail);

        orderService.createOrder(order);

        return "index";
    }
}

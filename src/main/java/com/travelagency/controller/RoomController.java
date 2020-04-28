package com.travelagency.controller;

import com.travelagency.dto.DateAndCountryDto;
import com.travelagency.dto.HotelDto;
import com.travelagency.dto.ReserveRoom;
import com.travelagency.dto.RoomDto;
import com.travelagency.entity.Order;
import com.travelagency.entity.Room;
import com.travelagency.entity.User;
import com.travelagency.service.HotelService;
import com.travelagency.service.OrderService;
import com.travelagency.service.RoomService;
import com.travelagency.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RoomController {

    private final HotelService hotelService;
    private final ModelMapper modelMapper;
    private final RoomService roomService;
    private final UserService userService;
    private final OrderService orderService;

    @Autowired
    public RoomController(HotelService hotelService, ModelMapper modelMapper, RoomService roomService, UserService userService, OrderService orderService) {
        this.hotelService = hotelService;
        this.modelMapper = modelMapper;
        this.roomService = roomService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @PostMapping("/roomStatistic/{id}")
    public String roomStatistic(@PathVariable(name = "id") long id, Model model, DateAndCountryDto dateAndCountryDto) {
        Long roomOccupancy = roomService.getRoomOccupancy(id, dateAndCountryDto.getFirstDate(), dateAndCountryDto.getSecondDate());
        Long numberOfCustomers = hotelService.getCountHotelClient(id);
        Long averageReserveTime = hotelService.getAverageReserveTime(id);
        HotelDto hotelDto = modelMapper.map(hotelService.getHotelById(id), HotelDto.class);
        List<RoomDto> listRooms = new ArrayList<>();
        roomService.getAllRoomsByHotelId(id).forEach(room -> listRooms.add(modelMapper.map(room, RoomDto.class)));
        DateAndCountryDto room = new DateAndCountryDto();
        model.addAttribute("numberOfCustomers", numberOfCustomers);
        model.addAttribute("averageReserveTime", averageReserveTime);
        model.addAttribute("hotel", hotelDto);
        model.addAttribute("listRooms", listRooms);
        model.addAttribute("room", room);
        model.addAttribute("day", "days");
        model.addAttribute("logo", "Room occupancy over a period of time");
        model.addAttribute("statistic", roomOccupancy);
        return "hotel/statistic_hotel";
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
        return "room/reserve_room";
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

        return "main/index";
    }
}

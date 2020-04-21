package com.travelagency.controller;

import com.travelagency.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HotelController {
    @Autowired
    private HotelService hotelService;
}

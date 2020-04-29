package com.travelagency.service;

import com.travelagency.dto.ReserveRoom;
import com.travelagency.entity.Order;
import com.travelagency.entity.User;
import org.springframework.security.core.Authentication;

public interface OrderService {

    Order createOrder(Order order);

    void saveOrder(Long id, ReserveRoom reserveRoom, Authentication authentication, User user);
}

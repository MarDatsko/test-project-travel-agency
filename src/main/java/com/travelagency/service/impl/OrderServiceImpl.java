package com.travelagency.service.impl;

import com.travelagency.dao.OrderDao;
import com.travelagency.dto.ReserveRoom;
import com.travelagency.entity.Order;
import com.travelagency.entity.Room;
import com.travelagency.entity.User;
import com.travelagency.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Order createOrder(Order order) {
        orderDao.createOrder(order);
        return order;
    }

    @Override
    public void saveOrder(Long id, ReserveRoom reserveRoom, Authentication authentication, User user) {
        Order order = new Order();
        order.setStartBooking(reserveRoom.getFirstDate());
        order.setEndBooking(reserveRoom.getSecondDate());
        Room room = new Room();
        room.setId(id);
        order.setRoom(room);
        order.setUser(user);
        createOrder(order);
    }
}

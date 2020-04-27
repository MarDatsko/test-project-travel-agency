package com.travelagency.service.impl;

import com.travelagency.dao.OrderDao;
import com.travelagency.entity.Order;
import com.travelagency.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public Order createOrder(Order order) {
        orderDao.createOrder(order);
        return order;
    }
}

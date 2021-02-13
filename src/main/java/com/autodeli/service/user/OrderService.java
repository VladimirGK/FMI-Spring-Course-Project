package com.autodeli.service.user;

import com.autodeli.web.user.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();

    Order getOrderById(String id);

    List<Order> getAllOrdersForUser(String id);

    Order addOrder(Order order);

    Order updateOrder(Order order);

    Order deleteOrder(String id);
}

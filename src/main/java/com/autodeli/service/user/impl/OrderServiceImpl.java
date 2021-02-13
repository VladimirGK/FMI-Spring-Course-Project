package com.autodeli.service.user.impl;

import com.autodeli.exception.EntityNotFoundException;
import com.autodeli.repository.user.OrderRepository;
import com.autodeli.service.user.OrderService;
import com.autodeli.web.user.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(String id) {
        return orderRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Order with ID:%s does not exist.", id)));
    }

    @Override
    public List<Order> getAllOrdersForUser(String id) {
        return orderRepository.findAllByUserId(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("User with ID:%s does not have any orders.", id)));
    }

    @Override
    public Order addOrder(Order order) {
        return orderRepository.insert(order);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order deleteOrder(String id) {
        Order removed = getOrderById(id);
        orderRepository.delete(removed);
        return removed;
    }
}

package com.autodeli.controller.user;

import com.autodeli.exception.InvalidEntityDataException;
import com.autodeli.service.user.OrderService;
import com.autodeli.service.user.UserService;
import com.autodeli.web.user.Order;
import com.autodeli.web.user.ShoppingCart;
import com.autodeli.web.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable("id") String id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order created = orderService.addOrder(order);
        userService.emptyShoppingCartForUser(order.getUserId());

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}")
                        .buildAndExpand(created.getId()).toUri()
        ).body(created);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable("id") String id, @RequestBody Order order) {
        if (!id.equals(order.getId())) {
            throw new InvalidEntityDataException(
                    String.format("Order URL ID:%s differs from body entity ID:%s", id, order.getId()));
        }
        return orderService.updateOrder(order);
    }

    @DeleteMapping("/{id}")
    public Order deleteOrder(@PathVariable("id") String id) {
        Order removed = getOrderById(id);
        orderService.deleteOrder(id);
        return removed;
    }

    @GetMapping("/user/{userId}")
    public List<Order> getOrdersForUser(@PathVariable("userId") String userId) {
        return orderService.getAllOrdersForUser(userId);
    }
}

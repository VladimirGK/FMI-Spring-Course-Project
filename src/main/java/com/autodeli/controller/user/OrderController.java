package com.autodeli.controller.user;

import com.autodeli.exception.InvalidEntityDataException;
import com.autodeli.service.EmailService;
import com.autodeli.service.user.OrderService;
import com.autodeli.service.user.UserService;
import com.autodeli.web.car.AutoPart;
import com.autodeli.web.consumable.Battery;
import com.autodeli.web.consumable.Oil;
import com.autodeli.web.consumable.Supplement;
import com.autodeli.web.user.Order;
import com.autodeli.web.user.ShoppingCart;
import com.autodeli.web.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
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
    private final EmailService emailService;

    @Autowired
    public OrderController(EmailService emailService, OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
        this.emailService = emailService;
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
        sendMail(order);

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}")
                        .buildAndExpand(created.getId()).toUri()
        ).body(created);
    }

    private void sendMail(Order order) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("autodelibg@gmail.com");
        message.setSubject("New order");
        message.setText(getMailTextBody(order));
        emailService.sendMail(message);
    }

    private String getMailTextBody(Order order) {
        User user = userService.getUserById(order.getUserId());
        StringBuilder text = new StringBuilder(String.format("New order made for user: %s\n\nFirst name: %s\nLast name: %s\nEmail: %s\nCity: %s\nAddress: %s\nPhone number: %s\n---Products---\n",
                order.getUserId(), !order.getFirstName().equals("") ? order.getFirstName() : user.getFirstName(),
                !order.getLastName().equals("") ? order.getLastName() : user.getLastName(), user.getEmail(), order.getCity(),
                order.getAddress(), order.getNumber()));

        for(AutoPart autoPart : order.getAutoParts()) {
            text.append(String.format("   * AutoPart: %s\n", autoPart.getId()));
        }
        for(Oil oil : order.getOils()) {
            text.append(String.format("   * Oil: %s\n", oil.getId()));
        }
        for(Battery battery : order.getBatteries()) {
            text.append(String.format("   * Battery: %s\n", battery.getId()));
        }
        for(Supplement supplement : order.getSupplements()) {
            text.append(String.format("   * Supplement: %s\n", supplement.getId()));
        }
        text.append(String.format("\nTotal price: %s\n", order.getTotal()));
        return text.toString();
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

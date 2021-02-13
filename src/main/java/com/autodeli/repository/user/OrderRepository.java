package com.autodeli.repository.user;

import com.autodeli.web.user.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {
    Optional<List<Order>> findAllByUserId(String userId);
}

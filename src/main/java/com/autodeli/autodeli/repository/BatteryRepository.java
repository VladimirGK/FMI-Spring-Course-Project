package com.autodeli.autodeli.repository;

import com.autodeli.autodeli.web.Battery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatteryRepository extends MongoRepository<Battery, String> {

}

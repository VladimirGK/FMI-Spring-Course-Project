package com.autodeli.repository.consumable;

import com.autodeli.web.consumable.Battery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatteryRepository extends MongoRepository<Battery, String> {

}

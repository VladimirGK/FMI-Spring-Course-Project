package com.autodeli.repository.car;

import com.autodeli.web.car.Engine;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EngineRepository extends MongoRepository<Engine, String> {
    List<Engine> findAllByBrandNameAndModelName(String brandName, String modelName);
    Engine findByName(String name);
}

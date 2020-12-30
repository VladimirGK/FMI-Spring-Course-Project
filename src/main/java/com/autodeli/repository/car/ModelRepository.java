package com.autodeli.repository.car;

import com.autodeli.web.car.Model;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ModelRepository extends MongoRepository<Model, String> {
    List<Model> findAllByBrandName(String name);
    Model findByName(String name);
}

package com.autodeli.repository.car;

import com.autodeli.web.car.AutoPart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AutoPartRepository extends MongoRepository<AutoPart, String> {
    List<AutoPart> findAllByEngineName(String name);
    AutoPart findByName(String name);

    List<AutoPart> findAllByBrandNameAndModelNameAndEngineName(String brandName, String modelName, String engineName);
}

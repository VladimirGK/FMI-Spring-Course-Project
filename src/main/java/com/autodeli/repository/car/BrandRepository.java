package com.autodeli.repository.car;

import com.autodeli.web.car.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends MongoRepository<Brand, String> {
  Brand findBrandByName(String name);
}

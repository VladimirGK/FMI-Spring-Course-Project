package com.autodeli.repository.consumable;

import com.autodeli.web.consumable.Oil;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OilRepository extends MongoRepository<Oil, String> {

}

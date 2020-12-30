package com.autodeli.repository.consumable;

import com.autodeli.web.consumable.Supplement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplementRepository extends MongoRepository<Supplement, String> {

}

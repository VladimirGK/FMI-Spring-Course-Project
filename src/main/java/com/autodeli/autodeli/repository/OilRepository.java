package com.autodeli.autodeli.repository;

import com.autodeli.autodeli.web.Oil;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OilRepository extends MongoRepository<Oil, String> {

}

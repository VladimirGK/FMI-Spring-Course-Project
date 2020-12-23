package com.autodeli.autodeli.repository;

import com.autodeli.autodeli.web.Supplement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplementRepository extends MongoRepository<Supplement, String> {

}

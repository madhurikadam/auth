package com.unyx.auth.repository;

import com.unyx.auth.model.AuthCollection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthCollectionRepository extends MongoRepository<AuthCollection,String> {
    public AuthCollection findByUserId(String userId);

}

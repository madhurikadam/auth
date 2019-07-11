package com.unyx.auth.repository;

import com.unyx.auth.model.AuthClient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AuthClientRepository  extends MongoRepository<AuthClient,String> {
    public AuthClient findByClientId(String clientId);
    public List<AuthClient> findByAppName(String appName);
    public AuthClient findByClientIdAndClientSecret(String clientId, String clientSecret);

}

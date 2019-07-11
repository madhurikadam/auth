package com.unyx.auth.controller;


import com.unyx.auth.model.AuthRequest;
import com.unyx.auth.model.AuthResp;
import com.unyx.auth.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/token",consumes = MediaType.ALL_VALUE)
    public AuthResp CreateToken(@Valid @RequestBody AuthRequest client) {
            logger.info("Started creating auth token");
            AuthResp response = authService.process(client);
            return response;
    }
}

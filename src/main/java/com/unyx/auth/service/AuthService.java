package com.unyx.auth.service;

import com.unyx.auth.exception.*;
import com.unyx.auth.model.*;
import com.unyx.auth.repository.AuthClientRepository;
import com.unyx.auth.repository.AuthCollectionRepository;
import com.unyx.auth.security.JwtTokenProvider;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.Map;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    @Autowired
    private AuthClientRepository authClientRepository;

    @Autowired
   private AuthCollectionRepository authCollectionRepository;

    @Autowired
    private FacebookTokenService facebookTokenService;

    @Autowired
    private OTPService otpService;

    public AuthResp process(AuthRequest request) {

        if (StringUtils.isEmpty(request.getClientId()) | StringUtils.isEmpty(request.getClientSecret() == null)) {
            logger.error("Client Id or Client Secret is missing");
            throw new AuthClientException("Client Id or Client Secret is missing");
        }
        //AuthClient client = new AuthClient(request.getClientId(),request.getClientSecret());
        AuthClient clientResp = authClientRepository.findByClientIdAndClientSecret(request.getClientId(), request.getClientSecret());
        if (clientResp != null) {
            if (request.getGrantType().equals(GrantType.FB_TOKEN.getType()) && request.getFbToken() != null) {
                logger.info("Facebook Token based authentication");
                Map<String, Object> jsonResponse = facebookTokenService.execute(request.getFbToken());

            } else if (request.getGrantType().equals(GrantType.OTP.getType())) {
                logger.info("OTP based authentication");
               // OTPService otpService = new OTPService();
                //TODO otp based authentication
                Boolean verityOtp= otpService.validateOtp(request.getVerificationRef(),request.getPhoneNumber());
                if(verityOtp == false){
                    throw  new OTPException("Failed to verify OTP");
                }
            } else {
                logger.error("Invalid Grant Type");
                throw new InvalideGrantTypeException();
            }
            // TODO Add logic to call the seller profile api and get the userid from that and pass it to for creating JWT token
            clientResp.getAppName();
            String userId = "23456";
            JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(request.getClientSecret());
            AuthResp tokenResp;
            try {
                System.out.println(request.getScopes());
                tokenResp = jwtTokenProvider.createToken(userId, request.getScopes());
            } catch (Exception e) {
                logger.error("Error while creating JWT token", e);
                throw new JwtCustomException("Error while creating JWT Token" + e.getMessage());
            }
            AuthCollection authCollection = new AuthCollection();
            authCollection.setUserId(userId);
            authCollection.setScope(request.getScopes());
            authCollection.setUnyxToken(tokenResp.getToken());
            authCollection.setUnyxTokenExpiry(tokenResp.getTimeExpiry());
            authCollection.setUserType(request.getUserType());
            authCollectionRepository.insert(authCollection);
            return tokenResp;
        } else {
            logger.error("Failed to Create Authentication Token,Invalid Client Id or Secret.");
            throw new InvalidClientSecret("Failed to Create Authentication Token,Invalid Client Id or Secret.");

        }

    }
    public static void main(String args[]) throws JSONException {
        JSONObject data = new JSONObject();
        data.put("headers",new JSONObject());
        System.out.println(data.length());
        if(data.get("headers") != "{}"){
            System.out.println(data);
            JSONObject j = (JSONObject) data.get("headers");
            Iterator keys = j.keys();
            System.out.println(keys);
            System.out.println(j.length());
            if (j.length()==0) {
                System.out.println(data);
            }
        }

    }
}

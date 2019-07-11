package com.unyx.auth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.unyx.auth.exception.FacebookTokenServiceException;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.ssl.SSLContexts;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import static com.unyx.auth.constant.Constant.*;


@Service
@PropertySource("classpath:application.properties")
public class FacebookTokenService {

    // TODO need to take client id and secret of facebook from configuration for now its constant
    private static final Logger logger = LoggerFactory.getLogger(FacebookTokenService.class);

    public Map<String,Object> execute(String token) {
        HttpResponse<JsonNode> userInfoResp = validateFbToken(token);
        checkErrorResp(userInfoResp);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> userInfo = mapper.convertValue(userInfoResp.getBody(), Map.class);
        if (!userInfo.containsKey("email")) {
            throw new FacebookTokenServiceException("Invalid Facebook APP permission. Please add required permission");
        }
        HttpResponse<JsonNode> longLivedToken = null;
        try {
            longLivedToken = convertToLongLivedToken(token);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        checkErrorResp(longLivedToken);
        userInfo.putAll(mapper.convertValue(longLivedToken.getBody(), Map.class));

        return userInfo;
    }

    public void checkErrorResp(HttpResponse<JsonNode> jsonResponse) {
        if (jsonResponse.getStatus() != 200) {
            String errorMsg;
            JSONObject respObj = jsonResponse.getBody().getObject();
            JSONObject error = (JSONObject) respObj.get("error");
            errorMsg = (String) error.get("message");
            logger.error("Error Message: " + errorMsg);
            logger.error(jsonResponse.getBody().toString());
            logger.error(String.valueOf(jsonResponse.getStatus()));
            throw new FacebookTokenServiceException(errorMsg);
        }
    }

    private HttpResponse<JsonNode> convertToLongLivedToken(String token) throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        HttpResponse<JsonNode> jsonResponse = null;
        SSLContext sslcontext = SSLContexts.custom()
                .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                .build();
        try {
            jsonResponse = Unirest.get(GRAPH_API_URL + "/oauth/access_token")
                    .queryString(GRANT_TYPE, FB_EXCHANGE_TOKEN).queryString(CLIENT_ID, FB_CLIENT).queryString(CLIENT_SECRET, FB_SECRET)
                    .queryString(FB_EXCHANGE_TOKEN, token)
                    .asJson();
            logger.info(jsonResponse.getBody().toString());
        } catch (UnirestException ex) {
            logger.error("Failed to exchange long lived token " + ex.getMessage());
            throw new FacebookTokenServiceException("Failed to exchange long lived token. " + ex.getMessage());
        } catch (Exception ex) {
            logger.error("Failed to exchange long lived token " + ex.getMessage());
            throw new FacebookTokenServiceException("Failed to exchange long lived token. " + ex.getMessage());
        }

        return jsonResponse;

    }

    private HttpResponse<JsonNode> validateFbToken(String fbToken) {
        HttpResponse<JsonNode> jsonResponse = null;
        try {
            jsonResponse = Unirest.get(GRAPH_API_URL + "/me").queryString("fields", "email,gender,birthday,location,last_name,first_name").asJson();
        } catch (UnirestException e) {
            logger.error("Failed to validate facebook token" + e.getMessage());
            throw new FacebookTokenServiceException("Invalid validate facebook token. Failed to validate Facebook user token");
        } catch (Exception e) {
            logger.error("Failed to validate facebook token" + e.getMessage());
            throw new FacebookTokenServiceException("Invalid validate facebook token. Failed to validate Facebook user token");

        }
        return jsonResponse;
    }

}





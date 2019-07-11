package com.unyx.auth.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class AuthRequest {

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getFbToken() {
        return fbToken;
    }

    public void setFbToken(String fbToken) {
        this.fbToken = fbToken;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVerificationRef() {
        return verificationRef;
    }

    public void setVerificationRef(String verificationRef) {
        this.verificationRef = verificationRef;
    }

    public List<String> getScopes() {
        return scope;
    }

    public void setScopes(ArrayList<String> scopes) {
        this.scope = scopes;
    }

    @JsonCreator
    public AuthRequest(@JsonProperty("clientId") String clientId, @JsonProperty("scope") List<String> scope, @JsonProperty("clientSecret") String clientSecret, @JsonProperty("grantType") String grantType, @JsonProperty("fbToken") String fbToken, @JsonProperty("userType") String userType, @JsonProperty("phoneNumber") String phoneNumber, @JsonProperty("verificationRef") String verificationRef) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.grantType = grantType;
        this.fbToken = fbToken;
        this.userType = userType;
        this.phoneNumber = phoneNumber;
        this.verificationRef = verificationRef;
        this.scope = scope;
    }

    @NotBlank
    @JsonProperty
    String clientId;
    @NotBlank
    @JsonProperty
    String clientSecret;
    @NotBlank
    @JsonProperty
    String grantType;
    @JsonProperty
    String fbToken;
    @JsonProperty
    @NotBlank
    String userType;
    @JsonProperty
    String phoneNumber;
    @JsonProperty
    String verificationRef;
    @JsonProperty
    @NotNull
    List<String> scope;
}

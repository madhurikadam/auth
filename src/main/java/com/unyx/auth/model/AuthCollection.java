package com.unyx.auth.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Document("auth_collection")
public class AuthCollection {

//    userId, unyxScopes ["seller_all"], unyxTokenExpiry,
//    unyxToken (JWT), tokenActive, userType (customer/seller)

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getScope() {
        return scope;
    }

    public void setScope(List<String> scope) {
        this.scope = scope;
    }

    public long getUnyxTokenExpiry() {
        return unyxTokenExpiry;
    }

    public void setUnyxTokenExpiry(long unyxTokenExpiry) {
        this.unyxTokenExpiry = unyxTokenExpiry;
    }

    public String getUnyxToken() {
        return unyxToken;
    }

    public void setUnyxToken(String unyxToken) {
        this.unyxToken = unyxToken;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public AuthCollection() {

    }

    public AuthCollection(@NotBlank String userId, List<String> scope, @NotNull long unyxTokenExpiry, @NotNull String unyxToken, @NotNull String userType) {
        this.userId = userId;
        this.scope = scope;
        this.unyxTokenExpiry = unyxTokenExpiry;
        this.unyxToken = unyxToken;
        this.userType = userType;
    }

    @NotBlank
    private String userId;

    //@NotBlank.List()
    private List<String> scope;

    @NotNull
    private long unyxTokenExpiry;

    @NotNull
    private String unyxToken;

    @NotNull
    private String userType;
}

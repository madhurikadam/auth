package com.unyx.auth.model;

public class AuthResp {
    String token;
    long timeExpiry;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getTimeExpiry() {
        return timeExpiry;
    }

    public void setTimeExpiry(long timeExpiry) {
        this.timeExpiry = timeExpiry;
    }

    public AuthResp(String token, long timeExpiry) {
        this.token = token;
        this.timeExpiry = timeExpiry;
    }
}

package com.unyx.auth.model;

public enum GrantType {
    FB_TOKEN("fb_token"),
    OTP("otp");

    private String type;

    GrantType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

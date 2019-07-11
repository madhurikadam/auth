package com.unyx.auth.model;

public enum Scopes {
     SELLER_ALL("seller_all"),
     CUSTOMER_ALL("customer_all");

     String scope;

    Scopes(String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return scope;
    }

}

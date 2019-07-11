package com.unyx.auth.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "auth_client")
public class AuthClient {

    @Id
    String id;

    @NotNull
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("clientId")
    String clientId;

    public AuthClient(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }
    public AuthClient(){

    }
    public AuthClient(String id, @NotNull String clientId, @NotNull String clientSecret, Boolean unyxApp, String appName, String appLogo) {
        this.id = id;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.unyxApp = unyxApp;
        this.appName = appName;
        this.appLogo = appLogo;
    }

    @NotNull
    @JsonProperty("clientSecret")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    String clientSecret;

    Boolean unyxApp;

    String appName;

    String appLogo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Boolean getUnyxApp() {
        return unyxApp;
    }

    public void setUnyxApp(Boolean unyxApp) {
        this.unyxApp = unyxApp;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppLogo() {
        return appLogo;
    }

    public void setAppLogo(String appLogo) {
        this.appLogo = appLogo;
    }

}

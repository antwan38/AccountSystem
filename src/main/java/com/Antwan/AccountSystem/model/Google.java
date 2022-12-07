package com.Antwan.AccountSystem.model;

public class Google {

    private String client_id;
    private String client_secret;
    private String grant_type;
    private String code;
    private String redirect_uri;

    public Google(String client_id, String client_secret, String code) {
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.code = code;
        this.redirect_uri = "http://localhost:8081/auth/google/code";
        this.grant_type = "authorization_code";
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }


//    "client_id": "664876581380-n5alegucma18cp4dk5enckptljaq94p7.apps.googleusercontent.com",
//            "client_secret" : "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",
//            "grant_type": "authorization_code",
//            "code": "4/0AfgeXvu224nXfrPsafgCJ8r1o92TdzTyL_lH7crkCjjiGsAzhIrG_BUuCKLee6vUL5fKLg",
//            "redirect_uri" : "http://localhost:8081/auth/google/info"


}

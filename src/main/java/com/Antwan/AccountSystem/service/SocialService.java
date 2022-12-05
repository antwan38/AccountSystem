package com.Antwan.AccountSystem.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.social.connect.web.ConnectSupport;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.NativeWebRequest;

@Service
public class SocialService {
    private Environment environment;
    private GoogleConnectionFactory googleConnectionFactory;
    private FacebookConnectionFactory facebookConnectionFactory;

    private final ConnectSupport connectSupport = new ConnectSupport();

    public SocialService(Environment environment){
        this.environment = environment;
        this.facebookConnectionFactory = new FacebookConnectionFactory(this.environment.getProperty("spring.social.facebook.appId"), this.environment.getProperty("spring.social.facebook.appSecret"));
        this.googleConnectionFactory = new GoogleConnectionFactory(this.environment.getProperty("spring.social.google.appId"), this.environment.getProperty("spring.social.google.appSecret"));
    }
    public String googleAuthUrl(NativeWebRequest request) {
        connectSupport.setCallbackUrl("http://localhost:8081/auth/google/info");
        MultiValueMap<String, String> additionalParameters = new LinkedMultiValueMap<>();
        additionalParameters.add("scope", "https://www.googleapis.com/auth/cloud-platform.read-only");
        return connectSupport.buildOAuthUrl(googleConnectionFactory, request, additionalParameters);

    }

    public String facebookAuthUrl(NativeWebRequest request) {
        connectSupport.setCallbackUrl("http://localhost:8081/auth/facebook/info");
        return connectSupport.buildOAuthUrl(facebookConnectionFactory, request);
    }

    public void googleGetInfo(NativeWebRequest request){
        connectSupport.completeConnection(googleConnectionFactory, request);
    }

    public void facebookGetInfo(NativeWebRequest request){
        connectSupport.completeConnection(facebookConnectionFactory, request);
    }
}

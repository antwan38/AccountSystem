package com.Antwan.AccountSystem.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.connect.web.ConnectSupport;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/auth")
public class AuthController {

    @Value("${spring.social.google.appId}")
    private String googleId;
    @Value("${spring.social.facebook.appId}")
    private String facebookId;
    @Value("${spring.social.google.appSecret}")
    private String googleSecret;
    @Value("${spring.social.facebook.appSecret}")
    private String facebookSecret;
    private MultiValueMap<String, String> additionalParameters = new LinkedMultiValueMap<>();

    private final ConnectSupport connectSupport;

    public AuthController() {
        this.connectSupport = new ConnectSupport();
        this.additionalParameters.add("profile", "profile");
        this.additionalParameters.add("email", "email");

    }

    @GetMapping("/google")
    public String googleAuthUrl(NativeWebRequest request) {
        return connectSupport.buildOAuthUrl(new GoogleConnectionFactory(googleId, googleSecret), request, additionalParameters);
    }

    @GetMapping("/facebook")
    public String facebookAuthUrl(NativeWebRequest request) {
        return connectSupport.buildOAuthUrl(new FacebookConnectionFactory(facebookId, facebookSecret), request);
    }
}

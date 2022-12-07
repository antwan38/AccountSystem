package com.Antwan.AccountSystem.service;

import com.Antwan.AccountSystem.model.Facebook;
import com.Antwan.AccountSystem.model.Google;
import com.Antwan.AccountSystem.repository.FacebookDal;
import com.Antwan.AccountSystem.repository.GoogleDal;
import com.fasterxml.jackson.databind.JsonNode;
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
    private final GoogleDal googleDal;
    private final FacebookDal facebookDal;

    private final ConnectSupport connectSupport = new ConnectSupport();

    public SocialService(Environment environment, GoogleDal googleDal, FacebookDal facebookDal){
        this.facebookDal = facebookDal;
        this.googleDal = googleDal;
        this.environment = environment;
        this.facebookConnectionFactory = new FacebookConnectionFactory(this.environment.getProperty("spring.social.facebook.appId"), this.environment.getProperty("spring.social.facebook.appSecret"));
        this.googleConnectionFactory = new GoogleConnectionFactory(this.environment.getProperty("spring.social.google.appId"), this.environment.getProperty("spring.social.google.appSecret"));
    }
    public String googleAuthUrl(NativeWebRequest request) {
        connectSupport.setCallbackUrl("http://localhost:8081/auth/google/code");
        MultiValueMap<String, String> additionalParameters = new LinkedMultiValueMap<>();
        additionalParameters.add("scope", "https://www.googleapis.com/auth/cloud-platform.read-only");
        additionalParameters.add("access_type", "offline");
        return connectSupport.buildOAuthUrl(googleConnectionFactory, request, additionalParameters);

    }

    public String facebookAuthUrl(NativeWebRequest request) {
        connectSupport.setCallbackUrl("http://localhost:8081/auth/facebook/code");
        return connectSupport.buildOAuthUrl(facebookConnectionFactory, request);
    }


    public void googleAccessTokenRequest(String code){
        Google google = new Google(this.environment.getProperty("spring.social.google.appId"), this.environment.getProperty("spring.social.google.appSecret"), code);
        googleDal.getAccessToken(google);
    }

    public JsonNode facebookAccessTokenRequest(String code){
        Facebook facebook = new Facebook(this.environment.getProperty("spring.social.facebook.appId"), this.environment.getProperty("spring.social.facebook.appSecret"), code);
        return getFacebookUserInfo(facebookDal.getAccessToken(facebook));
    }

//    public JsonNode getGoogleUserInfo(String accessToken){
//        return googleDal.getUserInfo(accessToken);
//    }

    public JsonNode getFacebookUserInfo(String accessToken){
        return facebookDal.getUserInfo(accessToken);
    }
}



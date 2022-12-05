package com.Antwan.AccountSystem.repository;

import org.springframework.social.google.api.Google;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class googleDal {
    private final RestTemplate restTemplate = new RestTemplate();

    public void getAccessToken(Google google) {
        String url = "https://oauth2.googleapis.com/token";
        try {
            return restTemplate.p
        } catch (Exception e) {
            return null;
        }

    }
}

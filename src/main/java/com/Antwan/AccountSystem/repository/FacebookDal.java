package com.Antwan.AccountSystem.repository;

import com.Antwan.AccountSystem.model.Facebook;
import com.Antwan.AccountSystem.model.Google;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class FacebookDal {
    public void getAccessToken(Facebook facebook) {
        try {

            ObjectMapper mapper = new ObjectMapper();
            System.out.println(mapper.writeValueAsString(facebook));

            // send a JSON data
            String res = WebClient.builder()
                    .baseUrl("https://graph.facebook.com/v8.0/oauth/access_token")
                    .build()
                    .post()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(mapper.writeValueAsString(facebook)))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            System.out.println("Response: " + res);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}

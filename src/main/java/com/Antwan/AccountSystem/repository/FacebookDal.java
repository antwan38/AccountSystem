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
    public String getAccessToken(Facebook facebook) {
        try {

            ObjectMapper mapper = new ObjectMapper();
            System.out.println(mapper.writeValueAsString(facebook));

            // send a JSON data
             JsonNode res = WebClient.builder()
                    .baseUrl("https://graph.facebook.com/v8.0/oauth/access_token")
                    .build()
                    .post()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(mapper.writeValueAsString(facebook)))
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block();

            System.out.println("Response: " + res.get("access_token").asText());
            return res.get("access_token").asText();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public JsonNode getUserInfo(String access_token) {
        try {

            ObjectMapper mapper = new ObjectMapper();
            System.out.println(mapper.writeValueAsString(access_token));

            // send a JSON data
            JsonNode res = WebClient.builder()
                    .baseUrl("https://graph.facebook.com/v15.0/me?fields=email,name")
                    .build()
                    .post()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(mapper.writeValueAsString(access_token)))
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block();

            return res;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

}

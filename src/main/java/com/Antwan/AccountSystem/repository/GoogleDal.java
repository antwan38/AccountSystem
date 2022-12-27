package com.Antwan.AccountSystem.repository;

import com.Antwan.AccountSystem.model.AccessToken;
import com.Antwan.AccountSystem.model.Google;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class GoogleDal {

    public String getAccessToken(Google google) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            System.out.println(mapper.writeValueAsString(google));

            // send a JSON data
            JsonNode res = WebClient.builder()
                    .baseUrl("https://oauth2.googleapis.com/token")
                    .build()
                    .post()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(mapper.writeValueAsString(google)))
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block();

            return res.get("access_token").asText();
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return "error";
        }

    }

    public JsonNode getUserInfo(String access_token) {
        try {

            ObjectMapper mapper = new ObjectMapper();
            AccessToken accessToken = new AccessToken(access_token);
            System.out.println(mapper.writeValueAsString(accessToken));

            // send a JSON data
            JsonNode res = WebClient.builder()
                    .baseUrl("https://www.googleapis.com/oauth2/v1/userinfo?alt=json")
                    .build()
                    .post()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(mapper.writeValueAsString(accessToken)))
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

package com.Antwan.AccountSystem.repository;

import com.Antwan.AccountSystem.model.Google;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class GoogleDal {

    public void getAccessToken(Google google) {
        try {
           ObjectMapper mapper = new ObjectMapper();
            System.out.println(mapper.writeValueAsString(google));

            // send a JSON data
            String res = WebClient.builder()
                    .baseUrl("https://oauth2.googleapis.com/token")
                    .build()
                    .post()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(mapper.writeValueAsString(google)))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            System.out.println("Response: " + res);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }

    }
}

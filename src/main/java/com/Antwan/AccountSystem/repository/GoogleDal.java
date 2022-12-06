package com.Antwan.AccountSystem.repository;

import com.Antwan.AccountSystem.model.Google;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class GoogleDal {
    private final RestTemplate restTemplate = new RestTemplate();

    public void getAccessToken(Google google) {
        try {
            String result = "";
            HttpPost post = new HttpPost("https://oauth2.googleapis.com/token");

            StringBuilder json = new StringBuilder();
            json.append("{");
            json.append("client_id : " +google.getClient_id());
            json.append("code : " +google.getCode());
            json.append("client_secret : " +google.getClient_secret());
            json.append("grant_type : " +google.getGrant_type());
            json.append("redirect_uri : " +google.getRedirect_uri());
            json.append("}");

            // send a JSON data
            post.setEntity(new StringEntity(json.toString()));

            try (CloseableHttpClient httpClient = HttpClients.createDefault();
                 CloseableHttpResponse response = httpClient.execute(post)) {

                result = EntityUtils.toString(response.getEntity());
            }

            System.out.println(result);

        } catch (Exception e) {

        }

    }
}

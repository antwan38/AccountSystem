package com.Antwan.AccountSystem.repository;

import com.Antwan.AccountSystem.model.Facebook;
import com.Antwan.AccountSystem.model.Google;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Repository;

@Repository
public class FacebookDal {
    public void getAccessToken(Facebook facebook) {
        try {
            String result = "";
            HttpPost post = new HttpPost("https://graph.facebook.com/v8.0/oauth/access_token");

            StringBuilder json = new StringBuilder();
            json.append("{");
            json.append("\"client_id\" : " + "\"" +facebook.getClient_id()+"\",");
            json.append("\"code\" : " + "\"" +facebook.getCode()+"\",");
            json.append("\"client_secret\" : " + "\"" +facebook.getClient_secret()+"\",");
            json.append("\"grant_type\" : " + "\"" +facebook.getGrant_type()+"\",");
            json.append("\"redirect_uri\" : " + "\"" +facebook.getRedirect_uri()+"\"");
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

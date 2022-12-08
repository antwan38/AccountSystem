package com.Antwan.AccountSystem.controller;

import com.Antwan.AccountSystem.service.SocialService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Map;


@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/auth")
public class AuthController {



    private final SocialService socialService;
    public AuthController(SocialService socialService){
        this.socialService = socialService;
    }

    @GetMapping("/google")
    public String googleAuthUrl(NativeWebRequest request) {
       return socialService.googleAuthUrl(request);
    }

    @GetMapping("/facebook")
    public String facebookAuthUrl(NativeWebRequest request) {
        return socialService.facebookAuthUrl(request);
    }

    @GetMapping("/google/code")
    public String googleGetToken(@RequestParam("code") String code){
        try {
            String result = java.net.URLDecoder.decode(code, StandardCharsets.UTF_8.name());
            JsonNode userInfo = socialService.googleAccessTokenRequest(result);
            return result; //userInfo.get("name").asText() + userInfo.get("email").asText();

        } catch (UnsupportedEncodingException e) {
            // not going to happen - value came from JDK's own StandardCharsets
            return "error";
        }

    }
    @GetMapping("/facebook/code")
    public String facebookGetToken(@RequestParam("code") String code){
        try {
            String result = java.net.URLDecoder.decode(code, StandardCharsets.UTF_8.name());
            JsonNode userInfo = socialService.facebookAccessTokenRequest(result);
            return userInfo.get("name").asText() + userInfo.get("email").asText();

        } catch (UnsupportedEncodingException e) {
            // not going to happen - value came from JDK's own StandardCharsets
            return "error";
        }

    }

}

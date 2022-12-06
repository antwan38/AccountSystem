package com.Antwan.AccountSystem.controller;

import com.Antwan.AccountSystem.service.SocialService;
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
    public String googleGetToken(NativeWebRequest request, @RequestParam("code") String code){
        try {
            String result = java.net.URLDecoder.decode(code, StandardCharsets.UTF_8.name());
            //socialService.googleAccessTokenRequest(result);
            return result;

        } catch (UnsupportedEncodingException e) {
            // not going to happen - value came from JDK's own StandardCharsets
            return "error";
        }

    }
    @GetMapping("/facebook/code")
    public String facebookGetToken(NativeWebRequest request, @RequestParam("code") String code){
        try {
            String result = java.net.URLDecoder.decode(code, StandardCharsets.UTF_8.name());
            socialService.facebookAccessTokenRequest(result);
            return result;

        } catch (UnsupportedEncodingException e) {
            // not going to happen - value came from JDK's own StandardCharsets
            return "error";
        }

    }

    @GetMapping("/facebook/info")
    public void facebookGetInfo(@RequestBody Map<String, String> data){
        System.out.println(data.get("access_token"));
    }

    @GetMapping("/google/info")
    public void googleGetUserInfo(@RequestBody Map<String, String> data){
        System.out.println(data.get("access_token"));
    }
}

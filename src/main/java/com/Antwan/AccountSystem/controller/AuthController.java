package com.Antwan.AccountSystem.controller;

import com.Antwan.AccountSystem.service.SocialService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.yaml.snakeyaml.util.UriEncoder;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;


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
    public String googleGetInfo(NativeWebRequest request, @RequestParam("code") String code){
        try {
            String result = java.net.URLDecoder.decode(code, StandardCharsets.UTF_8.name());
            socialService.googleAccessTokenRequest(result);
            return "ok";

        } catch (UnsupportedEncodingException e) {
            // not going to happen - value came from JDK's own StandardCharsets
            return "error";
        }
        //socialService.googleGetInfo(request);
    }

    @GetMapping("/facebook/info")
    public void facebookGetInfo(NativeWebRequest request){
        socialService.facebookGetInfo(request);
    }
}

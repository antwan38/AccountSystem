package com.Antwan.AccountSystem.controller;

import com.Antwan.AccountSystem.service.SocialService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;


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

    @GetMapping("/google/info")
    public void googleGetInfo(NativeWebRequest request){
        socialService.googleGetInfo(request);
    }

    @GetMapping("/facebook/info")
    public void facebookGetInfo(NativeWebRequest request){
        socialService.facebookGetInfo(request);
    }
}

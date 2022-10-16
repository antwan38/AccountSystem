package com.Antwan.AccountSystem.controller;

import com.Antwan.AccountSystem.model.google;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;
import java.util.ResourceBundle;

@RestController
@RequestMapping("/client")
public class clientController {


    @CrossOrigin("http://localhost:4200/")
    @GetMapping("/")
    public google getClientId(){

        ResourceBundle rs = ResourceBundle.getBundle("social");

        String prop = rs.getString("google.client.id");
        google go = new google(prop);
        return go;


    }

}

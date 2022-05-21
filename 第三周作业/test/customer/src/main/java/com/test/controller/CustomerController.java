package com.test.controller;


import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/customer")
    public void sortMethod(){
        String url = "http://localhost:18081/sort";
        List<Integer> sortList = restTemplate.getForObject(url,List.class);
        System.out.println(sortList);

    }
}

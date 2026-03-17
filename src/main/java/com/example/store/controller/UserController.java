package com.example.store.controller;

import com.example.store.model.IpApiResponse;
import com.example.store.model.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
public class UserController {

    private final RestTemplate restTemplate;

    public UserController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/info")
    public UserInfo getUserInfo(
            HttpServletRequest request,
            @RequestHeader(value = "User-Agent") String userAgent) {

        String ip = request.getRemoteAddr();
        
        // Handle local testing (localhost IP)
        if (ip.equals("0:0:0:0:0:0:0:1") || ip.equals("127.0.0.1")) {
            // Use a sample public IP for testing if on localhost
            ip = "8.8.8.8"; 
        }

        String url = "http://ip-api.com/json/" + ip;
        String country = "Unknown";
        
        try {
            IpApiResponse response = restTemplate.getForObject(url, IpApiResponse.class);
            if (response != null && "success".equals(response.getStatus())) {
                country = response.getCountry();
            }
        } catch (Exception e) {
            System.out.println("Error calling IP API: " + e.getMessage());
        }

        String deviceType = userAgent.toLowerCase().contains("mobile") ? "Mobile" : "Desktop";

        return new UserInfo(country, deviceType, ip);
    }
}
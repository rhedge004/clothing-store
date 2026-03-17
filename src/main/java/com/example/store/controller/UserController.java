package com.example.store.controller;

import com.example.store.model.IpApiResponse;
import com.example.store.model.UserInfo;
import com.example.store.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/user")
public class UserController {

    private final RestTemplate restTemplate;
    private final UserRepository userRepository;

    public UserController(RestTemplate restTemplate, UserRepository userRepository) {
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
    }

    @GetMapping("/info")
    public UserInfo getUserInfo(
            HttpServletRequest request,
            @RequestHeader(value = "User-Agent") String userAgent) {

        String ip = request.getRemoteAddr();
        if (ip.equals("0:0:0:0:0:0:0:1") || ip.equals("127.0.0.1")) {
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
            System.err.println("Error calling IP API: " + e.getMessage());
        }

        String deviceType = userAgent.toLowerCase().contains("mobile") ? "Mobile" : "Desktop";

        // Create and Save the entity
        UserInfo userInfo = new UserInfo();
        userInfo.setCountry(country);
        userInfo.setDeviceType(deviceType);
        userInfo.setIpAddress(ip);
        userInfo.setVisitTime(LocalDateTime.now());

        return userRepository.save(userInfo);
    }
}
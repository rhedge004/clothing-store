package com.example.store.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpApiResponse {
    private String status;
    private String country;
    private String countryCode;
    private String regionName;
    private String city;
    private String query; // This is the IP returned by the API
}
package com.example.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfo {
    private String country;
    private String deviceType;
    private String ipAddress;
}
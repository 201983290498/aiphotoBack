package com.example.demo.entity;

import lombok.Data;

import java.util.Map;

@Data
public class UserEx {
    private String username;
    private String pripassword;

    public UserEx(String username, String pripassword) {
        this.username = username;
        this.pripassword = pripassword;
    }

    public UserEx() {
    }
    public UserEx(Map<String,Object> data) {
        this.username = (String) data.get("username");
        this.pripassword = (String) data.get("pripassword");
    }
}

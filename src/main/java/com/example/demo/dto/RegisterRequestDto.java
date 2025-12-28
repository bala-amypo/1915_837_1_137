package com.example.demo.dto;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String email;
    private String fullName;
    private String password;
}
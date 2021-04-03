package com.example.demo.payload.request;

import javax.validation.constraints.NotEmpty;

public class LoginRequest {
    @NotEmpty(message = "User Name cannot be empty")
    private String userName;
    @NotEmpty(message = "Password cannot be empty")
    private String password;
}

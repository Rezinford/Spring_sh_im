package com.example.demo.payload.reponse;

import lombok.Getter;

@Getter
public class InvalidLoginResponse {

    private String userName;
    private String password;

    public InvalidLoginResponse() {
        this.userName = "Invalid User Name";
        this.password = "Invalid Password";
    }
}

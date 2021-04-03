package com.example.demo.payload.request;

import com.example.demo.annotations.PasswordMatches;
import com.example.demo.annotations.ValidEmail;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@PasswordMatches
public class SingUpRequest {

    @Email(message = "It should have email format")
    @NotBlank(message = "User email is required")
    @ValidEmail
    private String email;
    @NotEmpty(message = "Please enter your last name")
    private String lastName;
    @NotEmpty(message = "Please enter your first name")
    private String firstName;
    @NotEmpty(message = "Please enter your user name")
    private String userName;
    @NotEmpty(message = "Password is required")
    @Size(min = 6)
    private String password;
    private String confirmPassword;
}

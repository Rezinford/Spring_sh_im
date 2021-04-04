package com.example.demo.web;

import com.example.demo.payload.reponse.JWTTokenSuccessResponse;
import com.example.demo.payload.reponse.MessageResponse;
import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.request.SingUpRequest;
import com.example.demo.security.JWTTokenProvider;
import com.example.demo.security.SecurityConstants;
import com.example.demo.services.UserService;
import com.example.demo.validations.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@PreAuthorize("permitAll()")
public class AuthController  {

    @Autowired
    private JWTTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;
    @Autowired
    private UserService userServices;

    @PostMapping("/singin")
    public ResponseEntity<Object> authenticateUser (@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUserName(),
                loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generationToken(authentication);

        return ResponseEntity.ok(new JWTTokenSuccessResponse(true, jwt));
    }


    @PostMapping("/singup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SingUpRequest singUpRequest, BindingResult bindingResult){
        ResponseEntity<Object> errors  = responseErrorValidation.mapValidationService(bindingResult);
        if(!ObjectUtils.isEmpty(errors)) return errors;
        userServices.createUser(singUpRequest);
        return ResponseEntity.ok(new MessageResponse("User register successfully"));
    }


}

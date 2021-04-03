package com.example.demo.services;

import com.example.demo.entity.User;
import com.example.demo.entity.enums.ERole;
import com.example.demo.exception.UseExistException;
import com.example.demo.payload.request.SingUpRequest;
import com.example.demo.repositaty.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    public static final Logger LOG = LoggerFactory.getLogger(UserServices.class);

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServices(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = bCryptPasswordEncoder;
    }

    public User createUSer(SingUpRequest userIn) {
        User user = new User();
        user.setEmail(userIn.getEmail());
        user.setName(userIn.getFirstName());
        user.setLastName(userIn.getLastName());
        user.setUserName(userIn.getUserName());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.getRole().add(ERole.ROLE_USER);

        try {
            LOG.info("Saving User {}", userIn.getEmail());
            return userRepository.save(user);
        } catch (Exception e) {
            LOG.error("Error during registration. {}", e.getMessage());
            throw new UseExistException("This user " + user.getUsername() + "already exist. Please check credentials");
        }

    }
}

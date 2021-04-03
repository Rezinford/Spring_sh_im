package com.example.demo.services;

import com.example.demo.entity.User;
import com.example.demo.repositaty.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailServices implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName){
        User user = userRepository.findUserByEmail(userName)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with userName: "+ userName));


        return build(user);
    }

    public User loadUserById(Long id){
        return userRepository.findUserById(id).orElse( null);
    }

    public static User build(User user){
        List<GrantedAuthority> authorities = user.getRole().stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
        return new User(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities);
    }
}

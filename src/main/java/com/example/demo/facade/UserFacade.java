package com.example.demo.facade;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {

    public UserDTO userToUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUserName(user.getUsername());
        userDTO.setBio(user.getBio());
        return userDTO;
    }
}

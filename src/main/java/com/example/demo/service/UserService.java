package com.example.demo.service;



import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();

    Authentication getAuthentication();
}

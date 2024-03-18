package com.example.sklep.service;

import com.example.sklep.dto.UserDTO;
import com.example.sklep.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<UserDTO> findAllUsernameRoleEnabled();

    void createUser(User user);
}
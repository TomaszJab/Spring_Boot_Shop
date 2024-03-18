package com.example.sklep.Controllers;

import com.example.sklep.dto.UserDTO;
import com.example.sklep.entity.User;
import com.example.sklep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService theUserService){
        userService=theUserService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UserDTO>> findAllUsernameRoleEnabled(){
        List<UserDTO> userDTOList = userService.findAllUsernameRoleEnabled();
        return ResponseEntity.ok(userDTOList);
    }

    @PostMapping("/createUser")
    public void createUser(@RequestBody User theUser){
        userService.createUser(theUser);
    }
}

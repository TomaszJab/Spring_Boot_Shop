package com.example.sklep.service;

import com.example.sklep.dao.UserRepository;
import com.example.sklep.dto.UserDTO;
import com.example.sklep.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserServiceImpl(UserRepository theUserRepository){
        userRepository = theUserRepository;
    }
    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<UserDTO> findAllUsernameRoleEnabled() {
        return userRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private UserDTO convertEntityToDto(User user){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }
}

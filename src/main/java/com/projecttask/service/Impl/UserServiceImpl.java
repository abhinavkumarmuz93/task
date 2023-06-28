package com.projecttask.service.Impl;

import com.projecttask.Exception.ResourceNotFoundException;
import com.projecttask.entities.User;
import com.projecttask.payload.UserDto;
import com.projecttask.repository.UserRepository;
import com.projecttask.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = mapToEntity(userDto);
        User savedUser = userRepository.save(user);
        UserDto dto = mapToDto(savedUser);
        return dto;
    }

    @Override
    public UserDto updateUser(long userId, UserDto userDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found" + userId));
          user.setName(userDto.getName());
          user.setDateOfBirth(userDto.getDateOfBirth());
          user.setEmail(userDto.getEmail());
          user.setMobile(userDto.getMobile());
          user.setUserType(userDto.getUserType());
        User updatedUser = userRepository.save(user);
        UserDto dto = mapToDto(updatedUser);
        return dto;
    }

    @Override
    public UserDto getUserById(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found" + userId));
        UserDto dto = mapToDto(user);
        return dto;
    }
    @Override
    public List<UserDto> getAllUsers(String userType, int ageGreater, int ageLessThan) {
        List<User> users;

        if (userType != null && ageGreater > 0 && ageLessThan > 0) {
            users = userRepository.findByUserTypeAndAgeBetween(userType, ageGreater, ageLessThan);
        } else if (userType != null) {
            users = userRepository.findByUserType(userType);
        } else if (ageGreater > 0 && ageLessThan > 0) {
            users = userRepository.findByAgeBetween(ageGreater, ageLessThan);
        } else if (ageGreater > 0) {
            users = userRepository.findByAgeGreaterThanEqual(ageGreater);
        } else if (ageLessThan > 0) {
            users = userRepository.findByAgeLessThanEqual(ageLessThan);
        } else {
            users = userRepository.findAll();
        }
        List<UserDto> dto = users.stream().map(user -> mapToDto(user)).collect(Collectors.toList());
        return dto;
    }


    @Override
    public void deleteUserById(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found with id"+userId));
        userRepository.deleteById(userId);
    }


    private UserDto mapToDto(User savedUser) {
        UserDto dto = mapper.map(savedUser, UserDto.class);
        return dto;
    }

    private User mapToEntity(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        return user;
    }
}

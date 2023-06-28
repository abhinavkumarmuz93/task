package com.projecttask.service;

import com.projecttask.payload.UserDto;

import java.util.List;

public interface UserService {
    UserDto addUser(UserDto userDto);

    UserDto updateUser(long userId, UserDto userDto);

    UserDto getUserById(long userId);

    List<UserDto> getAllUsers(String userType, int ageGreater, int ageLessThan);

    void deleteUserById(long userId);
}

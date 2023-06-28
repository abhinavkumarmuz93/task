package com.projecttask.controller;

import com.projecttask.payload.UserDto;
import com.projecttask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
@Autowired
private UserService userService;


    //http://localhost:8080/api/users
    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody @Valid UserDto userDto, BindingResult result) {
        if(result.hasErrors()){
           return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        UserDto dto = userService.addUser(userDto);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }

    //http://localhost:8080/api/users/{userId}
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(
            @PathVariable("userId") long userId, @RequestBody @Valid UserDto userDto,BindingResult result) {
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        UserDto dto = userService.updateUser(userId, userDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    //http://localhost:8080/api/users/{userId}
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") long userId) {
        UserDto user = userService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //http://localhost:8080/api/users?userType=Developer&ageGreater=30&ageLessThan=40
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(
            @RequestParam(value = "userType", defaultValue = "Developer", required = false) String userType,
            @RequestParam(value = "ageGreater", defaultValue = "30", required = false) int ageGreater,
            @RequestParam(value = "ageLessThan", defaultValue = "40", required = false) int ageLessThan
    ) {
        List<UserDto> dtos = userService.getAllUsers(userType, ageGreater, ageLessThan);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    //http://localhost:8080/api/users/{userId}
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable("userId") long userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity<>("user is deleted",HttpStatus.OK);
    }
}



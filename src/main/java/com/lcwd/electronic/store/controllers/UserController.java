package com.lcwd.electronic.store.controllers;

import com.lcwd.electronic.store.dtos.ApiResponseMessage;
import com.lcwd.electronic.store.dtos.PageableResponse;
import com.lcwd.electronic.store.dtos.UserDto;
import com.lcwd.electronic.store.entities.User;
import com.lcwd.electronic.store.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //create
    @PostMapping
    public ResponseEntity<UserDto>createUser(@Valid @RequestBody UserDto userDto){
        UserDto user = userService.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto>updateUser(@Valid @RequestBody UserDto userDto, @PathVariable(value = "userId") String userId){
        UserDto updatedUserDto = userService.updateUser(userDto, userId);
        return new ResponseEntity<>(updatedUserDto,HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseMessage>deleteUser(@PathVariable(value = "userId")String userId){
        userService.deleteUser(userId);
        ApiResponseMessage message = ApiResponseMessage.builder()
                .message("user is deleted successfully")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    //get all
    @GetMapping
    public ResponseEntity<PageableResponse<UserDto>> getAllUsers(
            @RequestParam(value = "pageNumber",required = false,defaultValue = "0")int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false)int pageSize,
            @RequestParam(value = "sortBy",required = false,defaultValue = "name")String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir
            ){
        PageableResponse<UserDto> pageableResponse = userService.getAllUser(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(pageableResponse,HttpStatus.OK);
    }

    //get single
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable(value = "userId")String userId){
        UserDto userById = userService.getUserById(userId);
        return new ResponseEntity<>(userById,HttpStatus.OK);
    }

    //get by email
    @GetMapping("/email/{emailId}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable(value = "emailId")String emailId){
        UserDto userByEmail = userService.getUserByEmail(emailId);
        return new ResponseEntity<>(userByEmail,HttpStatus.OK);
    }

    //search user
    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable(value = "keywords")String keywords){
        List<UserDto> dtoList = userService.searchUser(keywords);
        return new ResponseEntity<>(dtoList,HttpStatus.OK);
    }
}

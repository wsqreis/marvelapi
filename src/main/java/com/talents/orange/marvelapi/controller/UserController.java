package com.talents.orange.marvelapi.controller;


import com.talents.orange.marvelapi.dto.request.UserDTO;
import com.talents.orange.marvelapi.dto.response.UserComicsDTO;
import com.talents.orange.marvelapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    @GetMapping("/{id}")
    public UserComicsDTO findById(@PathVariable Long id){
        return userService.getById(id);
    }

}

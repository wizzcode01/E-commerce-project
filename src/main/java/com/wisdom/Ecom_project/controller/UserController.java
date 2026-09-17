package com.wisdom.Ecom_project.controller;

import com.wisdom.Ecom_project.dto.RequestDto.RegisterRequestDto;
import com.wisdom.Ecom_project.model.Users;
import com.wisdom.Ecom_project.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDto registrationData){
        return UserService.register(registrationData);
        ResponseEntity.ok("Users registered successfully");
    }

    @GetMapping("/login")
    public Users login(@RequestBody Users user){
        return service.verify(user);
    }

}

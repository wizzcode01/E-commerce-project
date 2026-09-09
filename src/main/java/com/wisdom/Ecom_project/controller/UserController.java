package com.wisdom.Ecom_project.controller;

import com.wisdom.Ecom_project.RequestDto.RegisterRequestDto;
import com.wisdom.Ecom_project.model.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDto registrationData){
        return service.register(user);
    }

    @GetMapping("/login")
    public Users login(@RequestBody Users user){
        return service.verify(user);
    }

}

package com.wisdom.Ecom_project.controller;

import com.wisdom.Ecom_project.dto.RequestDto.LoginRequestDto;
import com.wisdom.Ecom_project.dto.RequestDto.RegisterRequestDto;
import com.wisdom.Ecom_project.dto.ResponseDto.AuthResponsePayload;
import com.wisdom.Ecom_project.dto.ResponseDto.responseDto;
import com.wisdom.Ecom_project.model.RefreshToken;
import com.wisdom.Ecom_project.model.Users;
import com.wisdom.Ecom_project.service.RefreshTokenService;
import com.wisdom.Ecom_project.service.UserService;
import com.wisdom.Ecom_project.service.jwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private jwtService jwt;

    @PostMapping("/register")
    public ResponseEntity<responseDto<Void>> register(@RequestBody RegisterRequestDto registrationData){
       userService.register(registrationData);

       responseDto<Void> responseContainer = new responseDto<>("Registration successful");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseContainer);
    }

    @GetMapping("/login")
    public ResponseEntity<responseDto<AuthResponsePayload>> login(@RequestBody LoginRequestDto loginRequestDto){
       AuthResponsePayload tokenPayload =  userService.verifyLogin(loginRequestDto);
       if(tokenPayload != null){
           responseDto<AuthResponsePayload> responseContainer = new responseDto<>("Authentication verified successful", tokenPayload);
           return ResponseEntity.ok(responseContainer);
       }

       responseDto<AuthResponsePayload> errorMsg = new responseDto<>("Authentication failed. Invalid credentials");
       return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMsg);

    }

    @PostMapping("/refresh")
    public ResponseEntity<responseDto<AuthResponsePayload>> refreshToken(@RequestBody String incomingRefreshToken ){
        RefreshToken newRefreshToken = refreshTokenService.rotateToken(incomingRefreshToken);

        String newAccessToken = jwt.generateToken(newRefreshToken.getUser().getEmail());
        AuthResponsePayload newTokensPayload = new AuthResponsePayload(newAccessToken, newRefreshToken.getToken());

        responseDto<AuthResponsePayload> successPayload =  new responseDto<>("Session restored successfully with tokens.", newTokensPayload);

        return ResponseEntity.ok(successPayload);
    }


}

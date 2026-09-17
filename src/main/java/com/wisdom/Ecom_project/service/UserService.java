package com.wisdom.Ecom_project.service;

import com.wisdom.Ecom_project.dto.RequestDto.LoginRequestDto;
import com.wisdom.Ecom_project.dto.RequestDto.RegisterRequestDto;
import com.wisdom.Ecom_project.exception.EmailAlreadyExistsException;
import com.wisdom.Ecom_project.model.Users;
import com.wisdom.Ecom_project.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepo repo;

    @Autowired
    private jwtService jwt;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void register(RegisterRequestDto dto){
        if(repo.existsByEmail(dto.getEmail())){
            throw new EmailAlreadyExistsException("An account with this email already exists.");
        }

        Users databaseUser = new Users(); // to create a new object from blueprint of Users
        databaseUser.setName(dto.getName());
        databaseUser.setEmail(dto.getEmail());

        String hashedPassword = encoder.encode(dto.getPassword());
        databaseUser.setPassword(hashedPassword);

        // databaseUser.setRole(Role.CUSTOMER);

        repo.save(databaseUser);
    }

    public String verifyLogin(LoginRequestDto loginRequestDto){
        UsernamePasswordAuthenticationToken unverifiedToken = new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        Authentication authentication = authManager.authenticate(unverifiedToken);

        if(authentication.isAuthenticated()){
            return jwt.generateToken(loginRequestDto.getEmail());
        }

        return "fail";
    }


}

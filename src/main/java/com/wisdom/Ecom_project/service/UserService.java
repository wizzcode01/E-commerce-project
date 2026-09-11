package com.wisdom.Ecom_project.service;

import com.wisdom.Ecom_project.RequestDto.RegisterRequestDto;
import com.wisdom.Ecom_project.model.Users;
import com.wisdom.Ecom_project.repository.UsersRepo;
import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepo repo;

    public void register(RegisterRequestDto dto){
        Users databaseUser = new Users(); // to create a new object from blueprint of Users
        databaseUser.setName(dto.getName());
        databaseUser.setEmail(dto.getEmail());
    }
}

package com.wisdom.Ecom_project.service;

import com.wisdom.Ecom_project.model.RefreshToken;
import com.wisdom.Ecom_project.model.Users;
import com.wisdom.Ecom_project.repository.RefreshTokenRepo;
import com.wisdom.Ecom_project.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final long refreshTokenDurationMs = 1000L * 60 * 60 * 24 * 7;

    @Autowired
    private RefreshTokenRepo refreshTokenRepo;

    @Autowired
    private UsersRepo usersRepo;

    public RefreshToken createRefreshToken(String email){
       RefreshToken refreshToken = new RefreshToken();

        Users user = usersRepo.findByEmail(email);
        refreshToken.setUser(user);

        //create a cryptographically secure random token string
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));

        return refreshTokenRepo.save(refreshToken);
    }
}

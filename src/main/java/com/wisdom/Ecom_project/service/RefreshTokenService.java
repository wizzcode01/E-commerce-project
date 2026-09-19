package com.wisdom.Ecom_project.service;

import com.wisdom.Ecom_project.exception.InvalidRefreshTokenException;
import com.wisdom.Ecom_project.model.RefreshToken;
import com.wisdom.Ecom_project.model.Users;
import com.wisdom.Ecom_project.repository.RefreshTokenRepo;
import com.wisdom.Ecom_project.repository.UsersRepo;
import jakarta.transaction.Transactional;
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
       RefreshToken refreshToken = new RefreshToken(); // create the object of the RefreshToken

        Users user = usersRepo.findByEmail(email); // get the user email from usersRepo
        refreshToken.setUser(user); // add the user to the refreshToken meaning we have linked a user to it refresh token.

        //create a cryptographically secure random token string
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));

        return refreshTokenRepo.save(refreshToken);
    }

    // before giving a user a new access token we must verify if their refresh token is still valid
    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().isBefore(Instant.now())){
            refreshTokenRepo.delete(token);
            throw new InvalidRefreshTokenException("Session is expired. Please log in again.");
        }
        return token;
    }

    //This is the refresh token rotation
    @Transactional // required because of database modification
    public RefreshToken rotateToken(String incomingToken){
       RefreshToken oldToken = refreshTokenRepo.findByToken(incomingToken)
               .orElseThrow(() -> new InvalidRefreshTokenException("Invalid refresh token. Session not found."));

       verifyExpiration(oldToken);
       Users user = oldToken.getUser();
       refreshTokenRepo.delete(oldToken);

       return createRefreshToken(user.getEmail());
    }
}

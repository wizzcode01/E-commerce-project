package com.wisdom.Ecom_project.repository;

import com.wisdom.Ecom_project.model.RefreshToken;
import com.wisdom.Ecom_project.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUser(Users users);
}

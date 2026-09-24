package com.wisdom.Ecom_project.repository;

import com.wisdom.Ecom_project.model.Wallet;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface walletRepo extends JpaRepository<Wallet, Long> {
   @Lock(LockModeType.PESSIMISTIC_WRITE)
   @Query("SELECT w FROM Wallet w WHERE w.user.email = :email")
   Optional<Wallet> findByUserEmailForUpdate(@Param("email") String email);
}

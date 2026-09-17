package com.wisdom.Ecom_project.repository;

import com.wisdom.Ecom_project.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users, Integer> {

    Users findByEmail(String email);
    boolean existsByEmail(String email);
}

package com.ecommerce.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.user.model.AuthUserModel;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUserModel,Long > {
    Optional<AuthUserModel> findByEmail(String email); 
}

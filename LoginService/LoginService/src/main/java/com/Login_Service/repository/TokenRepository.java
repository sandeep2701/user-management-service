package com.Login_Service.repository;

import com.Login_Service.entities.User;
import com.Login_Service.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<PasswordResetToken,String> {
    PasswordResetToken  findByToken(String token);
}

package com.Login_Service.repository;


import com.Login_Service.entities.User;
import com.Login_Service.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    public User findByEmail(String email);


}

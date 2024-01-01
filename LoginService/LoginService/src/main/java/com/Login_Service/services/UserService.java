package com.Login_Service.services;

import com.Login_Service.entities.User;
import com.Login_Service.model.ChangePasswordRequest;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User>  getAllUsers();

    User addUser(User user);

    User updateUser(User user);

    User getUserById(String userId);

    User getUserByEmail(String email);

    void deleteUser(String userId);
    void changePassword(ChangePasswordRequest request, Principal connectedUser);

}

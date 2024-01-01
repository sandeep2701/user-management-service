package com.Login_Service.services;

import com.Login_Service.entities.User;
import com.Login_Service.exception.PasswordIncorrectException;
import com.Login_Service.exception.ResourceNotFoundException;
import com.Login_Service.model.ChangePasswordRequest;
import com.Login_Service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User Not Found !!"));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        User user = (User)((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        //check if the oldPassword is correct
        if(!(passwordEncoder.matches(request.getOldPassword(),user.getPassword()))){
            throw new PasswordIncorrectException("password Incorrect!!");
        }
        //check if confirm password matches new password
        if(!request.getNewPassword().equals(request.getConfirmationPassword())){
            throw new PasswordIncorrectException("password does not match!!");
        }
        //update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        //saving the password
        userRepository.save(user);
    }

}

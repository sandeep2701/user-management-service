package com.Login_Service.controller;

import com.Login_Service.entities.User;
import com.Login_Service.model.ChangePasswordRequest;
import com.Login_Service.services.CustomUserDetailService;
import com.Login_Service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/home")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }


    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable String userId){
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("users/{userId}")
        public ResponseEntity<?> deleteStudent(@PathVariable String userId) {
            try {
                userService.deleteUser(userId);
                return ResponseEntity.ok(userId);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Principal connectedUser){
        userService.changePassword(request,connectedUser);
        return ResponseEntity.ok().build();
    }
}

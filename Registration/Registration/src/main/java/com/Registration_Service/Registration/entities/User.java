package com.Registration_Service.Registration.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User  {

    private String userId;
    private String name;
    private String password;
    private String gender;
    private String role;
    private String contact;
    private String email;

}

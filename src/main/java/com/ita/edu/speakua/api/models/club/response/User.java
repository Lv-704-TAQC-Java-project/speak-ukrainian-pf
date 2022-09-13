package com.ita.edu.speakua.api.models.club.response;

import lombok.Data;

@Data
public class User{
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String urlLogo;
    private Role role;
    private String provider;
    private String providerId;
    private boolean status;
    private String verificationCode;
}

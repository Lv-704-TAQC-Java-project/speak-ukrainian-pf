package com.ita.edu.speakua.api.models.user;

import lombok.Data;

@Data
public class ReadUserResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private String roleName;
    private String urlLogo;
    private String status;
}
package com.ita.edu.speakua.api.models.user;

import lombok.Data;

@Data
public class EditUserResponse {
    public int id;
    public String firstName;
    public String lastName;
    public String phone;
    public String email;
    public String roleName;
    public String urlLogo;
    public String status;
}
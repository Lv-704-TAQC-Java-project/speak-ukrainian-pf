package com.ita.edu.speakua.utils.jdbc.dto;

import com.ita.edu.speakua.utils.jdbc.entity.RoleEntity;
import com.ita.edu.speakua.utils.jdbc.entity.UserEntity;
import lombok.Data;

@Data
public class UserJoinRoleDTO {
    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    private String provider;
    private String providerId;
    private Boolean status;
    private String urlLogo;
    private String verificationCode;
    private RoleEntity role;

    public UserJoinRoleDTO(UserEntity entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.password = entity.getPassword();
        this.phone = entity.getPhone();
        this.provider = entity.getProvider();
        this.providerId = entity.getProviderId();
        this.status = entity.getStatus();
        this.urlLogo = entity.getUrlLogo();
        this.verificationCode = entity.getVerificationCode();
    }
}
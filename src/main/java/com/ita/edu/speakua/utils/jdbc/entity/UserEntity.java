package com.ita.edu.speakua.utils.jdbc.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserEntity {
    public static final String SELECT_USER_BY_EMAIL_AND_FULL_NAME = "SELECT * " +
            "FROM users " +
            "WHERE email='%s' " +
            "AND first_name='%s' " +
            "AND last_name='%s';";

    public static final String COUNT_USERS_WITH_EMAIL_AND_FULL_NAME = "SELECT COUNT(*) " +
            "FROM users " +
            "WHERE email='%s' " +
            "AND first_name='%s' " +
            "AND last_name='%s';";

    public static final String SELECT_ALL_WHERE_ID = "SELECT * FROM users WHERE id='%d';";

    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    private String provider;
    private String providerId;
    private boolean status;
    private String urlLogo;
    private String verificationCode;
    private long roleId;

    public static UserEntity parseRow(List<String> row) {
        UserEntity UserEntity = new UserEntity();
        UserEntity.setId(Long.parseLong(row.get(0)));
        UserEntity.setEmail(row.get(1));
        UserEntity.setFirstName(row.get(2));
        UserEntity.setLastName(row.get(3));
        UserEntity.setPassword(row.get(4));
        UserEntity.setPhone(row.get(5));
        UserEntity.setProvider(row.get(6));
        UserEntity.setProviderId(row.get(7));
        UserEntity.setStatus(Boolean.parseBoolean(row.get(8)));
        UserEntity.setUrlLogo(row.get(9));
        UserEntity.setVerificationCode(row.get(10));
        UserEntity.setRoleId(Long.parseLong(row.get(11)));
        return UserEntity;
    }

    public static List<UserEntity> parseRows(List<List<String>> rows) {
        List<UserEntity> userEntities = new ArrayList<>();
        for (List<String> row : rows) {
            userEntities.add(parseRow(row));
        }
        return userEntities;
    }
}
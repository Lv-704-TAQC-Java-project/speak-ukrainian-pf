package com.ita.edu.speakua.api.models.club.response;

import lombok.Data;

import static java.lang.String.format;

@Data
public class User {
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String urlLogo;
    private String provider;
    private int providerId;
    private boolean status;
    private String verificationCode;

    @Override
    public String toString() {
        return format("    {\n" +
                        "\"id\": %d,\n" +
                        "\"email\": \"%s\",\n" +
                        "\"password\": \"%s\",\n" +
                        "\"firstName\": \"%s\",\n" +
                        "\"lastName\": \"%s\",\n" +
                        "\"phone\": \"%s\",\n" +
                        "\"urlLogo\": \"%s\",\n" +
                        "\"provider\": \"%s\",\n" +
                        "\"providerId\": %d,\n" +
                        "\"status\": %s,\n" +
                        "\"verificationCode\": \"%s\",\n" +
                        "    }\n",
                id,
                email,
                password,
                firstName,
                lastName,
                phone,
                urlLogo,
                provider,
                providerId,
                status,
                verificationCode);
    }
}

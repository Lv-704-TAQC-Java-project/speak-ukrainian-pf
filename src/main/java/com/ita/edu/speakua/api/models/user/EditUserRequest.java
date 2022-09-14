package com.ita.edu.speakua.api.models.user;

import com.ita.edu.speakua.api.data.Role;
import lombok.Builder;

import static java.lang.String.format;

@Builder
public class EditUserRequest {
    public int id;
    public String email;
    public String firstName;
    public String lastName;
    public String phone;
    public String urlLogo;
    public String status;
    public Role roleName;

    public String json() {
        return format("{\n" +
                        "  \"id\": \"%s\",\n" +
                        "  \"email\": \"%s\",\n" +
                        "  \"firstName\": \"%s\",\n" +
                        "  \"lastName\": \"%s\",\n" +
                        "  \"phone\": \"%s\"\n" +
                        "  \"urlLogo\": \"%s\"\n" +
                        "  \"status\": \"%s\"\n" +
                        "  \"roleName\": \"%s\"\n" +
                        "}",
                id,
                email,
                firstName,
                lastName,
                phone,
                urlLogo,
                status,
                roleName
        );
    }
}
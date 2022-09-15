package com.ita.edu.speakua.api.models.registration;

import com.ita.edu.speakua.api.data.Role;
import lombok.Builder;

import static java.lang.String.format;

@Builder
public class SignUpRequest {
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String phone;
    public Role roleName;

    public String json() {
        return format("{\n" +
                        "\"firstName\":\"%s\",\n" +
                        "\"lastName\":\"%s\",\n" +
                        "\"email\":\"%s\",\n" +
                        "\"password\":\"%s\",\n" +
                        "\"phone\":\"%s\",\n" +
                        "\"roleName\":\"%s\"\n" +
                        "}",
                firstName,
                lastName,
                email,
                password,
                phone,
                roleName.getRoleValue());
    }
}
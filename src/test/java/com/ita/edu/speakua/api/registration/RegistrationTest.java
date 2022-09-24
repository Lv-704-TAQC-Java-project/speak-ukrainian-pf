package com.ita.edu.speakua.api.registration;

import com.ita.edu.speakua.api.ApiBaseTestRunner;
import com.ita.edu.speakua.api.clients.RegistrationClient;
import com.ita.edu.speakua.api.models.ErrorResponse;
import com.ita.edu.speakua.api.models.registration.SignUpRequest;
import com.ita.edu.speakua.utils.jdbc.services.UserService;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static com.ita.edu.speakua.api.data.Role.USER;
import static java.util.Arrays.asList;
import static org.testng.Assert.assertEquals;

public class RegistrationTest extends ApiBaseTestRunner {

    @Issue("TUA-499")
    @Description("Verify registration fails if provided password has invalid format")
    @Link("https://jira.softserve.academy/browse/TUA-499")
    @Test
    public void verifyInvalidPasswordRegistrationFails() {
        String firstName = "firstname";
        String lastName = "lastname";
        String email = "newuniqemail@gmail.com";

        RegistrationClient registrationClient = new RegistrationClient();
        SignUpRequest signUpRequest = SignUpRequest
                .builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password("123456")
                .phone("0634562314")
                .roleName(USER.getRoleValue())
                .build();

        Response signUpResponse = registrationClient.signUp(signUpRequest);
        assertEquals(signUpResponse.statusCode(), 400,
                "Incorrect API response status code when registering with invalid password format");

        ErrorResponse singUpErrorResponse = signUpResponse.as(ErrorResponse.class);
        SoftAssert softly = new SoftAssert();
        softly.assertEquals(singUpErrorResponse.getStatus(), 400,
                "Incorrect response status code");
        List<String> passwordErrorMessages = asList("password must contain at least one number and special symbol",
                "password must contain at least one uppercase and lowercase letter",
                "password size must be between 8 and 20");
        passwordErrorMessages.forEach(message -> softly.assertTrue(singUpErrorResponse.getMessage().contains(message),
                "Password error message should contain: " + message));
        softly.assertAll();

        long usersQuantity = new UserService().getTasksCount(email, firstName, lastName);
        assertEquals(usersQuantity, 0, "User should not be added to database");
    }
}
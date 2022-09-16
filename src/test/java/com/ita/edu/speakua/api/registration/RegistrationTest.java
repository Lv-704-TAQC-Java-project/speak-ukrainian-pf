package com.ita.edu.speakua.api.registration;

import com.ita.edu.speakua.api.ApiBaseTestRunner;
import com.ita.edu.speakua.api.clients.RegistrationClient;
import com.ita.edu.speakua.api.data.Role;
import com.ita.edu.speakua.api.models.ErrorResponse;
import com.ita.edu.speakua.api.models.registration.SignUpRequest;
import com.ita.edu.speakua.utils.jdbc.services.UserService;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

public class RegistrationTest extends ApiBaseTestRunner {

    @Issue("TUA-499")
    @Description("Verify registration fails if provided password has invalid format")
    @Link("https://jira.softserve.academy/browse/TUA-499")
    @Test
    public void verifyInvalidPasswordRegistrationFails() {
        String firstName = "firstname";
        String lastName = "lastname";
        String email = "email";

        RegistrationClient registrationClient = new RegistrationClient();
        SignUpRequest signUpRequest = SignUpRequest
                .builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password("Pf#123456")
                .phone("0634562314")
                .roleName(Role.MANAGER)
                .build();

        Response signUpResponse = registrationClient.signUp(signUpRequest);
        assertEquals(signUpResponse.statusCode(), 400,
                "Incorrect response status code");

        ErrorResponse singUpErrorResponse = signUpResponse.as(ErrorResponse.class);
        SoftAssert softly = new SoftAssert();
        softly.assertEquals(singUpErrorResponse.getStatus(), 400,
                "Unexpected response status code");
        softly.assertEquals(singUpErrorResponse.getMessage(), "email is not valid",
                "Incorrect error message");
        softly.assertAll();

        long usersQuantity = new UserService().getTasksCount(email, firstName, lastName);
        assertEquals(usersQuantity, 0, "User should not be added to database");
    }
}
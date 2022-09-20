package com.ita.edu.speakua.api.user;

import com.ita.edu.speakua.api.ApiBaseTestRunner;
import com.ita.edu.speakua.api.clients.Authentication;
import com.ita.edu.speakua.api.clients.UserClient;
import com.ita.edu.speakua.api.data.Role;
import com.ita.edu.speakua.api.models.ErrorResponse;
import com.ita.edu.speakua.api.models.user.EditUserRequest;
import com.ita.edu.speakua.api.models.user.ReadUserResponse;
import com.ita.edu.speakua.utils.jdbc.entity.UserEntity;
import com.ita.edu.speakua.utils.jdbc.services.UserService;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class UserTest extends ApiBaseTestRunner {

    private Authentication authentication;

    @BeforeClass
    public void beforeClass() {
        authentication = new Authentication(properties.getUserEmail(), properties.getUserPassword());
    }

    @DataProvider(name = "invalidPhoneData")
    public static Object[][] invalidPhoneData() {
        return new Object[][]{
                {"123456789121212"},
                {"assdsdsd"},
                {"@$#%#%^"}
        };
    }

    @Test(dataProvider = "invalidPhoneData")
    public void verifyThatUserCanNotSaveChangesWithPhoneInvalidData(String phone) {
        int id = 203;

        UserClient userClient = new UserClient(authentication.getToken());

        Response getResponse = userClient.get(id);
        ReadUserResponse readUserResponse = getResponse.as(ReadUserResponse.class);

        EditUserRequest editUserRequest = EditUserRequest.builder()
                .firstName("Nastia")
                .lastName("Kukh")
                .email("soyec48727@busantei.com")
                .phone(phone)
                .roleName(Role.MANAGER)
                .urlLogo(null)
                .status(String.valueOf(true))
                .build();

        Response response = userClient.put(id, editUserRequest);
        assertEquals(response.statusCode(), 400);

        ErrorResponse errorResponse = response.as(ErrorResponse.class);
        assertEquals(errorResponse.getMessage(), "Phone' field contain an invalid value");

        List<UserEntity> users = new UserService().getUsersWhereId(id);
        for (UserEntity user : users) {
            assertEquals(user.getPhone(), readUserResponse.getPhone(), "User phone should be unchanged");
        }
    }
}
package com.ita.edu.speakua.api.user;

import com.ita.edu.speakua.api.ApiBaseTestRunner;
import com.ita.edu.speakua.api.clients.Authentication;
import com.ita.edu.speakua.api.clients.UserClient;
import com.ita.edu.speakua.api.models.ErrorResponse;
import com.ita.edu.speakua.api.models.user.EditUserRequest;
import com.ita.edu.speakua.api.models.user.EditUserResponse;
import com.ita.edu.speakua.api.models.user.ReadUserResponse;
import com.ita.edu.speakua.utils.jdbc.dto.UserJoinRoleDTO;
import com.ita.edu.speakua.utils.jdbc.entity.UserEntity;
import com.ita.edu.speakua.utils.jdbc.services.RoleService;
import com.ita.edu.speakua.utils.jdbc.services.UserService;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.ita.edu.speakua.api.data.Role.*;
import static org.testng.Assert.assertEquals;

public class UserTest extends ApiBaseTestRunner {

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
        Authentication authentication = new Authentication(properties.getAdminEmail(), properties.getAdminPassword());
        int id = 203;

        UserClient userClient = new UserClient(authentication.getToken());

        Response getResponse = userClient.get(id);
        ReadUserResponse readUserResponse = getResponse.as(ReadUserResponse.class);

        EditUserRequest editUserRequest = EditUserRequest.builder()
                .firstName("Nastia")
                .lastName("Kukh")
                .email("soyec48727@busantei.com")
                .phone(phone)
                .roleName(MANAGER.getRoleValue())
                .urlLogo(null)
                .status(true)
                .build();

        Response response = userClient.put(id, editUserRequest);
        assertEquals(response.statusCode(), 400);

        ErrorResponse errorResponse = response.as(ErrorResponse.class);
        assertEquals(errorResponse.getMessage(),
                "phone Phone number must contain 10 numbers and can`t contain other symbols");

        UserEntity user = new UserService().getUserWhereId(id);
        String roleName = new RoleService().getRoleNameWhereUserId(id);
        assertEquals(user.getFirstName(), readUserResponse.getFirstName(), "First name should be unchanged");
        assertEquals(user.getLastName(), readUserResponse.getLastName(), "Last name should be unchanged");
        assertEquals(user.getEmail(), readUserResponse.getEmail(), "Email should be unchanged");
        assertEquals(user.getPhone(), readUserResponse.getPhone(), "User phone should be unchanged");
        assertEquals(roleName, readUserResponse.getRoleName(), "Role should be unchanged");
        assertEquals(user.getUrlLogo(), readUserResponse.getUrlLogo(), "Url logo should be unchanged");
        assertEquals(user.getStatus(), readUserResponse.getStatus(), "Status should be unchanged");
    }

    @Issue("TUA-416")
    @Description("Verify user and manager can edit role")
    @Link("https://jira.softserve.academy/browse/TUA-416")
    @Test
    public void verifyUsersWithDifferentRolesCanEditProfile() {
        String email = properties.getUserEmail();
        String password = properties.getUserPassword();

        UserJoinRoleDTO initialUserDB = new UserService().getUserJoinRoleDTO(email);
        long initialUserId = initialUserDB.getId();
        String initialUserFirstName = initialUserDB.getFirstName();
        String initialUserLastName = initialUserDB.getLastName();
        String initialUserPhone = initialUserDB.getPhone();
        boolean initialUserStatus = initialUserDB.getStatus();
        String initialUserUrlLogo = initialUserDB.getUrlLogo();
        String initialUserRoleName = initialUserDB.getRole().getName();

        assertEquals(initialUserRoleName, USER.getRoleValue());

        EditUserRequest editUserRequest = EditUserRequest.builder()
                .firstName(initialUserFirstName)
                .lastName(initialUserLastName)
                .email(email)
                .phone(initialUserPhone)
                .roleName(MANAGER.getRoleValue())
                .urlLogo(initialUserUrlLogo)
                .status(initialUserStatus)
                .build();

        UserClient userClient = new UserClient(new Authentication(email, password).getToken());
        Response apiResponse = userClient.put(initialUserId, editUserRequest);
        assertEquals(apiResponse.statusCode(), 200);

        EditUserResponse editUserApiResponse = apiResponse.as(EditUserResponse.class);
        assertEquals(editUserApiResponse.getRoleName(), MANAGER.getRoleValue());

        UserJoinRoleDTO editedUserDB = new UserService().getUserJoinRoleDTO(email);
        assertEquals(editedUserDB.getRole().getName(), MANAGER.getRoleValue());

        editUserRequest = EditUserRequest.builder()
                .firstName(initialUserFirstName)
                .lastName(initialUserLastName)
                .email(email)
                .phone(initialUserPhone)
                .roleName(USER.getRoleValue())
                .urlLogo(initialUserUrlLogo)
                .status(initialUserStatus)
                .build();

        apiResponse = userClient.put(initialUserId, editUserRequest);
        assertEquals(apiResponse.statusCode(), 200);

        editUserApiResponse = apiResponse.as(EditUserResponse.class);
        assertEquals(editUserApiResponse.getRoleName(), USER.getRoleValue());

        editedUserDB = new UserService().getUserJoinRoleDTO(email);
        assertEquals(editedUserDB.getRole().getName(), USER.getRoleValue());
    }
}
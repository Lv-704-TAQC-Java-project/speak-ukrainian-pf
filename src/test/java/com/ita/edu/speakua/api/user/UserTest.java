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
import org.testng.asserts.SoftAssert;

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
    @Description("Verify user and manager can edit profile data")
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
        Boolean initialUserStatus = initialUserDB.getStatus();
        String initialUserUrlLogo = initialUserDB.getUrlLogo();
        String initialUserRoleName = initialUserDB.getRole().getName();

        String newUserFirstName = initialUserDB.getFirstName() + "New";
        String newUserLastName = initialUserDB.getLastName() + "New";
        String newUserPhone = "0631111111";
        String newUserUrlLogo = "/upload/some/newImage.png";
        String newUserRoleName = initialUserRoleName.equals(USER.getRoleValue()) ? MANAGER.getRoleValue() : USER.getRoleValue();

        EditUserRequest editUserRequest = EditUserRequest.builder()
                .firstName(newUserFirstName)
                .lastName(newUserLastName)
                .email(email)
                .phone(newUserPhone)
                .roleName(newUserRoleName)
                .urlLogo(newUserUrlLogo)
                .status(initialUserStatus)
                .build();
        UserClient userClient = new UserClient(new Authentication(email, password).getToken());
        Response apiResponse = userClient.put(initialUserId, editUserRequest);
        assertEquals(apiResponse.statusCode(), 200,
                "Incorrect API response status code when requested to edit user with id " + initialUserId);

        SoftAssert softly = new SoftAssert();
        EditUserResponse editUserResponse = apiResponse.as(EditUserResponse.class);
        softly.assertEquals(editUserResponse.getId(), initialUserId,
                "Api edit user first response should contain id");
        softly.assertEquals(editUserResponse.getFirstName(), newUserFirstName,
                "Api edit user first response should contain first name");
        softly.assertEquals(editUserResponse.getLastName(), newUserLastName,
                "Api edit user first response should contain last name");
        softly.assertEquals(editUserResponse.getPhone(), newUserPhone,
                "Api edit user first response should contain phone");
        softly.assertEquals(editUserResponse.getRoleName(), newUserRoleName,
                "Api edit user first response should contain role name");
        softly.assertEquals(editUserResponse.getUrlLogo(), newUserUrlLogo,
                "Api edit user first response should contain logo url");
        softly.assertEquals(editUserResponse.getStatus(), initialUserStatus,
                "Api edit user first response should contain status");

        UserJoinRoleDTO databaseUser = new UserService().getUserJoinRoleDTO(email);
        softly.assertEquals(databaseUser.getId(), initialUserId,
                "Database and API first response should have same user id");
        softly.assertEquals(databaseUser.getFirstName(), newUserFirstName,
                "Database and API first response should have same user first name");
        softly.assertEquals(databaseUser.getLastName(), newUserLastName,
                "Database and API first response should have same user last name");
        softly.assertEquals(databaseUser.getPhone(), newUserPhone,
                "Database and API first response should have same user phone");
        softly.assertEquals(databaseUser.getRole().getName(), newUserRoleName,
                "Database and API first response should have same user role name");
        softly.assertEquals(databaseUser.getUrlLogo(), newUserUrlLogo,
                "Database and API first response should have same user logo url");
        softly.assertEquals(databaseUser.getStatus(), initialUserStatus,
                "Database and API first response should have same user status");
        softly.assertAll();

        editUserRequest = EditUserRequest.builder()
                .firstName(initialUserFirstName)
                .lastName(initialUserLastName)
                .email(email)
                .phone(initialUserPhone)
                .roleName(initialUserRoleName)
                .urlLogo(initialUserUrlLogo)
                .status(initialUserStatus)
                .build();
        apiResponse = userClient.put(initialUserId, editUserRequest);
        assertEquals(apiResponse.statusCode(), 200,
                "Incorrect API response status code when requested to edit user with id " + initialUserId);

        softly = new SoftAssert();
        editUserResponse = apiResponse.as(EditUserResponse.class);
        softly.assertEquals(editUserResponse.getId(), initialUserId,
                "Api edit user second response should contain id");
        softly.assertEquals(editUserResponse.getFirstName(), initialUserFirstName,
                "Api edit user second response should contain first name");
        softly.assertEquals(editUserResponse.getLastName(), initialUserLastName,
                "Api edit user second response should contain last name");
        softly.assertEquals(editUserResponse.getPhone(), initialUserPhone,
                "Api edit user second response should contain phone");
        softly.assertEquals(editUserResponse.getRoleName(), initialUserRoleName,
                "Api edit user second response should contain role name");
        softly.assertEquals(editUserResponse.getUrlLogo(), initialUserUrlLogo,
                "Api edit user second response should contain logo url");
        softly.assertEquals(editUserResponse.getStatus(), initialUserStatus,
                "Api edit user second response should contain status");

        databaseUser = new UserService().getUserJoinRoleDTO(email);
        softly.assertEquals(databaseUser.getId(), initialUserId,
                "Database and API second response should have same user id");
        softly.assertEquals(databaseUser.getFirstName(), initialUserFirstName,
                "Database and API second response should have same user first name");
        softly.assertEquals(databaseUser.getLastName(), initialUserLastName,
                "Database and API second response should have same user last name");
        softly.assertEquals(databaseUser.getPhone(), initialUserPhone,
                "Database and API second response should have same user phone");
        softly.assertEquals(databaseUser.getRole().getName(), initialUserRoleName,
                "Database and API second response should have same user role name");
        softly.assertEquals(databaseUser.getUrlLogo(), initialUserUrlLogo,
                "Database and API second response should have same user logo url");
        softly.assertEquals(databaseUser.getStatus(), initialUserStatus,
                "Database and API second response should have same user status");
        softly.assertAll();
    }

    @DataProvider(name = "invalidNameData")
    public static Object[][] invalidNameData() {
        String firstName = "Nastia";
        String lastName = "Kukh";
        return new Object[][]{
                {"Nastia1234", lastName, "\"firstName\" can`t contain numbers"},
                {"NastiaNastiaNastiaNastiaNastia", lastName, "\"firstName\" can contain from 1 to 25 letters"},
                {"Nastia!@##$#$%", lastName, "\"firstName\" can contain only ukrainian and english letters"},
                {firstName, "Kukhar#%$#", "\"lastName\" can contain only ukrainian and english letters"},
                {firstName, "KukharKukharKukharKukharKukharKukharKukhar#", "\"lastName\" can contain from 1 to 25 letters"},
                {firstName, "Kukhar123343#", "\"lastName\" can`t contain numbers"},
        };
    }

    @Issue("TUA-415")
    @Description("Verify that user can not save changes with invalid data (fields lastName and firstName)")
    @Link("https://jira.softserve.academy/browse/TUA-415")
    @Test(dataProvider = "invalidNameData")
    public void verifyThatUserCanNotSaveChangesWithNameInvalidData(String firstName, String lastName, String expectedErrorMessage) {
        Authentication authentication = new Authentication(properties.getAdminEmail(), properties.getAdminPassword());
        int id = 203;
        UserClient userClient = new UserClient(authentication.getToken());
        EditUserRequest editUserRequest = EditUserRequest.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email("soyec48727@busantei.com")
                .phone("999999922")
                .roleName(MANAGER.getRoleValue())
                .urlLogo(null)
                .status(true)
                .build();

        Response response = userClient.put(id, editUserRequest);
        assertEquals(response.statusCode(), 400);

        ErrorResponse errorResponse = response.as(ErrorResponse.class);
        assertEquals(errorResponse.getMessage(), expectedErrorMessage);
    }
}
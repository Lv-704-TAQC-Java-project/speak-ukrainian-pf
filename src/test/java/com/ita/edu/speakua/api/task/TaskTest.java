package com.ita.edu.speakua.api.task;

import com.ita.edu.speakua.api.ApiBaseTestRunner;
import com.ita.edu.speakua.api.clients.Authentication;
import com.ita.edu.speakua.api.clients.TaskClient;
import com.ita.edu.speakua.api.models.ErrorResponse;
import com.ita.edu.speakua.api.models.task.CreateTaskRequest;
import com.ita.edu.speakua.api.models.task.CreateTaskResponse;
import com.ita.edu.speakua.api.models.task.EditTaskRequest;
import com.ita.edu.speakua.api.models.task.ReadTaskResponse;
import com.ita.edu.speakua.utils.jdbc.entity.TaskEntity;
import com.ita.edu.speakua.utils.jdbc.services.TaskService;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.testng.Assert.assertEquals;

public class TaskTest extends ApiBaseTestRunner {
    private Authentication authentication;
    private final String tomorrow = LocalDate.now().plusDays(1).toString();
    private final String picture = "/upload/some/image.png";
    private final String name = "Some name";
    private final String headerText = "Some header text header text header text header text";
    private final String description = "Some description description description description description description description";

    @BeforeClass
    public void beforeClass() {
        authentication = new Authentication(properties.getAdminEmail(), properties.getAdminPassword());
    }

    @Issue("TUA-441")
    @Description("Verify user can create Task with valid values")
    @Link("https://jira.softserve.academy/browse/TUA-441")
    @Test
    public void verifyTaskCreation() {
        TaskClient taskClient = new TaskClient(authentication.getToken());
        CreateTaskRequest createTaskRequest = CreateTaskRequest
                .builder()
                .name(name)
                .headerText(headerText)
                .description(description)
                .picture(picture)
                .startDate(tomorrow)
                .build();

        long maxTaskId = new TaskService().getTasksMaxId();
        int challengeId = 5;

        Response createTaskRawResponse = taskClient.createTask(challengeId, createTaskRequest);
        assertEquals(createTaskRawResponse.statusCode(), 200);

        CreateTaskResponse createTaskResponse = createTaskRawResponse.as(CreateTaskResponse.class);

        SoftAssert softly = new SoftAssert();
        softly.assertEquals(createTaskResponse.getId(), maxTaskId + 1);
        softly.assertEquals(createTaskResponse.getName(), name);
        softly.assertEquals(createTaskResponse.getDescription(), description);
        softly.assertEquals(createTaskResponse.getPicture(), picture);
        softly.assertEquals(createTaskResponse.getChallengeId(), challengeId);

        List<Integer> parsedDateList = Arrays.stream(tomorrow.split("-"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        softly.assertEquals(createTaskResponse.getStartDate(), parsedDateList);

        softly.assertAll();
    }

    @DataProvider(name = "createTaskInvalidData")
    public Object[][] createTaskInvalidData() {
        return new Object[][]{
                {"", headerText, description, picture, tomorrow, asList("name must not be blank", "name must contain a minimum of 5 and a maximum of 100 letters")},
                {name, "", description, picture, tomorrow, asList("headerText must contain a minimum of 40 and a maximum of 3000 letters", "headerText must not be blank")},
                {name, headerText, "", picture, tomorrow, asList("description must contain a minimum of 40 and a maximum of 3000 letters")},
                {name, headerText, description, "", tomorrow, asList("picture must not be blank", "picture Incorrect file path.", "It must be like /upload/*/*.png")},
                {name, headerText, description, picture, "", asList("startDate must not be null")},
                {name, headerText, description, " " + picture, tomorrow, asList("picture Incorrect file path.", "It must be like /upload/*/*.png")},
                {null, headerText, description, picture, tomorrow, asList("name must not be blank")},
                {name, null, description, picture, tomorrow, asList("headerText must not be blank")},
                {name, headerText, null, picture, tomorrow, asList("description must not be blank")},
        };
    }

    @Issue("TUA-443")
    @Description("Verify user can not create Task with empty fields")
    @Link("https://jira.softserve.academy/browse/TUA-443")
    @Test(dataProvider = "createTaskInvalidData")
    public void verifyTaskCreationFailsForEmptyFields(String name, String headerText, String description, String picture, String date, List<String> errors) {
        TaskClient taskClient = new TaskClient(authentication.getToken());
        CreateTaskRequest createTaskRequest = CreateTaskRequest
                .builder()
                .name(name)
                .headerText(headerText)
                .description(description)
                .picture(picture)
                .startDate(date)
                .build();

        Response createTaskRawResponse = taskClient.createTask(5, createTaskRequest);
        assertEquals(createTaskRawResponse.statusCode(), 400);

        ErrorResponse createTaskErrorResponse = createTaskRawResponse.as(ErrorResponse.class);

        SoftAssert softly = new SoftAssert();
        softly.assertEquals(createTaskErrorResponse.getStatus(), 400);

        errors.forEach(error -> {
            String errorMessage = createTaskErrorResponse.getMessage();
            softly.assertTrue(errorMessage.contains(error),
                    format("Error message [%s] should contain: \n\t\t[%s]", errorMessage, error));
        });

        softly.assertAll();
    }

    @DataProvider(name = "InvalidDescriptionAndNameData")
    public Object[][] InvalidDescriptionAndNameData() {
        return new Object[][]{
                {"name", description,
                        "name must contain a minimum of 5 and a maximum of 100 letters"},
                {new String(new char[26]).replace("\0", "name"), description,
                        "name must contain a minimum of 5 and a maximum of 100 letters"},
                {"namenameЁ, Ы,Э", description,
                        "name Помилка. Текст містить недопустимі символи"},
                {name, "descriptiondescriptiondescriptiondescri ",
                        "description must contain a minimum of 40 and a maximum of 3000 letters"},
                {name, new String(new char[300]).replace("\0", "description"),
                        "description must contain a minimum of 40 and a maximum of 3000 letters"},
                {name, "descriptiondescriptiondescriptiondescriptiondescription Ё, Ы,Э ",
                        "description Помилка. Текст містить недопустимі символи"}
        };
    }

    @Issue("TUA-445")
    @Description("Verify that user can not edit Task with invalid values")
    @Link("https://jira.softserve.academy/browse/TUA-445")
    @Test(dataProvider = "InvalidDescriptionAndNameData")
    public void verifyTaskCreationFailsForInvalidValues(String name, String description, String expectedErrorMessage) {
        TaskClient taskClient = new TaskClient(authentication.getToken());
        CreateTaskRequest createTaskRequest = CreateTaskRequest
                .builder()
                .name(name)
                .headerText(headerText)
                .description(description)
                .picture(picture)
                .startDate(tomorrow)
                .build();

        Response createTaskRawResponse = taskClient.createTask(746, createTaskRequest);
        assertEquals(createTaskRawResponse.statusCode(), 400);

        ErrorResponse createTaskErrorResponse = createTaskRawResponse.as(ErrorResponse.class);

        SoftAssert softly = new SoftAssert();
        softly.assertEquals(createTaskErrorResponse.getStatus(), 400);
        softly.assertEquals(createTaskErrorResponse.getMessage(), expectedErrorMessage);
        softly.assertAll();
    }

    @Issue("TUA-441")
    @Description("Verify that user can edit Task with valid values")
    @Link("https://jira.softserve.academy/browse/TUA-444")
    @Test
    public void verifyTaskCreationWithValidData() {
        String name = "namenamename1213#$% ";
        String description = "descriptiondescriptiondescriptiondescriptiondescription12345$%%^$# ";
        String picture = "/upload/test/test.png";
        int taskId = 703;

        TaskEntity taskBeforeChanges = new TaskService().getTaskById(taskId);

        TaskClient taskClient = new TaskClient(authentication.getToken());
        EditTaskRequest editTaskRequest = EditTaskRequest
                .builder()
                .name(name)
                .headerText(taskBeforeChanges.getHeaderText())
                .description(description)
                .picture(picture)
                .startDate(tomorrow)
                .challengeId(taskBeforeChanges.getChallengeId())
                .build();

        Response editTaskResponse = taskClient.put(taskId, editTaskRequest);
        assertEquals(editTaskResponse.statusCode(), 200);

        ReadTaskResponse readTaskResponse = editTaskResponse.as(ReadTaskResponse.class);
        TaskEntity taskAfterChanges = new TaskService().getTaskById(taskId);

        SoftAssert softly = new SoftAssert();

        softly.assertEquals(taskAfterChanges.getId(), readTaskResponse.getId());
        softly.assertEquals(taskAfterChanges.getName(), readTaskResponse.getName());
        softly.assertEquals(taskAfterChanges.getHeaderText(), readTaskResponse.getHeaderText());
        softly.assertEquals(taskAfterChanges.getDescription(), readTaskResponse.getDescription());
        softly.assertEquals(taskAfterChanges.getPicture(), readTaskResponse.getPicture());

        List<Integer> parsedDateList = Arrays.stream(tomorrow.split("-"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        softly.assertEquals(readTaskResponse.getStartDate(), parsedDateList);

        softly.assertAll();
    }
}
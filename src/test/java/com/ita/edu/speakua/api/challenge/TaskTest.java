package com.ita.edu.speakua.api.challenge;

import com.ita.edu.speakua.api.ApiBaseTestRunner;
import com.ita.edu.speakua.api.clients.Authentication;
import com.ita.edu.speakua.api.clients.TaskClient;
import com.ita.edu.speakua.api.models.task.CreateTaskRequest;
import com.ita.edu.speakua.api.models.task.CreateTaskResponse;
import com.ita.edu.speakua.utils.jdbc.services.TaskService;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

public class TaskTest extends ApiBaseTestRunner {
    private Authentication authentication;

    @BeforeClass
    public void beforeClass() {
        authentication = new Authentication(properties.getUserEmail(), properties.getUserPassword());
    }

    @Issue("TUA-441")
    @Description("Verify that user can create Task with valid values")
    @Link("https://jira.softserve.academy/browse/TUA-441")
    @Test
    public void verifyTaskCreation() {
        String name = "Some name";
        String headerText = "Some header text header text header text header text";
        String description = "Some description description description description description description description";
        String picture = "/upload/some/image.png";
        String date = "2022-09-17";

        TaskClient taskClient = new TaskClient(authentication.getToken());
        CreateTaskRequest createTaskRequest = CreateTaskRequest
                .builder()
                .name(name)
                .headerText(headerText)
                .description(description)
                .picture(picture)
                .startDate(date)
                .build();

        long maxTaskId = new TaskService().getTasksMaxId();
        int challengeId = 5;

        Response postResponse = taskClient.post(challengeId, createTaskRequest);
        assertEquals(postResponse.statusCode(), 200);

        CreateTaskResponse createTaskResponse = postResponse.as(CreateTaskResponse.class);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(createTaskResponse.getId(), maxTaskId + 1);
        softAssert.assertEquals(createTaskResponse.getName(), name);
        softAssert.assertEquals(createTaskResponse.getDescription(), description);
        softAssert.assertEquals(createTaskResponse.getPicture(), picture);
        softAssert.assertEquals(createTaskResponse.getChallengeId(), challengeId);

        List<Integer> parsedDateList = Arrays.stream(date.split("-"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        softAssert.assertEquals(createTaskResponse.getStartDate().get(0), parsedDateList.get(0));
        softAssert.assertEquals(createTaskResponse.getStartDate().get(1), parsedDateList.get(1));
        softAssert.assertEquals(createTaskResponse.getStartDate().get(2), parsedDateList.get(2));

        softAssert.assertAll();
    }
}
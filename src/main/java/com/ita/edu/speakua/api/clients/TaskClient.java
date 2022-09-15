package com.ita.edu.speakua.api.clients;

import com.ita.edu.speakua.api.models.task.CreateTaskRequest;
import io.restassured.response.Response;

public class TaskClient extends BaseClient {
    private final String authentication;

    public TaskClient(String authentication) {
        super();
        this.authentication = authentication;
    }

    public Response getAll() {
        return prepareRequest()
                .header("Authorization", "Bearer " + this.authentication)
                .when()
                .get(baseUrl + "/tasks");
    }

    public Response post(int id, CreateTaskRequest createTaskRequest) {
        return prepareRequest()
                .header("Authorization", "Bearer " + this.authentication)
                .body(createTaskRequest.json())
                .when()
                .post(baseUrl + "/challenge/" + id + "/task");
    }
}
package com.ita.edu.speakua.api.clients;

import com.ita.edu.speakua.api.models.club.request.CreateClubRequest;
import io.restassured.response.Response;

public class ClubClient extends BaseClient{

    private final String path = "/club";
    private final String authentication;


    public ClubClient(String authentication) {
        super();
        this.authentication = authentication;
    }

    public Response post(CreateClubRequest createClubRequest) {
        return prepareRequest()
                .header("Authorization", "Bearer " + this.authentication)
                .body("{\n" +
                        "  \"id\": 913,\n" +
                        "  \"categoriesName\": [\n" +
                        "    \"Спортивні секції\"\n" +
                        "  ],\n" +
                        "  \"locations\": [\n" +
                        "    {\n" +
                        "      \"id\": 23,\n" +
                        "      \"name\": \"Голосівська\",\n" +
                        "      \"address\": \"string\",\n" +
                        "      \"cityId\": 1,\n" +
                        "      \"districtId\": 1,\n" +
                        "      \"stationId\": 1,\n" +
                        "      \"cityName\": \"Київ\",\n" +
                        "      \"districtName\": \"Голосіївський\",\n" +
                        "      \"stationName\": \"Голосіївська\",\n" +
                        "      \"coordinates\": \"50.35535081747696, 30.51765754176391\",\n" +
                        "      \"longitude\": 0,\n" +
                        "      \"latitude\": 0,\n" +
                        "      \"centerId\": 2,\n" +
                        "      \"clubId\": 3,\n" +
                        "      \"phone\": \"0631133456\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"description\": \"{\\\"blocks\\\":[{\\\"key\\\":\\\"brl63\\\",\\\"text\\\":\\\"Ми поставили перед собою ціль створити мережу найкращих центрів раннього розвитку в Україні, де дітки навчатимуться з задоволенням, а батьки радітимуть від результатів.\\\",\\\"type\\\":\\\"unstyled\\\",\\\"depth\\\":1,\\\"inlineStyleRanges\\\":[],\\\"entityRanges\\\":[],\\\"data\\\":{}}],\\\"entityMap\\\":{}}\\\"\",\n" +
                        "  \"name\": \"Olq clubs new club New club new club New club new club New club new club New club new club New clubs\",\n" +
                        "  \"ageFrom\": 17,\n" +
                        "  \"ageTo\": 18,\n" +
                        "  \"urlBackground\": \"/dev/static/images/user/avatar/user1.png\",\n" +
                        "  \"urlLogo\": \"/dev/static/images/user/avatar/user1.png\",\n" +
                        "  \"urlGallery\": [\n" +
                        "    {\n" +
                        "      \"urlGallery\": \"https://apiTest.API\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"isOnline\": true,\n" +
                        "  \"contacts\": \"string\",\n" +
                        "  \"isApproved\": true,\n" +
                        "  \"userId\": 1,\n" +
                        "  \"centerId\": 1,\n" +
                        "  \"clubExternalId\": 4,\n" +
                        "  \"centerExternalId\": 5\n" +
                        "}")
                .when()
                .post(String.format("%s%s", baseUrl, path));
    }

    public Response postParseJson(String filePath, String clubName) {
        return prepareRequest()
                .header("Authorization", "Bearer " + this.authentication)
                .body(CreateClubRequest.parseJson(filePath, clubName))
                .when()
                .post(String.format("%s%s", baseUrl, path));
    }
}

package com.ita.edu.speakua.api.club;

import com.ita.edu.speakua.api.ApiBaseTestRunner;
import com.ita.edu.speakua.api.clients.Authentication;
import com.ita.edu.speakua.api.clients.ClubClient;
import com.ita.edu.speakua.api.models.club.request.CreateClubRequest;
import com.ita.edu.speakua.api.models.club.request.Location;
import com.ita.edu.speakua.api.models.club.request.UrlGallery;
import com.ita.edu.speakua.api.models.club.response.ReadClubResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

public class ClubTest extends ApiBaseTestRunner {
    private Authentication authentication;

    @BeforeClass
    public void beforeClass() {
        authentication = new Authentication(properties.getUserEmail(), properties.getUserPassword());
    }

    @Issue("TUA-505")
    @Description("Verify admin can create club with name more than 100 chars")
    @Link("https://jira.softserve.academy/browse/TUA-505")
    @Test
    public void successPostClubTest() {
        ClubClient clubClient = new ClubClient(authentication.getToken());

        ArrayList<String> categoriesName = new ArrayList<>();
        categoriesName.add("Спортивні секції");

        Location location = Location
                .builder()
                .id(23)
                .name("Голосівська")
                .address("string")
                .cityId(1)
                .districtId(1)
                .stationId(1)
                .cityName("Київ")
                .districtName("Голосіївський")
                .stationName("Голосіївська")
                .coordinates("50.35535081747696, 30.51765754176391")
                .longitude(0)
                .latitude(0)
                .centerId(2)
                .clubId(3)
                .phone("0937777777")
                .build();

        ArrayList<Location> locations = new ArrayList<>();
        locations.add(location);

        UrlGallery urlGallery = new UrlGallery();
        urlGallery.setUrlGallery("https://apiTest.API");

        ArrayList<UrlGallery> urlGaleryList = new ArrayList<>();
        urlGaleryList.add(urlGallery);

        String clubName = RandomStringUtils.randomAlphabetic(100);

        CreateClubRequest createClubRequest = CreateClubRequest
                .builder()
                .id(900)
                .categoriesName(categoriesName)
                .locations(locations)
                .description("{\\\"blocks\\\":[{\\\"key\\\":\\\"brl63\\\",\\\"text\\\":" +
                        "\\\"Ми поставили перед собою ціль створити мережу найкращих центрів раннього розвитку в Україні, " +
                        "де дітки навчатимуться з задоволенням, а батьки радітимуть від результатів." +
                        "\\\",\\\"type\\\":\\\"unstyled\\\",\\\"depth\\\":1,\\\"inlineStyleRanges\\\":[],\\\"entityRanges\\\":[]," +
                        "\\\"data\\\":{}}],\\\"entityMap\\\":{}}\\\"")
                .name(clubName)
                .ageFrom(4)
                .ageTo(8)
                .urlBackground("/dev/static/images/user/avatar/user1.png")
                .urlLogo("/dev/static/images/user/avatar/user1.png")
                .urlGallery(urlGaleryList)
                .isOnline(true)
                .contacts("string")
                .isApproved(true)
                .userId(1)
                .centerId(1)
                .clubExternalId(4)
                .centerExternalId(5)
                .build();

        Response response = clubClient.post(createClubRequest);
        assertEquals(response.statusCode(), 200);
        System.out.println(response.asPrettyString());

        ReadClubResponse readClubResponse = response.as(ReadClubResponse.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(readClubResponse.getName(), clubName,
                "Club name should be correct");
        softAssert.assertEquals(readClubResponse.getAgeFrom(), 4,
                "AgeFrom should be equal to expected");
        softAssert.assertEquals(readClubResponse.getAgeTo(), 8,
                "AgeTo should be equal to expected");
        softAssert.assertEquals(readClubResponse.getIsApproved(), "true",
                "IsApproved value should be correct");
        softAssert.assertEquals(readClubResponse.getIsOnline(), "true",
                "IsOnline value should be correct");
        softAssert.assertAll();
    }
}


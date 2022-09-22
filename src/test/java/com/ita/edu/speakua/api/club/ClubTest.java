package com.ita.edu.speakua.api.club;

import com.ita.edu.speakua.api.ApiBaseTestRunner;
import com.ita.edu.speakua.api.clients.Authentication;
import com.ita.edu.speakua.api.clients.ClubClient;
import com.ita.edu.speakua.api.models.ErrorResponse;
import com.ita.edu.speakua.api.models.club.request.CreateClubRequest;
import com.ita.edu.speakua.api.models.club.request.Location;
import com.ita.edu.speakua.api.models.club.request.UrlGallery;
import com.ita.edu.speakua.api.models.club.response.LocationResponse;
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
        authentication = new Authentication(properties.getAdminEmail(), properties.getAdminPassword());
    }

    @Issue("TUA-505")
    @Description("Verify admin can create club with name more than 100 chars")
    @Link("https://jira.softserve.academy/browse/TUA-505")
    @Test
    public void successPostClubTest() {
        ClubClient clubClient = new ClubClient(authentication.getToken());

        ArrayList<String> categoriesName = new ArrayList<>();
        String categoryName = "Спортивні секції";
        categoriesName.add(categoryName);

        int locationId = 23;
        int cityId = 1;
        int districtId = 1;
        int stationId = 1;
        String locationName = "Голосівська";
        String cityName = "Київ";
        String districtName = "Голосіївський";
        String stationName = "Голосіївська";
        String coordinates = "50.35535081747696, 30.51765754176391";
        String phone = "0937777777";

        Location location = Location
                .builder()
                .id(locationId)
                .name(locationName)
                .address("string")
                .cityId(cityId)
                .districtId(districtId)
                .stationId(stationId)
                .cityName(cityName)
                .districtName(districtName)
                .stationName(stationName)
                .coordinates(coordinates)
                .centerId(2)
                .clubId(3)
                .phone(phone)
                .build();

        ArrayList<Location> locations = new ArrayList<>();
        locations.add(location);

        String urlGalleryValue = "https://apiTest.API";
        UrlGallery urlGallery = UrlGallery
                .builder()
                .urlGallery(urlGalleryValue)
                .build();

        ArrayList<UrlGallery> urlGalleryList = new ArrayList<>();
        urlGalleryList.add(urlGallery);

        String clubName = RandomStringUtils.randomAlphabetic(100);
        String description = "{\"blocks\":[{\"key\":\"brl63\",\"text\":" +
                "\"Ми поставили перед собою ціль створити мережу найкращих центрів раннього розвитку в Україні, " +
                "де дітки навчатимуться з задоволенням, а батьки радітимуть від результатів." +
                "\",\"type\":\"unstyled\",\"depth\":1,\"inlineStyleRanges\":[],\"entityRanges\":[]," +
                "\"data\":{}}],\"entityMap\":{}}\"";
        int ageFrom = 4;
        int ageTo = 8;
        int userId = 1;
        int centerId = 1;
        int clubExternalId = 123;
        int centerExternalId = 111;
        String urlBackground = "/dev/static/images/user/avatar/user1.png";
        String urlLogo = "/dev/static/images/user/avatar/user1.png";
        String contacts = "Our new contacts";

        CreateClubRequest createClubRequest = CreateClubRequest
                .builder()
                .categoriesName(categoriesName)
                .locations(locations)
                .description(description)
                .name(clubName)
                .ageFrom(ageFrom)
                .ageTo(ageTo)
                .urlBackground(urlBackground)
                .urlLogo(urlLogo)
                .urlGallery(urlGalleryList)
                .isOnline(true)
                .contacts(contacts)
                .isApproved(true)
                .userId(userId)
                .centerId(centerId)
                .clubExternalId(clubExternalId)
                .centerExternalId(centerExternalId)
                .build();

        Response response = clubClient.post(createClubRequest);
        assertEquals(response.statusCode(), 200);

        ReadClubResponse readClubResponse = response.as(ReadClubResponse.class);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(readClubResponse.getCategories().get(0).getName(), categoryName,
                "Categories name should be correct");

        LocationResponse actualLocation = readClubResponse.getLocations().get(0);
        softAssert.assertEquals(actualLocation.getName(), locationName,
                "Location name should be correct");
        softAssert.assertEquals(actualLocation.getId(), locationId,
                "Location id should be correct");
        softAssert.assertEquals(actualLocation.getCityId(), cityId,
                "Location city id should be correct");

        //TODO: this test may fail as district is and station id values may not be correct
        softAssert.assertEquals(actualLocation.getDistrictId(), districtId,
                "Location district id should be correct");
        softAssert.assertEquals(actualLocation.getStationId(), stationId,
                "Location station id should be correct");

        softAssert.assertEquals(actualLocation.getCityName(), cityName,
                "Location city name should be correct");
        softAssert.assertEquals(actualLocation.getDistrictName(), districtName,
                "Location district name should be correct");
        softAssert.assertEquals(actualLocation.getStationName(), stationName,
                "Location station name should be correct");

        //TODO: this test may fail as coordinates value is null
        softAssert.assertEquals(actualLocation.getCoordinates(), coordinates,
                "Coordinates should be correct");
        String[] coordinatesArr = coordinates.split(", ");
        softAssert.assertEquals(actualLocation.getLatitude(), Double.parseDouble(coordinatesArr[0]),
                "Latitude should be correct");
        softAssert.assertEquals(actualLocation.getLongitude(), Double.parseDouble(coordinatesArr[1]),
                "Longitude should be correct");

        softAssert.assertEquals(readClubResponse.getDescription(), description,
                "Description should be correct");
        softAssert.assertEquals(readClubResponse.getName(), clubName,
                "Club name should be correct");
        softAssert.assertEquals(readClubResponse.getAgeFrom(), ageFrom,
                "AgeFrom should be equal to expected");
        softAssert.assertEquals(readClubResponse.getAgeTo(), ageTo,
                "AgeTo should be equal to expected");
        softAssert.assertEquals(readClubResponse.getUrlBackground(), urlBackground,
                "Url background should be correct");
        softAssert.assertEquals(readClubResponse.getUrlLogo(), urlLogo,
                "Url logo should be correct");
        softAssert.assertEquals(readClubResponse.getUrlGallery().get(0).getUrl(), urlGalleryValue,
                "Url gallery value should be correct");
        softAssert.assertEquals(readClubResponse.getIsOnline(), "true",
                "IsOnline value should be correct");
        //TODO: this test may fail as contacts value is null
        softAssert.assertEquals(readClubResponse.getCenter().getContacts(), contacts,
                "Contacts should be correct");
        softAssert.assertEquals(readClubResponse.getIsApproved(), "true",
                "IsApproved value should be correct");
        softAssert.assertEquals(readClubResponse.getUser().getId(), userId,
                "User id should be correct");
        softAssert.assertEquals(readClubResponse.getCenter().getId(), centerId,
                "Center id should be correct");
        softAssert.assertAll();
    }

    @Issue("TUA-501")
    @Link("https://jira.softserve.academy/browse/TUA-501")
    @Description("Verify that User as \"Керiвник гуртка\" cannot create new club is in a center " +
            "with Russian alphabet for \"Назва\" field")
    @Test
    public void unSuccessPostTest() {
        ClubClient clubClient = new ClubClient(authentication.getToken());

        Location location = Location
                .builder()
                .name("Голосівська")
                .cityName("Київ")
                .districtName("Голосіївський")
                .stationName("Голосіївська")
                .address("https://speak-ukrainian.org.ua/dev/club/910")
                .coordinates("50.35535081747696, 30.51765754176391")
                .phone("0937777777")
                .build();

        UrlGallery urlGallery = UrlGallery
                .builder()
                .urlGallery("https://apiTest.API")
                .build();
        ArrayList<UrlGallery> urlGalleries = new ArrayList<>();
        urlGalleries.add(urlGallery);
        ArrayList<Location> locations = new ArrayList<>();
        locations.add(location);

        ArrayList<String> categoryNames = new ArrayList<>();
        categoryNames.add("Вокальна студія");
        categoryNames.add("музика");
        categoryNames.add(" музичні інструменти");

        CreateClubRequest createClubRequest = CreateClubRequest
                .builder()
                .categoriesName(categoryNames)
                .name("Э э ъ Ъ Ы ы")
                .ageFrom(2)
                .ageTo(18)
                .urlLogo("/dev/static/images/user/avatar/user1.png")
                .urlBackground("/dev/static/images/user/avatar/user1.png")
                .isOnline(true)
                .description("{\"blocks\":[{\"key\":\"brl63\",\"text\":" +
                        "\"Ми поставили перед собою ціль створити мережу найкращих центрів раннього розвитку в Україні, " +
                        "де дітки навчатимуться з задоволенням, а батьки радітимуть від результатів.\",\"type\":" +
                        "\"unstyled\",\"depth\":1,\"inlineStyleRanges\":[],\"entityRanges\":[]," +
                        "\"data\":{}}],\"entityMap\":{}}")
                .userId(264)
                .locations(locations)
                .contacts("{\"1\"::\"ліл\"}")
                .centerId(2)
                .urlGallery(urlGalleries)
                .build();
        Response postResponse = clubClient.post(createClubRequest);
        ErrorResponse errorResponse = postResponse.as(ErrorResponse.class);

        SoftAssert softly = new SoftAssert();

        softly.assertEquals(errorResponse.getStatus(), 400);
        softly.assertEquals(errorResponse.getMessage(),
                "name Помилка. Присутні недопустимі символи",
                "Message should be correct");
        softly.assertAll();
    }
}


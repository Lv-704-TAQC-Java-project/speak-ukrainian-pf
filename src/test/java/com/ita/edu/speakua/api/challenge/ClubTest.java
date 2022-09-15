package com.ita.edu.speakua.api.challenge;

import com.ita.edu.speakua.api.ApiBaseTestRunner;
import com.ita.edu.speakua.api.clients.Authentication;
import com.ita.edu.speakua.api.clients.ClubClient;
import com.ita.edu.speakua.api.models.club.request.CreateClubRequest;
import com.ita.edu.speakua.api.models.club.request.Location;
import com.ita.edu.speakua.api.models.club.request.UrlGallery;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

public class ClubTest extends ApiBaseTestRunner {
    private Authentication authentication;

    @BeforeClass
    public void beforeClass() {
        authentication = new Authentication(properties.getUserEmail(), properties.getUserPassword());
    }

    @Test
    public void successPostTest() {
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
                .phone("937777777")
                .build();

        ArrayList<Location> locations = new ArrayList<>();
        locations.add(location);

        UrlGallery urlGalery = new UrlGallery();
        urlGalery.setUrlGallery("https://apiTest.API");

        ArrayList<UrlGallery> urlGaleryList = new ArrayList<>();
        urlGaleryList.add(urlGalery);

        CreateClubRequest createClubRequest = CreateClubRequest
                .builder()
                .id(913)
                .categoriesName(categoriesName)
                .locations(locations)
                .description("{\"blocks\":[{\"key\":\"brl63\",\"text\":\"" +
                        "Ми поставили перед собою ціль створити мережу найкращих центрів раннього розвитку в Україні, " +
                        "де дітки навчатимуться з задоволенням, а батьки радітимуть від результатів." +
                        "\",\"type\":\"unstyled\",\"depth\":1,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}]," +
                        "\"entityMap\":{}}\"")
                .name(RandomStringUtils.randomAlphanumeric(100))
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

        System.out.println(createClubRequest.json());

        Response response = clubClient.post(createClubRequest);
        assertEquals(response.statusCode(), 200);
    }

    @Test
    public void successPostTestParseJson() {
        ClubClient clubClient = new ClubClient(authentication.getToken());
        Response response = clubClient.postParseJson("src/test/resources/create_club.json",
                RandomStringUtils.randomAlphanumeric(100));
        assertEquals(response.statusCode(), 200);
    }
}


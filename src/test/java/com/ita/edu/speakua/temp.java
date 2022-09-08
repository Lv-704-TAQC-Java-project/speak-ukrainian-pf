package com.ita.edu.speakua;

import com.ita.edu.speakua.api.clients.SingInClient;
import com.ita.edu.speakua.api.models.login.SingInRequest;
import com.ita.edu.speakua.api.models.login.SingInResponse;
import com.ita.edu.speakua.utils.ConfigProperties;
import com.ita.edu.speakua.utils.jdbc.dao.CenterDAO;
import com.ita.edu.speakua.utils.jdbc.dao.CityDAO;
import com.ita.edu.speakua.utils.jdbc.entity.CenterEntity;
import com.ita.edu.speakua.utils.jdbc.entity.CityEntity;
import com.ita.edu.speakua.utils.jdbc.entity.ClubEntity;
import com.ita.edu.speakua.utils.jdbc.services.ClubService;
import com.ita.edu.speakua.utils.jdbc.services.LocationService;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;


public class temp {
    ClubService clubService = new ClubService();
    protected static final ConfigProperties property =  new ConfigProperties();

    @Test
    public void jdbs() {
        CityDAO cityDAO = new CityDAO();
        List<CityEntity> cities = cityDAO.selectAll();
        System.out.println(cities);
        CityEntity city = cityDAO.selectById(3);
        System.out.println(city);

        System.out.println("===============================================");

        CenterDAO centerDAO = new CenterDAO();
        List<CenterEntity> centers = centerDAO.selectAllCenters();
        CenterEntity center = centerDAO.selectCenterById(1);
//        List<CenterNameEntity> firstSixCentersByNameAsc = centerDAO.firstSixNamesAsc();
//        List<CenterNameEntity> firstSixCentersByNameDesc = centerDAO.firstSixNamesDesc();
        System.out.println(center);
//        System.out.println(firstSixCentersByNameAsc);
//        System.out.println(firstSixCentersByNameDesc);
//        System.out.println(centers);

//        TaskDAO taskDAO = new TaskDAO();
//        List<TaskEntity> tasks = taskDAO.selectLikeName("Maksym test");
//        System.out.println(tasks);
    }

    @Test
    public void jdbcClub() {

        ClubEntity club = clubService.selectById(1);
        System.out.println(club);
        System.out.println("===============================================");
//        List<ClubEntity> sixNamesAsc = clubDAO.parseSixNamesAsc();
//        System.out.println(sixNamesAsc);

    }

    @Test()
    public void location() {
        LocationService locationService = new LocationService();
        System.out.println(locationService.getLocationByName("club_loc_!!!"));
    }


    @Test
    public void api_login_test() {
        SingInClient client = new SingInClient();
        SingInRequest cred = new SingInRequest(property.getUserEmail(), property.getUserPassword());
        Response response = client.successSignInRequest(cred);
        SingInResponse singInData = response.as(SingInResponse.class);

        System.out.println(singInData);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 200);
        softAssert.assertEquals(singInData.getId(), 1L);
        softAssert.assertEquals(singInData.getEmail(), property.getUserEmail());
        System.out.println(singInData.getAccessToken());

    }
}
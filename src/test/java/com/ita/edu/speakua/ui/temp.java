package com.ita.edu.speakua.ui;

import com.ita.edu.speakua.ui.utils.jdbc.dao.CenterDAO;
import com.ita.edu.speakua.ui.utils.jdbc.dao.CityDAO;
import com.ita.edu.speakua.ui.utils.jdbc.dao.ClubDAO;
import com.ita.edu.speakua.ui.utils.jdbc.dao.LocationsDAO;
import com.ita.edu.speakua.ui.utils.jdbc.entity.*;
import org.testng.annotations.Test;

import java.util.List;

public class temp {
    @Test
    public void jdbs() {
        CityDAO cityDAO = new CityDAO();
        List<CityEntity> cities = cityDAO.selectAll();
        System.out.println(cities);
        CityEntity city = cityDAO.selectById(3);
        System.out.println(city);

        System.out.println("===============================================");

        CenterDAO centerDAO = new CenterDAO();
        List<CenterEntity> centers = centerDAO.selectAll();
        CenterEntity center = centerDAO.selectById(1);
        List<CenterNameEntity> firstSixCentersByNameAsc = centerDAO.firstSixNamesAsc();
        List<CenterNameEntity> firstSixCentersByNameDesc = centerDAO.firstSixNamesDesc();
        System.out.println(center);
        System.out.println(firstSixCentersByNameAsc);
        System.out.println(firstSixCentersByNameDesc);
//        System.out.println(centers);
    }

    @Test
    public void jdbcClub() {
        ClubDAO clubDAO = new ClubDAO();
        ClubEntity club = clubDAO.selectById(1);
        System.out.println(club);
        System.out.println("===============================================");
        List<ClubNameEntity> sixNamesAsc = clubDAO.selectSixNamesAsc();
        System.out.println(sixNamesAsc);
        System.out.println("===============================================");
        List<ClubNameEntity> sixNamesDesc = clubDAO.selectSixNamesDesc();
        System.out.println(sixNamesDesc);
        System.out.println("===============================================");
        List<ClubRatingEntity> sixRatingsAsc = clubDAO.selectSixRatingsAsc();
        System.out.println(sixRatingsAsc);
        System.out.println("===============================================");
        List<ClubRatingEntity> sixRatingsDesc = clubDAO.selectSixRatingsDesc();
        System.out.println(sixRatingsDesc);
    }

    @Test
    public void location() {
        LocationsDAO locationsDAO = new LocationsDAO();
        List<LocationsEntity> locations = locationsDAO.selectByName("club_loc_!!!");
//        List<LocationsEntity> locations = locationsDAO.selectByName("center_location_first.....");

        System.out.println(locations.toString());
    }
}
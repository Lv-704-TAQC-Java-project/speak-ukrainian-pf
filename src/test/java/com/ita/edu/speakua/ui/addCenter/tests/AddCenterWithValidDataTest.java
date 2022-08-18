package com.ita.edu.speakua.ui.addCenter.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addCenterComponent.AddCenterMainInfoComponent;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addLocation.Location;
import com.ita.edu.speakua.ui.runners.AddCenterTestRunner;
import com.ita.edu.speakua.ui.runners.LoginTestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCenterWithValidDataTest extends AddCenterTestRunner {

    @DataProvider(name = "addCenterData")
    public Object[][] addCenterData() {
        return new Object[][]{
                {"Center#1", "Київ", "Деснянський", "вул. Садова, 1а", "50.4485253, 30.4735083", "0632233456", "Нормальний"}
        };
    }

    @Test(dataProvider = "addCenterData")
    public void verifyClubIsAdded(String centerName, String city, String region, String address, String coordinates, String phoneNumber, String club) {
        String locationName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        addCenterMainInfoComponent
                .inputName(centerName)
                .addLocation(new Location(locationName, city, region, address, coordinates, phoneNumber))
                .chooseLocation(locationName)
                .openNextStep()
                .enterPhoneNumber(phoneNumber)
                .openNextStep()
                .addDescribe("Lorem Ipsum is simply dummy text of the kkkkkkkkkkkkkk")
                .openNextStep()
                .chooseClub(club)
                .addCenter();
        Assert.assertTrue(new HomePage(driver).isHomePageOpened());

    }

}

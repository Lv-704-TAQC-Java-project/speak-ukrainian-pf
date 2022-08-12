package com.ita.edu.speakua.ui.addCenter.tests;

import com.ita.edu.speakua.ui.header.HeaderComponent;
import com.ita.edu.speakua.ui.runners.LoginTestRunner;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddLocationTest extends LoginTestRunner {

    @DataProvider(name = "menuDataAddLocation")
    public Object[][] cityFromDropDownAddLocationMenu() {
        return new Object[][]{
                {"Київ", "Деснянський", "вул. Садова, 1а", "50.4485253, 30.4735083", "0632233456"}
        };
    }

    @Issue("TUA-158")
    @Description("Verify that admin can add new location to the locations list")
    @Test(dataProvider = "menuDataAddLocation")
    public void addLocationTest(String city, String region, String address, String coordinates, String phoneNumber) {
        String name = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        HeaderComponent header = new HeaderComponent(driver);
        header
                .openAdminProfileMenu()
                .openAddCenterModal()
                .clickAddLocationButton()
                .fillInLocationNameField(name)
                .selectCityByName(city)
                .selectRegionByName(region)
                .fillInAddressField(address)
                .fillInGeographicСoordinatesField(coordinates)
                .fillInPhoneNumberField(phoneNumber)
                .addButtonClick();

        Assert.assertTrue(header
                .getAdminProfileMenuComponent()
                .getAddCenterMainInfoComponent()
                .isLocationAdded(name),
                "New Location is not added");
    }
}

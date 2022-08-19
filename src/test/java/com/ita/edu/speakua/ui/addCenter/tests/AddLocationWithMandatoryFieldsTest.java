package com.ita.edu.speakua.ui.addCenter.tests;

import com.ita.edu.speakua.ui.header.Header;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addLocation.Location;
import com.ita.edu.speakua.ui.runners.LoginTestRunner;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddLocationWithMandatoryFieldsTest extends LoginTestRunner {

    @DataProvider(name = "addLocationValidData")
    public Object[][] addLocationValidData() {
        return new Object[][]{
                {"Харків", "вул. Мазепи 55", "50.4485253, 30.4735083", "0977777777"}
        };
    }

    @Issue("TUA-159")
    @Description("Verify that admin can add new location to the locations list filling only mandatory fields")
    @Test(dataProvider = "addLocationValidData")
    public void addLocationTest(String city, String address, String coordinates, String phoneNumber) {

        String name = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        Header header = new Header(driver);

        boolean isLocationAdded = header
                .openAdminProfileMenu()
                .openAddCenterModal()
                .addLocationWithMandatoryFields(new Location(name, city, address, coordinates, phoneNumber))
                .isLocationAdded(name);
        Assert.assertTrue(isLocationAdded, "Location is not added!");
    }
}

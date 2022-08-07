package com.ita.edu.speakua.ui.addCenter.tests;

import com.ita.edu.speakua.ui.header.HeaderComponent;
import com.ita.edu.speakua.ui.runners.BaseTestRunnerWithLogIn;
import dev.failsafe.function.CheckedRunnable;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.swing.*;

public class AddLocationTest extends BaseTestRunnerWithLogIn {

    @DataProvider(name = "cityFromDropDownAddLocationMenu")
    public Object[][] cityFromDropDownAddLocationMenu() {
        return new Object[][]{
                {"Нова локація", "Київ", "Деснянський", "вул. Садова, 1а", "0632233456"}
        };
    }

    @Test(dataProvider = "cityFromDropDownAddLocationMenu")
    public void addLocationTest(String Name, String City, String Region, String Address, String PhoneNumber) {

        HeaderComponent header = new HeaderComponent(driver);
        header
                .openAdminProfileMenu()
                .openAddCenterModal()
                .clickAddLocationButton()
                .fillInLocationNameField(Name)
                .selectCityByName(City)
                .selectRegionByName(Region)
                .fillInAddressField(Address)
                .fillInPhoneNumberField(PhoneNumber);
//         .addButtonClick();
//
//        boolean newLocationIsAdded = header
//                .getAdminProfileMenuComponent()
//                .getAddCenterMainInfoComponent()
//                .getLocationItem(Name).isDisplayed();
//
//        Assert.assertTrue(newLocationIsAdded);
    }
}

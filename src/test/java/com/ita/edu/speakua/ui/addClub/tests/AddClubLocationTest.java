package com.ita.edu.speakua.ui.addClub.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.runners.LoginTestRunner;
import io.qameta.allure.Issue;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddClubLocationTest extends LoginTestRunner {
    @DataProvider(name = "cityFromDropDownAddLocationMenu")
    public Object[][] cityFromDropDownAddLocationMenu() {
        return new Object[][]{
                {"Київ", "Деснянський", "вул. Садова, 1а", "50.4485253, 30.4735083", "0632233456"}
        };
    }

    @Issue("TUA-237")
    @Test(dataProvider = "cityFromDropDownAddLocationMenu")
    public void test(String city, String region, String address, String coordinates, String phoneNumber) {
        var addClubContactComponent = new HomePage(driver)
                .openAdminProfileMenu()
                .openUserProfilePage()
                .openAddClubModal()
                .inputNameOfClub("Спортивні танці")
                .selectCategoryClub("Спортивні секції")
                .inputAgeFrom(4)
                .inputAgeTo(8)
                .openNextStep();
        String name = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        addClubContactComponent
                .addLocation()
                .fillInLocationNameField(name)
                .selectCityByName(city)
                .selectRegionByName(region)
                .fillInAddressField(address)
                .fillInGeographicCoordinatesField(coordinates)
                .fillInPhoneNumberField(phoneNumber)
                .addButtonClick();
        addClubContactComponent.inputEmail("a@gdg.ooo");

//        Assert.assertTrue(c.isAddButtonEnabled());

    }


}

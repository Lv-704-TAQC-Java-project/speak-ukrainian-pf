package com.ita.edu.speakua.ui.club.tests;

import com.ita.edu.speakua.ui.header.HeaderComponent;
import com.ita.edu.speakua.ui.runners.BaseTestRunnerWithLogIn;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddLocationTest extends BaseTestRunnerWithLogIn {

    @DataProvider(name = "cityFromDropDownAddLocationMenu")
    public Object[][] cityFromDropDownAddLocationMenu() {
        return new Object[][]{
                {"Київ"}
        };
    }

    @Test(dataProvider = "cityFromDropDownAddLocationMenu")
    public void addLocationTest(String city) {
        new HeaderComponent(driver)
                .openAdminProfileMenu()
                .openAddCenterModal()
                .clickAddLocationButton();
    }


}

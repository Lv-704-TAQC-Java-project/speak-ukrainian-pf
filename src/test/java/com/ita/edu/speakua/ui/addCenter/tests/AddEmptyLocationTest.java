package com.ita.edu.speakua.ui.addCenter.tests;

import com.ita.edu.speakua.ui.header.HeaderComponent;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addCenterComponent.AddLocationComponent;
import com.ita.edu.speakua.ui.runners.LoginTestRunner;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddEmptyLocationTest extends LoginTestRunner {
    @Issue("TUA-160")
    @Test
    public void addLocationTest() {
        AddLocationComponent locationComponent = new HeaderComponent(driver)
                .openAdminProfileMenu()
                .openAddCenterModal()
                .clickAddLocationButton();

        Assert.assertFalse(locationComponent.isAddButtonEnabled());

    }
}

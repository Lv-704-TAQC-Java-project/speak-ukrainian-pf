package com.ita.edu.speakua.ui.addCenter.tests;

import com.ita.edu.speakua.ui.header.Header;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addLocationModal.AddLocationModal;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addLocationModal.Location;
import com.ita.edu.speakua.ui.runners.SignInTestRunner;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddEmptyLocationTest extends SignInTestRunner {
    @Issue("TUA-160")
    @Test
    public void addLocationTest() {
        new Header(driver)
                .openAdminProfileMenu()
                .openAddCenterModal()
                .addLocation(new Location("", "", "", "", "", ""));


        Assert.assertFalse(new AddLocationModal(driver).isAddButtonEnabled());

    }
}

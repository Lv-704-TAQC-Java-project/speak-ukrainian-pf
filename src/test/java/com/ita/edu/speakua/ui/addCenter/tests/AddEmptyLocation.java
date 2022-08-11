package com.ita.edu.speakua.ui.addCenter.tests;

import com.ita.edu.speakua.ui.header.HeaderComponent;
import com.ita.edu.speakua.ui.runners.BaseTestRunnerWithLogIn;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddEmptyLocation extends BaseTestRunnerWithLogIn{
    @Test
    public void addLocationTest() {

        var c=new HeaderComponent(driver)
                .openAdminProfileMenu()
                .openAddCenterModal()
                .clickAddLocationButton();

        Assert.assertFalse(c.isAddButtonEnabled());

    }
}

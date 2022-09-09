package com.ita.edu.speakua.ui.advancedSearch.tests;

import com.ita.edu.speakua.ui.runners.AdvancedSearchTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AdvancedSearchChildAgeTest extends AdvancedSearchTestRunner {

    @DataProvider(name = "childAgeValidData")
    public Object[][] childAgeValidData() {
        return new Object[][]{
                {2, 2},
                {9, 9},
                {18, 18}

        };
    }

    @Issue("TUA-210")
    @Description("Verify that user can enter valid child age")
    @Test(dataProvider = "childAgeValidData")
    public void advancedSearchChildAgePositiveTest(int enteredAge, int expectedAge) {
        advancedSearchPanel.setChildAge(enteredAge);
        int actualAge = advancedSearchPanel.getChildAge();
        Assert.assertEquals(actualAge, expectedAge);
    }

    @DataProvider(name = "childAgeNegativeData")
    public Object[][] childAgeNegativeData() {
        return new Object[][]{
                {1, 2},
                {19, 18}
        };
    }

    @Issue("TUA-210")
    @Description("Verify that user can not enter child age out of range 2 - 18")
    @Test(dataProvider = "childAgeNegativeData")
    public void advancedSearchChildAgeNegativeTest(int enteredAge, int expectedAge) {
        advancedSearchPanel.setChildAge(enteredAge);
        int actualAge = advancedSearchPanel.getChildAge();
        Assert.assertEquals(actualAge, expectedAge);
    }

}

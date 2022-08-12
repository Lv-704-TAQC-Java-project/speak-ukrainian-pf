package com.ita.edu.speakua.ui.advancedSearch.tests;

import com.ita.edu.speakua.ui.runners.AdvancedSearchTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AdvancedSearchTest extends AdvancedSearchTestRunner {

    @Test
    public void verifyItemsAdvancedSearchPanelAreActivated() {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(advancedSearchPanel.cityListIsActivated(),
                "City selected list should be activated");
        softAssert.assertTrue(advancedSearchPanel.districtListIsActivated(),
                "District selected list should be activated");
        softAssert.assertTrue(advancedSearchPanel.metroListIsActivated(),
                "Metro station selected list should be activated");
        softAssert.assertTrue(advancedSearchPanel.checkOnlineIsActivated(),
                "Checkbox is online should be activated");
        softAssert.assertTrue(advancedSearchPanel.categoriesListIsActivated(),
                "Category checkbox list should be activated");
        softAssert.assertTrue(advancedSearchPanel.ageChildInputActivated(),
                "Age input should be activated");

        softAssert.assertAll();
    }

    @Issue("TUA-510")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify disability of some elements after selecting center")
    @Test
    public void verifySomeElementsIsDisabledAfterSelectingCenter() {
        advancedSearchPanel.centerRadioButtonClick();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertFalse(advancedSearchPanel.availableOnlineCheckboxIsDisplayed(),
                "Available online checkbox is displayed");
        softAssert.assertFalse(advancedSearchPanel.categoriesBlockIsDisplayed(),
                "Categories block online checkbox is displayed");
        softAssert.assertFalse(advancedSearchPanel.childAgeBlockIsDisplayed(),
                "Child age block block online checkbox is displayed");

        softAssert.assertAll();
    }
}

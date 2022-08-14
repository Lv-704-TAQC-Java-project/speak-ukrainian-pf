package com.ita.edu.speakua.ui.advancedSearch.tests;

import com.ita.edu.speakua.ui.runners.AdvancedSearchTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AdvancedSearchTest extends AdvancedSearchTestRunner {

    @Issue("TUA-509")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that all parameters are activated with the selected club radio button")
    @Test
    public void verifyItemsAdvancedSearchPanelAreActivated() {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(advancedSearchPanel.isCityListActivated(),
                "City selected list should be activated");
        softAssert.assertTrue(advancedSearchPanel.isDistrictListActivated(),
                "District selected list should be activated");
        softAssert.assertTrue(advancedSearchPanel.isMetroListActivated(),
                "Metro station selected list should be activated");
        softAssert.assertTrue(advancedSearchPanel.isCheckOnlineActivated(),
                "Checkbox is online should be activated");
        softAssert.assertTrue(advancedSearchPanel.isCategoriesListActivated(),
                "Category checkbox list should be activated");
        softAssert.assertTrue(advancedSearchPanel.isAgeChildInputActivated(),
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

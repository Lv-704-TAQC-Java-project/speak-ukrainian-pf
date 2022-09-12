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
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that all parameters are activated with the selected club radio button")
    @Test
    public void verifyItemsAdvancedSearchPanelAreActivated() {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(advancedSearchPanel.isCitySelectorVisible(),
                "City selector should be activated");
        softAssert.assertTrue(advancedSearchPanel.isDistrictSelectorVisible(),
                "District selector should be activated");
        softAssert.assertTrue(advancedSearchPanel.isMetroSelectorVisible(),
                "Metro station selector should be activated");
        softAssert.assertTrue(advancedSearchPanel.isAvailableOnlineCheckboxVisible(),
                "Checkbox is online should be activated");
        softAssert.assertTrue(advancedSearchPanel.isCategoriesBlockVisible(),
                "Category checkbox list should be activated");
        softAssert.assertTrue(advancedSearchPanel.isChildAgeBlockVisible(),
                "Age input should be activated");

        softAssert.assertAll();
    }

    @Issue("TUA-510")
    @Description("Verify disability of some elements after selecting center")
    @Test
    public void verifyThatSomeElementsIsDisabledAfterSelectingCenter() {
        advancedSearchPanel.selectCenterFilter();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(advancedSearchPanel.isCitySelectorVisible(),
                "City selector should be displayed");
        softAssert.assertTrue(advancedSearchPanel.isDistrictSelectorVisible(),
                "District selector should be displayed");
        softAssert.assertTrue(advancedSearchPanel.isMetroSelectorVisible(),
                "Metro selector should be displayed");
        softAssert.assertFalse(advancedSearchPanel.isAvailableOnlineCheckboxVisible(),
                "Available online checkbox should not be displayed");
        softAssert.assertFalse(advancedSearchPanel.isCategoriesBlockVisible(),
                "Categories block should not be displayed");
        softAssert.assertFalse(advancedSearchPanel.isChildAgeBlockVisible(),
                "Child age block should not be displayed");

        softAssert.assertAll();
    }
}

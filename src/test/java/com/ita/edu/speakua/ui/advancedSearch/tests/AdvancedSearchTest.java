package com.ita.edu.speakua.ui.advancedSearch.tests;

import com.ita.edu.speakua.ui.runners.AdvancedSearchTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AdvancedSearchTest extends AdvancedSearchTestRunner {

    @Issue("TUA-509")
    @Description("Verify that all parameters are activated with the selected club radio button")
    @Test
    public void verifyItemsAdvancedSearchPanelAreActivated() {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(advancedSearchPanel.isCitySelectorVisible(),
                "City selected list should be activated");
        softAssert.assertTrue(advancedSearchPanel.isDistrictSelectorVisible(),
                "District selected list should be activated");
        softAssert.assertTrue(advancedSearchPanel.isMetroSelectorVisible(),
                "Metro station selected list should be activated");
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
    public void verifySomeElementsIsDisabledAfterSelectingCenter() {
        advancedSearchPanel.selectCenterFilter();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertFalse(advancedSearchPanel.isAvailableOnlineCheckboxVisible(),
                "Available online checkbox is displayed");
        softAssert.assertFalse(advancedSearchPanel.isCategoriesBlockVisible(),
                "Categories block is displayed");
        softAssert.assertFalse(advancedSearchPanel.isChildAgeBlockVisible(),
                "Child age block is displayed");

        softAssert.assertAll();
    }
}

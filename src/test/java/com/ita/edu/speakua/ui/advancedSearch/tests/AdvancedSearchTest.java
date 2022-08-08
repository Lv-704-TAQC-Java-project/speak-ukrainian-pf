package com.ita.edu.speakua.ui.advancedSearch.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.clubs.AdvancedSearchPanelComponent;
import com.ita.edu.speakua.ui.runners.BaseTestRunner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AdvancedSearchTest extends BaseTestRunner {

    @Test
    public void verifyItemsAdvancedSearchPanelAreActivated() {
        AdvancedSearchPanelComponent verify = new HomePage(driver)
                .clickAdvancedSearchButton()
                .getAdvancedSearchPanelComponent();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(verify.cityListIsActivated(), "City selected list should be activated");
        softAssert.assertTrue(verify.districtListIsActivated(), "District selected list should be activated");
        softAssert.assertTrue(verify.metroListIsActivated(), "Metro station selected list should be activated");
        softAssert.assertTrue(verify.checkOnlineIsActivated(), "Checkbox is online should be activated");
        softAssert.assertTrue(verify.categoriesListIsActivated(), "Category checkbox list should be activated");
        softAssert.assertTrue(verify.ageChildInputActivated(), "Age input should be activated");
    }

    @Test
    public void verifySomeElementsIsDisabledAfterSelectingCenter() {
        AdvancedSearchPanelComponent advancedSearchPanel = new HomePage(driver)
                .clickAdvancedSearchButton()
                .getAdvancedSearchPanelComponent()
                .centerRadioButtonClick();

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

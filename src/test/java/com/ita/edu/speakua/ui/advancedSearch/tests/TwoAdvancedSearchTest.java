package com.ita.edu.speakua.ui.advancedSearch.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.clubs.AdvancedSearchPanelComponent;
import com.ita.edu.speakua.ui.runners.BaseTestRunner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TwoAdvancedSearchTest extends BaseTestRunner {

    @Test
    public void verifySomeElementsIsDisabledAfterSelectingCenter(){
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

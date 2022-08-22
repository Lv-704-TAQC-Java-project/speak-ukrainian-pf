package com.ita.edu.speakua.ui.advancedSearch.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.clubs.ClubsPage;
import com.ita.edu.speakua.ui.runners.BaseTestRunner;
import com.ita.edu.speakua.ui.runners.SameWindowTestRunner;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdvancedSearchVisibleTest extends SameWindowTestRunner {
    @Issue("224")
    @Test
    public void CheckAdvancedSearchButton() {
        ClubsPage verify = new HomePage(driver)
                .openAdvancedSearch()
                .getAdvancedSearchPanelComponent()
                .advancedSearchButtonClick();

        Assert.assertFalse(verify.isDisappearsAdvancedSearchPanelComponent());

    }
}

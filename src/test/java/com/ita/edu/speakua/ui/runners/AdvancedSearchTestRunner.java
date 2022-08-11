package com.ita.edu.speakua.ui.runners;

import com.ita.edu.speakua.ui.header.HeaderComponent;
import org.testng.annotations.BeforeClass;

public class AdvancedSearchTestRunner extends SameWindowTestRunner {

    @BeforeClass
    public void openAdvancedSearch() {
        new HeaderComponent(driver)
                .getNavigationComponent()
                .clickClubsBtn()
                .advancedSearchButtonClick();
    }
}

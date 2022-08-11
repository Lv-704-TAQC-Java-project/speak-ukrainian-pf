package com.ita.edu.speakua.ui.runners;

import com.ita.edu.speakua.ui.clubs.AdvancedSearchPanelComponent;
import com.ita.edu.speakua.ui.header.HeaderComponent;
import org.testng.annotations.BeforeClass;

public class AdvancedSearchTestRunner extends SameWindowTestRunner {
    protected AdvancedSearchPanelComponent advancedSearchPanel;

    @BeforeClass
    public void openAdvancedSearch() {
        advancedSearchPanel = new HeaderComponent(driver)
                .getNavigationComponent()
                .clickClubsBtn()
                .advancedSearchButtonClick()
                .getAdvancedSearchPanelComponent();
    }
}
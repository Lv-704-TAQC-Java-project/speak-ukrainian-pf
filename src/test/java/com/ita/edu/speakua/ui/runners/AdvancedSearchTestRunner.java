package com.ita.edu.speakua.ui.runners;

import com.ita.edu.speakua.ui.clubs.AdvancedSearchPanel;
import com.ita.edu.speakua.ui.clubs.ClubsPage;
import com.ita.edu.speakua.ui.header.Header;
import org.testng.annotations.BeforeClass;

public class AdvancedSearchTestRunner extends SameWindowTestRunner {
    protected AdvancedSearchPanel advancedSearchPanel;
    protected ClubsPage clubsPage;

    @BeforeClass
    public void openAdvancedSearch() {
        clubsPage = getHomePage()
                .getNavigationComponent()
                .clickClubsBtn();
        advancedSearchPanel = clubsPage
                .advancedSearchButtonClick()
                .getAdvancedSearchPanelComponent();
    }
}
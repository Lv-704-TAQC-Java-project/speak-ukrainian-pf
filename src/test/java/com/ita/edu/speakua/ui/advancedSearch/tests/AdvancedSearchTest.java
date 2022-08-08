package com.ita.edu.speakua.ui.advancedSearch.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.clubs.AdvancedSearchPanelComponent;
import com.ita.edu.speakua.ui.runners.BaseTestRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdvancedSearchTest extends BaseTestRunner {

    @Test
    public void verifyItemsAdvancedSearchPanelAreActivated(){
        AdvancedSearchPanelComponent verify = new HomePage(driver)
               .clickAdvancedSearchButton()
               .getAdvancedSearchPanelComponent();

        Assert.assertTrue(verify.cityListIsActivated());
        Assert.assertTrue(verify.districtListIsActivated());
        Assert.assertTrue(verify.metroListIsActivated());
        Assert.assertTrue(verify.checkOnlineIsActivated());
        Assert.assertTrue(verify.categoriesListIsActivated());
        Assert.assertTrue(verify.ageChildInputActivated());
    }

}

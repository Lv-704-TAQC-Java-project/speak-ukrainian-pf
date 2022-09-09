package com.ita.edu.speakua.ui.runners;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationMenu.addChallenge.AddChallengePage;
import org.testng.annotations.BeforeMethod;

public class AddChallengeTestRunner extends LoginTestRunner{
    protected AddChallengePage addChallengePage;
    protected int lastChallengeNumber;

    @BeforeMethod
    public void openEditProfile() {
        lastChallengeNumber = new HomePage(driver)
                .openAdminProfileMenu()
                .openAdministrationMenu()
                .openChallengePage()
                .getLastChallengeNumber();
        addChallengePage = new HomePage(driver)
                .openAdminProfileMenu()
                .openAdministrationMenu()
                .openChallengePage()
                .openChallengeAddPage();
    }
}
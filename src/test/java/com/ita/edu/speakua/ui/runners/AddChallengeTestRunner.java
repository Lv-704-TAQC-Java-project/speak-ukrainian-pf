package com.ita.edu.speakua.ui.runners;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addChallenge.AddChallengePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addChallenge.ChallengePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addTask.AddTaskPage;
import org.testng.annotations.BeforeMethod;

public class AddChallengeTestRunner extends LoginTestRunner{
    protected AddChallengePage addChallengePage;
    protected int lastChallengeNumber;

    @BeforeMethod
    public void openEditProfile() {
        lastChallengeNumber = new HomePage(driver)
                .openAdminProfileMenu()
                .openAdministrationModal()
                .openChallengePage()
                .getLastChallengeNumber();
        addChallengePage = new HomePage(driver)
                .openAdminProfileMenu()
                .openAdministrationModal()
                .openChallengePage()
                .openChallengeAddPage();
    }
}
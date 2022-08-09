package com.ita.edu.speakua.ui.runners;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.profilePage.EditProfileComponent;
import org.testng.annotations.*;


public class EditProfileTestRunner extends BaseTestRunnerWithLogIn {
    protected EditProfileComponent editProfileComponent;


    @BeforeClass
    public void openEditProfile() {
        editProfileComponent = new HomePage(driver)
                .openAdminProfileMenu()
                .openUserProfilePage()
                .clickEditProfileButton();
    }


}

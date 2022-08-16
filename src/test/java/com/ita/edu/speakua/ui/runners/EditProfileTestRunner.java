package com.ita.edu.speakua.ui.runners;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.profilePage.EditProfileComponent;
import org.testng.annotations.BeforeClass;

public class EditProfileTestRunner extends LoginTestRunner {
    protected EditProfileComponent editProfileComponent;
    protected String initialPhone;
    protected String initialFirstName;

    @BeforeClass
    public void openEditProfile() {
        editProfileComponent = new HomePage(driver)
                .openAdminProfileMenu()
                .openUserProfilePage()
                .openEditProfilePage();
        initialPhone = editProfileComponent.getPhone();
        initialFirstName = editProfileComponent.getFirstName();
    }
}
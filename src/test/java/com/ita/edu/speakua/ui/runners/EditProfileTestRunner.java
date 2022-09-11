package com.ita.edu.speakua.ui.runners;

import com.ita.edu.speakua.ui.header.profileMenuAdmin.profilePage.EditProfileModal;
import org.testng.annotations.BeforeClass;

public class EditProfileTestRunner extends SignInTestRunner {
    protected EditProfileModal editProfileModal;
    protected String initialPhone;
    protected String initialFirstName;
    protected String initialLastName;

    @BeforeClass
    public void openEditProfile() {
        editProfileModal = homePage
                .openAdminProfileMenu()
                .openUserProfilePage()
                .openEditProfilePage();
        initialPhone = editProfileModal.getPhone();
        initialFirstName = editProfileModal.getFirstName();
        initialLastName = editProfileModal.getLastName();
    }
}
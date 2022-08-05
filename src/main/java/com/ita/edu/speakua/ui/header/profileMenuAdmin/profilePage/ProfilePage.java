package com.ita.edu.speakua.ui.header.profileMenuAdmin.profilePage;

import com.ita.edu.speakua.ui.BaseMethods;
import com.ita.edu.speakua.ui.header.HeaderComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends HeaderComponent {

    @FindBy(xpath = "//div[@class='user-email-data']")
    private WebElement currentUserEmailField;
    @FindBy(xpath = "//button[contains(@class, 'ant-btn-text button')]")
    private WebElement editProfileButton;
    private EditProfileComponent editProfileModalComponent;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public EditProfileComponent clickEditProfileButton() {
        editProfileButton.click();
        return new EditProfileComponent(driver);
    }

}

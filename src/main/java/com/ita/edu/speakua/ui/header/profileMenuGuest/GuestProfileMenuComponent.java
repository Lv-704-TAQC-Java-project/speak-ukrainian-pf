package com.ita.edu.speakua.ui.header.profileMenuGuest;

import com.ita.edu.speakua.ui.BaseMethods;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.profilePage.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class GuestProfileMenuComponent extends BaseMethods {
    @FindBy(xpath = "//div[contains(text(), 'Увійти')]//ancestor::li")
    private WebElement loginButton;

    @FindBy(xpath = "//div[contains(text(), 'Зареєструватися')]//ancestor::li")
    private WebElement registrationButton;

    @FindBy(xpath = "//a[contains(@href, 'user')]")
    private WebElement myProfileButton;

    private LoginModalComponent loginModal;
    private RegistrationModalComponent registrationModal;


    public GuestProfileMenuComponent(WebDriver driver) {
        super(driver);
    }


    public LoginModalComponent getLoginModal() {
        if (loginModal == null) {
            loginModal = new LoginModalComponent(driver);
        }
        return loginModal;
    }

    public RegistrationModalComponent getRegistrationModal() {
        if (registrationModal == null) {
            registrationModal = new RegistrationModalComponent(driver);
        }
        return registrationModal;
    }

    public LoginModalComponent openLoginModal() {
        loginButton.click();
        return getLoginModal();
    }

    public RegistrationModalComponent openRegistrationModal(){
        registrationButton.click();
        return getRegistrationModal();
    }

    public ProfilePage openUserProfilePage() {
        myProfileButton.click();
        return new ProfilePage(driver);
    }
}

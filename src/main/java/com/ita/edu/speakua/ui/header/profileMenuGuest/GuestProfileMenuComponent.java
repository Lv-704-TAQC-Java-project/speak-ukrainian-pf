package com.ita.edu.speakua.ui.header.profileMenuGuest;

import com.ita.edu.speakua.ui.BaseMethods;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.profilePage.ProfilePage;
import io.qameta.allure.Step;
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

    public GuestProfileMenuComponent(WebDriver driver) {
        super(driver);
    }

    public LoginModalComponent getLoginModal() {
        return new LoginModalComponent(driver);
    }

    public RegistrationModalComponent getRegistrationModal() {
        return new RegistrationModalComponent(driver);
    }

    @Step("Open Login Modal")
    public LoginModalComponent openLoginModal() {
        waitVisibility(loginButton);
        actionsClick(loginButton);
        return new LoginModalComponent(driver);
    }

    @Step("Open Registration Modal")
    public RegistrationModalComponent openRegistrationModal() {
        registrationButton.click();
        return getRegistrationModal();
    }

    public ProfilePage openUserProfilePage() {
        myProfileButton.click();
        return new ProfilePage(driver);
    }
}

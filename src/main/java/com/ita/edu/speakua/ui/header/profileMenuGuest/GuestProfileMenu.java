package com.ita.edu.speakua.ui.header.profileMenuGuest;

import com.ita.edu.speakua.ui.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class GuestProfileMenu extends BasePage {
    @FindBy(xpath = "//div[contains(text(), 'Увійти')]//ancestor::li")
    private WebElement loginButton;

    @FindBy(xpath = "//div[contains(text(), 'Зареєструватися')]//ancestor::li")
    private WebElement registrationButton;

    public GuestProfileMenu(WebDriver driver) {
        super(driver);
    }

    public RegistrationModal getRegistrationModal() {
        return new RegistrationModal(driver);
    }

    @Step("Guest profile menu: open Login Modal")
    public LoginModal openLoginModal() {
        wait.visibility(loginButton);
        action.click(loginButton);
        return new LoginModal(driver);
    }

    @Step("Guest profile menu: open Registration Modal")
    public RegistrationModal openRegistrationModal() {
        registrationButton.click();
        return getRegistrationModal();
    }
}

package com.ita.edu.speakua.ui.header.profileMenuGuest;

import com.ita.edu.speakua.ui.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class GuestProfileMenuComponent extends BasePage {
    @FindBy(xpath = "//div[contains(text(), 'Увійти')]//ancestor::li")
    private WebElement loginButton;

    @FindBy(xpath = "//div[contains(text(), 'Зареєструватися')]//ancestor::li")
    private WebElement registrationButton;

    public GuestProfileMenuComponent(WebDriver driver) {
        super(driver);
    }

    public RegistrationModalComponent getRegistrationModal() {
        return new RegistrationModalComponent(driver);
    }

    @Step("Guest profile menu: open Login Modal")
    public LoginModalComponent openLoginModal() {
        wait.visibility(loginButton);
        action.click(loginButton);
        return new LoginModalComponent(driver);
    }

    @Step("Guest profile menu: open Registration Modal")
    public RegistrationModalComponent openRegistrationModal() {
        registrationButton.click();
        return getRegistrationModal();
    }
}

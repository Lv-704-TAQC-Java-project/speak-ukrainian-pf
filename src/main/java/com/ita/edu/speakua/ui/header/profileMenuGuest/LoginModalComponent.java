package com.ita.edu.speakua.ui.header.profileMenuGuest;

import com.ita.edu.speakua.ui.BasePage;
import com.ita.edu.speakua.ui.header.Header;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginModalComponent extends BasePage {

    @FindBy(xpath = "//input[@id='basic_email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='basic_password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[contains(@class, 'login-button')]")
    private WebElement loginButton;

    public LoginModalComponent(WebDriver driver) {
        super(driver);
    }


    @Step("Login modal: enter email {email}")
    public LoginModalComponent fillInEmail(String email) {
        action.setNewValueForInput(emailField, email);
        return this;
    }

    @Step("Login modal: enter password {password}")
    public LoginModalComponent fillInPassword(String password) {
        action.setNewValueForInput(passwordField, password);
        return this;
    }

    @Step("Login modal: submit login form")
    public Header submitLoginForm() {
        wait.visibility(loginButton);
        loginButton.click();
        return new Header(driver);
    }
}

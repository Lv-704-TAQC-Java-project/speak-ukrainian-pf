package com.ita.edu.speakua.ui.header.profileMenuGuest;

import com.ita.edu.speakua.ui.BasePage;
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


    @Step("set login {email}")
    public LoginModalComponent fillInEmail(String email) {
        emailField.click();
        emailField.clear();
        emailField.sendKeys(email);
        return this;
    }

    @Step("set password {password}")
    public LoginModalComponent fillInPassword(String password) {

        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }

    @Step("click submit")
    public LoginModalComponent clickLoginButton() {
        wait.visibility(loginButton);
        loginButton.click();
        return this;
    }
}

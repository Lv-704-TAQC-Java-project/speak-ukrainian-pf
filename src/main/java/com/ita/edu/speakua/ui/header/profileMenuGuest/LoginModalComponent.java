package com.ita.edu.speakua.ui.header.profileMenuGuest;

import com.ita.edu.speakua.ui.BaseMethods;
import com.ita.edu.speakua.ui.HomePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginModalComponent extends BaseMethods {

    @FindBy(xpath = "//div[contains(@class, 'modal-login')]")
    private WebElement loginModal;

    @FindBy(xpath = "//input[@id='basic_email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='basic_password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[contains(@class, 'login-button')]")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@class='login-header']")
    private WebElement loginHeader;

    @FindBy(xpath = "//button[contains(@class, 'modal-close')]")
    private WebElement closeLoginModal;


    public LoginModalComponent(WebDriver driver) {
        super(driver);
    }


    public void closeLoginModal() {
        closeLoginModal.click();
    }

    public LoginModalComponent clickOnLoginHeader() {
        loginHeader.click();
        return this;
    }

    public LoginModalComponent fillInEmail(String email) {
        emailField.click();
        emailField.clear();
        emailField.sendKeys(email);
        return this;
    }

    public LoginModalComponent fillInPassword(String password) {

        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }


    public LoginModalComponent clickLoginButton() {
        waitVisibilityOfWebElement(loginButton);
        loginButton.click();
        return this;
    }

    public LoginModalComponent waitForUserToBeLoggedIn() {
        waitForPageToReload();
        return this;
    }

    public HomePage getHomePage() {
        return new HomePage(driver);
    }
}

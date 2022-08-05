package com.ita.edu.speakua.ui.header.profileMenuGuest;

import com.ita.edu.speakua.ui.BasePage;
import com.ita.edu.speakua.ui.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class LoginModalComponent extends BasePage {
    private WebElement loginModal;
    private WebElement emailField;
    private WebElement emailFieldWrapper;
    private WebElement passwordField;
    private WebElement passwordFieldWrapper;
    private WebElement loginButton;
    private WebElement loginHeader;


    public LoginModalComponent(WebDriver driver) {
        super(driver);
    }

    public WebElement getCloseLoginModalButton() {
        return driver.findElement(By.xpath("//button[contains(@class, 'modal-close')]"));
    }

    public void closeLoginModal() {
        getCloseLoginModalButton().click();
    }

    public WebElement getLoginModal() {
        if (loginModal == null) {
            waitVisibilityOfElement(By.xpath("//div[contains(@class, 'modal-login')]"));
            loginModal = driver.findElement(By.xpath("//div[contains(@class, 'modal-login')]"));
        }
        return loginModal;
    }

    public WebElement getEmailField() {
        if (emailField == null) {
            emailField = driver.findElement(By.xpath("//input[@id='basic_email']"));
        }
        return emailField;
    }

    public WebElement getEmailFieldWrapper(String color) {
        if (emailFieldWrapper == null) {
            waitAttributeOfElementContains(By.xpath("//input[@id='basic_email']/parent::span"), "border-color", color);
            emailFieldWrapper = getEmailField().findElement(By.xpath("./parent::span"));
        }
        return emailFieldWrapper;
    }

    public WebElement getPasswordField() {
        if (passwordField == null) {
            passwordField = driver.findElement(By.xpath("//input[@id='basic_password']"));
        }
        return passwordField;
    }

    public WebElement getPasswordFieldWrapper(String color) {
        if (passwordFieldWrapper == null) {
            waitAttributeOfElementContains(By.xpath("//input[@id='basic_password']/parent::span"), "border-color", color);
            passwordFieldWrapper = getPasswordField().findElement(By.xpath("./parent::span"));
        }
        return passwordFieldWrapper;
    }

    public WebElement getLoginButton() {
        if (loginButton == null) {
            loginButton = driver.findElement(By.xpath("//button[contains(@class, 'login-button')]"));
        }
        return loginButton;
    }

    public WebElement getLoginHeader() {
        if (loginHeader == null) {
            loginHeader = driver.findElement(By.xpath("//div[@class='login-header']"));
        }
        return loginHeader;
    }

    public LoginModalComponent clickOnLoginHeader() {
        Actions action = new Actions(driver);
        action.moveToElement(getLoginHeader()).click();
        return this;
    }

    public LoginModalComponent fillInEmail(String email) {
        getEmailField().sendKeys(Keys.chord(Keys.CONTROL, "a"));
        getEmailField().sendKeys(email);
        return this;
    }

    public LoginModalComponent fillInPassword(String password) {
        getPasswordField().sendKeys(Keys.chord(Keys.CONTROL, "a"));
        getPasswordField().sendKeys(password);
        return this;
    }

    public LoginModalComponent clickLoginButton() {
        getLoginButton().click();
        return this;
    }

    public LoginModalComponent waitForUserToBeLoggedIn() {
        waitForPageToReload();
        return this;
    }

    public HomePage getHomePage() {
        return new HomePage(driver);
    }

    public HomePage getHomePageReload() {
        waitForPageToReload();
        return new HomePage(driver);
    }
}

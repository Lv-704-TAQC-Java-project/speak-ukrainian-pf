package com.ita.edu.speakua.ui.header.profileMenuGuest;

import com.ita.edu.speakua.ui.BasePage;
import com.ita.edu.speakua.ui.header.Header;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInModal extends BasePage {

    @FindBy(xpath = "//input[@id='basic_email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='basic_password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[contains(@class, 'login-button')]")
    private WebElement submitButton;

    public SignInModal(WebDriver driver) {
        super(driver);
    }


    @Step("Sign in modal: enter email '{email}'")
    public SignInModal enterEmail(String email) {
        action.setNewValueForInput(emailField, email);
        return this;
    }

    @Step("Sign in modal: enter password '{password}'")
    public SignInModal enterPassword(String password) {
        action.setNewValueForInput(passwordField, password);
        return this;
    }

    @Step("Sign in modal: submit sign in form")
    public Header submit() {
        wait.visibility(submitButton);
        submitButton.click();
        wait.pageReload();
        return new Header(driver);
    }
}

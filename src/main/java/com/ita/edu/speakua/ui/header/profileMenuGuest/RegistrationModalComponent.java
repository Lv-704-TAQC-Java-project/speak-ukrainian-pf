package com.ita.edu.speakua.ui.header.profileMenuGuest;

import com.ita.edu.speakua.ui.utils.BaseMethods;
import com.ita.edu.speakua.ui.HomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class RegistrationModalComponent extends BaseMethods {
    @FindBy(xpath = "//input[@id='lastName']")
    private WebElement lastNameInputField;

    @FindBy(xpath = "//input[@id='firstName']")
    private WebElement firstNameInputField;

    @FindBy(xpath = "//input[@id='phone']")
    private WebElement phoneInputField;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailInputField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInputField;

    @FindBy(xpath = "//input[@id='confirm']")
    private WebElement confirmPasswordInputField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitRegistrationFormBtn;

    @FindBy(xpath = "//span[@class='ant-modal-close-x']/parent::button")
    private WebElement closeRegistrationFormBtn;

    public RegistrationModalComponent(WebDriver driver) {
        super(driver);
    }


    @Step("Fill in last name field")
    public RegistrationModalComponent fillInLastName(String lastName) {
        waitVisibility(lastNameInputField);
        lastNameInputField.sendKeys(lastName);
        return this;
    }

    public String getLastNameValue() {
        waitVisibility(lastNameInputField);
        return lastNameInputField.getAttribute("value");
    }

    @Step("Fill in first name field")
    public RegistrationModalComponent fillInName(String name) {
        firstNameInputField.sendKeys(name);
        return this;
    }

    public String getFistNameValue() {
        return firstNameInputField.getAttribute("value");
    }

    @Step("Fill in phone number field")
    public RegistrationModalComponent fillInPhone(String phone) {
        phoneInputField.sendKeys(phone);
        return this;
    }

    public String getPhoneValue() {
        return phoneInputField.getAttribute("value");
    }

    @Step("Fill in email field")
    public RegistrationModalComponent fillInEmail(String email) {
        emailInputField.sendKeys(email);
        return this;
    }

    public String getEmailValue() {
        return emailInputField.getAttribute("value");
    }

    @Step("Fill in password field")
    public RegistrationModalComponent fillInPassword(String password) {
        passwordInputField.sendKeys(password);
        return this;
    }

    public String getPasswordValue() {
        return passwordInputField.getAttribute("value");
    }

    @Step("Fill in confirm password field")
    public RegistrationModalComponent fillInConfirmPassword(String confirmPassword) {
        confirmPasswordInputField.sendKeys(confirmPassword);
        return this;
    }

    public String getConfirmPasswordValue() {
        return confirmPasswordInputField.getAttribute("value");
    }

    public RegistrationModalComponent submitRegistrationForm() {
        submitRegistrationFormBtn.click();
        return this;
    }

    public HomePage closeRegistrationModal() {
        closeRegistrationFormBtn.click();
        return new HomePage(driver);
    }

    public String getAllErrorMessages() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<WebElement> divsWithMessages = driver.findElements(By.xpath("//div[@class='ant-form-item-explain-error']"));

        if (divsWithMessages == null) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        for (WebElement div : divsWithMessages) {
            result.append(div.getText()).append(" ");
        }

        return result.toString();
    }

    public HomePage getHomePage() {
        return new HomePage(driver);
    }
}

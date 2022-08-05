package com.ita.edu.speakua.ui.header.profileMenuGuest;

import com.ita.edu.speakua.ui.BaseMethods;
import com.ita.edu.speakua.ui.HomePage;
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


    public RegistrationModalComponent(WebDriver driver) {
        super(driver);
    }


    public RegistrationModalComponent fillInLastName(String lastName) {
        lastNameInputField.sendKeys(lastName);
        return this;
    }

    public RegistrationModalComponent fillInName(String name) {
        firstNameInputField.sendKeys(name);
        return this;
    }

    public RegistrationModalComponent fillInPhone(String phone) {
        phoneInputField.sendKeys(phone);
        return this;
    }

    public RegistrationModalComponent fillInEmail(String email) {
        emailInputField.sendKeys(email);
        return this;
    }

    public RegistrationModalComponent fillInPassword(String password) {
        passwordInputField.sendKeys(password);
        return this;
    }

    public RegistrationModalComponent fillInConfirmPassword(String confirmPassword) {
        confirmPasswordInputField.sendKeys(confirmPassword);
        return this;
    }

    public RegistrationModalComponent submitRegistrationForm() {
        submitRegistrationFormBtn.click();
        return this;
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

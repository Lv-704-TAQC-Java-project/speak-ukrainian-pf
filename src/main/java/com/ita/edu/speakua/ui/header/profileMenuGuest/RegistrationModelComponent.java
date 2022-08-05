package com.ita.edu.speakua.ui.header.profileMenuGuest;

import com.ita.edu.speakua.ui.BasePage;
import com.ita.edu.speakua.ui.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RegistrationModelComponent extends BasePage {
    private WebElement lastNameInputField;

    private WebElement nameInputField;

    private WebElement phoneInputField;

    private WebElement emailInputField;

    private WebElement passwordInputField;

    private WebElement confirmPasswordInputField;

    private WebElement submitRegistrationFormBtn;

    public RegistrationModelComponent(WebDriver driver) {
        super(driver);
    }

    public WebElement getLastNameInputField() {
        if (lastNameInputField == null) {
            lastNameInputField = driver.findElement(By.xpath("//input[@id='lastName']"));
        }
        return lastNameInputField;
    }


    public WebElement getNameInputField() {
        if (nameInputField == null) {
            nameInputField = driver.findElement(By.xpath("//input[@id='firstName']"));
        }
        return nameInputField;
    }


    public WebElement getPhoneInputField() {
        if (phoneInputField == null) {
            phoneInputField = driver.findElement(By.xpath("//input[@id='phone']"));
        }
        return phoneInputField;
    }


    public WebElement getEmailInputField() {
        if (emailInputField == null) {
            emailInputField = driver.findElement(By.xpath("//input[@id='email']"));
        }
        return emailInputField;
    }


    public WebElement getPasswordInputField() {
        if (passwordInputField == null) {
            passwordInputField = driver.findElement(By.xpath("//input[@id='password']"));
        }
        return passwordInputField;
    }


    public WebElement getConfirmPasswordInputField() {
        if (confirmPasswordInputField == null) {
            confirmPasswordInputField = driver.findElement(By.xpath("//input[@id='confirm']"));
        }
        return confirmPasswordInputField;
    }


    public WebElement getSubmitRegistrationFormBtn() {
        if (submitRegistrationFormBtn == null) {
            submitRegistrationFormBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        }
        return submitRegistrationFormBtn;
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


    public RegistrationModelComponent fillInLastName(String lastName) {
        getLastNameInputField().sendKeys(lastName);
        return this;
    }

    public RegistrationModelComponent fillInName(String name) {
        getNameInputField().sendKeys(name);
        return this;
    }

    public RegistrationModelComponent fillInPhone(String phone) {
        getPhoneInputField().sendKeys(phone);
        return this;
    }

    public RegistrationModelComponent fillInEmail(String email) {
        getEmailInputField().sendKeys(email);
        return this;
    }

    public RegistrationModelComponent fillInPassword(String password) {
        getPasswordInputField().sendKeys(password);
        return this;
    }

    public RegistrationModelComponent fillInConfirmPassword(String confirmPassword) {
        getConfirmPasswordInputField().sendKeys(confirmPassword);
        return this;
    }

    public RegistrationModelComponent openSubmitRegistrationButton() {
        getSubmitRegistrationFormBtn().click();
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

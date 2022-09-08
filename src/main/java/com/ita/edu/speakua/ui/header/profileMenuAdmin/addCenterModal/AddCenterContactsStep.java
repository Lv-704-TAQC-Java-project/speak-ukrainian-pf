package com.ita.edu.speakua.ui.header.profileMenuAdmin.addCenterModal;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddCenterContactsStep extends AbstractAddCenterStep {

    @FindBy(xpath = "//input[@id='contacts_contactТелефон']")
    private WebElement enterPhoneNumber;

    @FindBy(xpath = "//span[contains(text(),'Наступний крок')]/ancestor::button")
    private WebElement nextStepButton;

    public AddCenterContactsStep(WebDriver driver) {
        super(driver);
    }

    @Step("Enter phone number")
    public AddCenterContactsStep enterPhoneNumber(String data) {
        enterPhoneNumber.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        enterPhoneNumber.sendKeys(Keys.chord(data));
        return new AddCenterContactsStep(driver);
    }

    @Step("Open next modal 'Describe'")
    public AddCenterDescripriptoinStep openNextStep() {
        nextStepButton.click();
        return new AddCenterDescripriptoinStep(driver);
    }
}

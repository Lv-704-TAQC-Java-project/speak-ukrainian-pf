package com.ita.edu.speakua.ui.header.profileMenuAdmin.addCenterComponent;

import com.ita.edu.speakua.ui.header.profileMenuAdmin.addClubComponent.AddClubDescribeComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddCenterContactComponent extends AbstractAddCenterComponent {

    @FindBy(xpath = "//input[@id='contacts_contactFacebook']")
    private WebElement enterFaceBook;

    @FindBy(xpath = "//input[@id='contacts_contactContact']")
    private WebElement enterBasicContact;

    @FindBy(xpath = "//input[@id='contacts_contactПошта']")
    private WebElement enterEmail;

    @FindBy(xpath = "//input[@id='contacts_contactSkype']")
    private WebElement enterSkype;

    @FindBy(xpath = "//input[@id='contacts_contactWhatsApp']")
    private WebElement enterWhatsApp;

    @FindBy(xpath = "//input[@id='contacts_contactТелефон']")
    private WebElement enterPhoneNumber;

    @FindBy(xpath = "//span[contains(text(),'Назад')]/ancestor::button")
    private WebElement previousStepButton;

    @FindBy(xpath = "//span[contains(text(),'Наступний крок')]/ancestor::button")
    private WebElement nextStepButton;

    public AddCenterContactComponent(WebDriver driver) {
        super(driver);
    }

    @Step("Enter Facebook contact")
    public AddCenterContactComponent enterFaceBook(String data) {
        enterFaceBook.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        enterFaceBook.sendKeys(Keys.chord(data));
        return new AddCenterContactComponent(driver);
    }

    @Step("Enter basic contact")
    public AddCenterContactComponent enterBasicContact(String data) {
        enterBasicContact.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        enterBasicContact.sendKeys(Keys.chord(data));
        return new AddCenterContactComponent(driver);
    }

    @Step("Enter email contact")
    public AddCenterContactComponent enterEmail(String data) {
        enterEmail.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        enterEmail.sendKeys(Keys.chord(data));
        return new AddCenterContactComponent(driver);
    }

    @Step("Enter Skype contact")
    public AddCenterContactComponent enterSkype(String data) {
        enterSkype.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        enterSkype.sendKeys(Keys.chord(data));
        return new AddCenterContactComponent(driver);
    }

    @Step("Enter WhatsApp contact")
    public AddCenterContactComponent enterWhatsApp(String data) {
        enterWhatsApp.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        enterWhatsApp.sendKeys(Keys.chord(data));
        return new AddCenterContactComponent(driver);
    }

    @Step("Enter phone number")
    public AddCenterContactComponent enterPhoneNumber(String data) {
        enterPhoneNumber.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        enterPhoneNumber.sendKeys(Keys.chord(data));
        return new AddCenterContactComponent(driver);
    }

    @Step("Go back to previous modal 'Main info'")
    public AddCenterMainInfoComponent openPreviousStep() {
        previousStepButton.click();
        return new AddCenterMainInfoComponent(driver);
    }

    @Step("Open next modal 'Describe'")
    public AddCenterDescribeComponent openNextStep() {
        nextStepButton.click();
        return new AddCenterDescribeComponent(driver);
    }
}

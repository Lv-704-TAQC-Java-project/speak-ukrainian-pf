package com.ita.edu.speakua.ui.header.profileMenuAdmin.addClubComponent;

import com.ita.edu.speakua.ui.header.profileMenuAdmin.addLocation.AddLocationComponent;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addLocation.Location;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddClubContactComponent extends AbstractAddClubComponent {

    @FindBy(xpath = "//input[@id='basic_contactFacebook']")
    private WebElement faceBook;

    @FindBy(xpath = "//input[@id='basic_contactContact']")
    private WebElement basicContact;

    @FindBy(xpath = "//input[@id='basic_contactПошта']")
    private WebElement email;

    @FindBy(xpath = "//input[@id='basic_contactSkype']")
    private WebElement skype;

    @FindBy(xpath = "//input[@id='basic_contactWhatsApp']")
    private WebElement whatsApp;

    @FindBy(xpath = "//input[@id='basic_contactТелефон']")
    private WebElement phoneNumber;

    @FindBy(xpath = "//span[contains(text(),'Назад')]/ancestor::button")
    private WebElement previousStepButton;

    @FindBy(xpath = "//span[contains(text(),'Наступний крок')]/ancestor::button")
    private WebElement nextStepButton;

    @FindBy(xpath = "//span[@class='add-club-location']")
    private WebElement addLocation;


    public AddClubContactComponent(WebDriver driver) {
        super(driver);
    }

    @Step("Enter Facebook contact")
    public AddClubContactComponent inputFaceBook(String data){
        faceBook.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        faceBook.sendKeys(Keys.chord(data));
        return this;
    }

    @Step("Enter basic contact")
    public AddClubContactComponent inputBasicContact(String data){
        basicContact.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        basicContact.sendKeys(Keys.chord(data));
        return this;
    }

    @Step("Enter email contact")
    public AddClubContactComponent inputEmail(String data){
        email.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        email.sendKeys(Keys.chord(data));
        return this;
    }

    @Step("Enter Skype contact")
    public AddClubContactComponent inputSkype(String data){
        skype.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        skype.sendKeys(Keys.chord(data));
        return this;
    }

    @Step("Enter WhatsApp contact")
    public AddClubContactComponent inputWhatsApp(String data){
        whatsApp.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        whatsApp.sendKeys(Keys.chord(data));
        return this;
    }

    @Step("Enter PhoneNumber {phone}")
    public AddClubContactComponent inputPhoneNumber(String phone){
        phoneNumber.click();
        phoneNumber.clear();
        phoneNumber.sendKeys(phone);
        return this;
    }

    @Step("Add location on club component")
    public AddClubContactComponent addLocation(Location location){
        addLocation.click();
        new AddLocationComponent(driver).addLocation(location);
        return this;
    }

    @Step("Go back to previous modal 'Main info'")
    public AddClubMainInfoComponent openPreviousStep(){
        previousStepButton.click();
        return new AddClubMainInfoComponent(driver);
    }

    @Step("Open next modal 'Describe'")
    public AddClubDescribeComponent openNextStep(){
        nextStepButton.click();
        return new AddClubDescribeComponent(driver);
    }
}

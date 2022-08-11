package com.ita.edu.speakua.ui.header.profileMenuAdmin.addClubComponent;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddClubContactComponent extends AbstractAddClubComponent {

    @FindBy(xpath = "//input[@id='basic_contactFacebook']")
    WebElement inputFaceBook;
    @FindBy(xpath = "//input[@id='basic_contactContact']")
    WebElement inputBasicContact;
    @FindBy(xpath = "//input[@id='basic_contactПошта']")
    WebElement inputEmail;
    @FindBy(xpath = "//input[@id='basic_contactSkype']")
    WebElement inputSkype;
    @FindBy(xpath = "//input[@id='basic_contactWhatsApp']")
    WebElement inputWhatsApp;
    @FindBy(xpath = "//input[@id='basic_contactТелефон']")
    WebElement inputPhoneNumber;
    @FindBy(xpath = "//span[contains(text(),'Назад')]/ancestor::button")
    WebElement previousStep;
    @FindBy(xpath = "//span[contains(text(),'Наступний крок')]/ancestor::button")
    private WebElement nextStepBtn;


    public AddClubContactComponent(WebDriver driver) {
        super(driver);
    }

    public AddClubContactComponent inputFaceBook(String name){
        inputFaceBook.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputFaceBook.sendKeys(Keys.chord(name));
        return new AddClubContactComponent(driver);
    }

    public AddClubContactComponent inputBasicContact(String name){
        inputBasicContact.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputBasicContact.sendKeys(Keys.chord(name));
        return new AddClubContactComponent(driver);
    }

    public AddClubContactComponent inputEmail(String name){
        inputEmail.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputEmail.sendKeys(Keys.chord(name));
        return new AddClubContactComponent(driver);
    }

    public AddClubContactComponent inputSkype(String name){
        inputSkype.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputSkype.sendKeys(Keys.chord(name));
        return new AddClubContactComponent(driver);
    }

    public AddClubContactComponent inputWhatsApp(String name){
        inputWhatsApp.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputWhatsApp.sendKeys(Keys.chord(name));
        return new AddClubContactComponent(driver);
    }

    @Step("set PhoneNumber {phone}")
    public AddClubContactComponent inputPhoneNumber(String phone){
//        inputPhoneNumber.sendKeys(Keys.chord(Keys.CONTROL, "a"));
//        inputPhoneNumber.sendKeys(Keys.chord(phone));
        inputPhoneNumber.click();
        inputPhoneNumber.clear();
        inputPhoneNumber.sendKeys(phone);
        return new AddClubContactComponent(driver);
    }


    public AddClubMainInfoComponent clickPreviousStep(){
        previousStep.click();
        return new AddClubMainInfoComponent(driver);
    }

    @Step("Click 'next step' button")
    public AddClubDescribeComponent clickNextStep(){
        nextStepBtn.click();
        return new AddClubDescribeComponent(driver);
    }


}

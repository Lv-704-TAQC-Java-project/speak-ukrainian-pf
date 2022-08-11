package com.ita.edu.speakua.ui.header.profileMenuAdmin.addCenterComponent;

import com.ita.edu.speakua.ui.header.profileMenuAdmin.addClubComponent.AddClubDescribeComponent;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddCenterContactComponent extends AbstractAddCenterComponent{


    @FindBy(xpath = "//input[@id='contacts_contactFacebook']")
    WebElement inputFaceBook;
    @FindBy(xpath = "//input[@id='contacts_contactContact']")
    WebElement inputBasicContact;
    @FindBy(xpath = "//input[@id='contacts_contactПошта']")
    WebElement inputEmail;
    @FindBy(xpath = "//input[@id='contacts_contactSkype']")
    WebElement inputSkype;
    @FindBy(xpath = "//input[@id='contacts_contactWhatsApp']")
    WebElement inputWhatsApp;
    @FindBy(xpath = "//input[@id='contacts_contactТелефон']")
    WebElement inputPhoneNumber;
    @FindBy(xpath = "//span[contains(text(),'Назад')]/ancestor::button")
    WebElement previousStep;
    @FindBy(xpath = "//span[contains(text(),'Наступний крок')]/ancestor::button")
    private WebElement nextStepBtn;


    public AddCenterContactComponent(WebDriver driver) {
        super(driver);
    }

    public AddCenterContactComponent inputFaceBook(String name){
        inputFaceBook.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputFaceBook.sendKeys(Keys.chord(name));
        return new AddCenterContactComponent(driver);
    }

    public AddCenterContactComponent inputBasicContact(String name){
        inputBasicContact.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputBasicContact.sendKeys(Keys.chord(name));
        return new AddCenterContactComponent(driver);
    }

    public AddCenterContactComponent inputEmail(String name){
        inputEmail.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputEmail.sendKeys(Keys.chord(name));
        return new AddCenterContactComponent(driver);
    }

    public AddCenterContactComponent inputSkype(String name){
        inputSkype.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputSkype.sendKeys(Keys.chord(name));
        return new AddCenterContactComponent(driver);
    }

    public AddCenterContactComponent inputWhatsApp(String name){
        inputWhatsApp.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputWhatsApp.sendKeys(Keys.chord(name));
        return new AddCenterContactComponent(driver);
    }

    public AddCenterContactComponent inputPhoneNumber(String name){
        inputPhoneNumber.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputPhoneNumber.sendKeys(Keys.chord(name));
        return new AddCenterContactComponent(driver);
    }


    public AddCenterMainInfoComponent clickPreviousStep(){
        previousStep.click();
        return new AddCenterMainInfoComponent(driver);
    }

    public AddClubDescribeComponent clickNextStep(){
        nextStepBtn.click();
        return new AddClubDescribeComponent(driver);
    }
}

package com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addChallenge;

import com.ita.edu.speakua.ui.BasePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.taskPage.AddTaskPage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.taskPage.TaskPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddChallengePage extends BasePage {

    @FindBy(xpath = "//input[@id='sortNumber']")
    WebElement inputSortNumber;

    @FindBy(xpath = "//input[@id='name']")
    WebElement inputName;

    @FindBy(xpath = "//input[@id='title']")
    WebElement inputHeading;

    @FindBy(xpath = "//div[@class='ql-editor ql-blank']/p")
    WebElement inputDescribe;

    @FindBy(xpath = "//span[@class='upload-label']")
    WebElement uploadImageBtn;

    @FindBy(xpath = "//input[@id='startDate']")
    WebElement saveBtn;

    @FindBy(xpath = "//span[contains(text(),'Зберегти')]/ancestor::button")
    WebElement saveButton;

    public AddChallengePage(WebDriver driver) {
        super(driver);
    }

    public AddChallengePage inputSortNumber(String name){
        inputSortNumber.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputSortNumber.sendKeys(Keys.chord(name));
        return new AddChallengePage(driver);
    }

    public AddChallengePage inputName(String name){
        inputName.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputName.sendKeys(Keys.chord(name));
        return new AddChallengePage(driver);
    }

    public AddChallengePage inputHeading(String name){
        inputHeading.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputHeading.sendKeys(Keys.chord(name));
        return new AddChallengePage(driver);
    }

    public AddChallengePage inputDescribe(String name){
        inputDescribe.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputDescribe.sendKeys(Keys.chord(name));
        return new AddChallengePage(driver);
    }

    public AddChallengePage openUploadImageBtn(){
        uploadImageBtn.click();
        return new AddChallengePage(driver);
    }

    public ChallengePage clickSaveButton(String name){
        saveButton.click();
        return new ChallengePage(driver);
    }
}

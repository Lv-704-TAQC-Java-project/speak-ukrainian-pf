package com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addTask;

import com.ita.edu.speakua.ui.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddTaskPage extends BasePage {

    @FindBy(xpath = "//input[@id='startDate']")
    WebElement startDate;

    @FindBy(xpath = "//span[contains(text(),'Завантажити')]")
    WebElement uploadImage;

    @FindBy(xpath = "//input[@id='name']")
    WebElement inputName;

    @FindBy(xpath = "//label[contains(text(),'Заголовок')]/../following-sibling::div//p")
    WebElement inputHeading;

    @FindBy(xpath = "//label[contains(text(),'Опис')]/../following-sibling::div//p")
    WebElement inputDescribing;

    @FindBy(xpath = "//input[@id='challengeId']")
    WebElement openChallenge;

    @FindBy(xpath = "//div[@class='rc-virtual-list-holder-inner']")
    WebElement challengeList;

    @FindBy(xpath = "//span[contains(text(),'Зберегти')]/ancestor::button")
    WebElement saveButton;

    @FindBy(xpath = "//span[contains(text(),'Please')]")
    WebElement errorMessageIsEmpty;

    @FindBy(xpath = "//span[contains(text(),'Помилка')]")
    WebElement errorInvalidCharacters;

    @FindBy(xpath = "//span[contains(text(),'minimum of 40')]")
    WebElement errorLessThenFortyCharacters;

    public AddTaskPage(WebDriver driver) {
        super(driver);
    }

    public AddTaskPage inputStartDate(String name){
        startDate.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        startDate.sendKeys(Keys.chord(name));
        return new AddTaskPage(driver);
    }

    public AddTaskPage inputName(String name){
        inputName.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputName.sendKeys(Keys.chord(name));
        return new AddTaskPage(driver);
    }

    public AddTaskPage inputHeading(String name){
        inputHeading.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputHeading.sendKeys(Keys.chord(name));
        return new AddTaskPage(driver);
    }

    public AddTaskPage inputDescribing(String name){
        inputDescribing.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputDescribing.sendKeys(Keys.chord(name));
        return new AddTaskPage(driver);
    }

    public AddTaskPage openChallengeList(){
        openChallenge.click();
        return new AddTaskPage(driver);
    }

    public WebElement getChallengeItem(String name){
        return challengeList.findElement(By.xpath(String.format(".//div[contains(text(),'%s')]",name)));
    }

    public AddTaskPage chooseChallenge(String name){
        getChallengeItem(name).click();
        return new AddTaskPage(driver);
    }

    public TaskPage clickSaveButton(String name){
        saveButton.click();
        return new TaskPage(driver);
    }

    public boolean errorMessageIsEmptyIsVisible(){
        return errorMessageIsEmpty.isDisplayed();
    }

    public boolean errorMessageInvalidCharactersIsVisible(){
        return errorInvalidCharacters.isDisplayed();
    }

    public boolean errorMessageLessThenFortyCharactersIsVisible(){
        return errorLessThenFortyCharacters.isDisplayed();
    }

    public boolean errorMessageMoreThenThreeThousandCharactersIsVisible(){
        return errorLessThenFortyCharacters.isDisplayed();
    }

}

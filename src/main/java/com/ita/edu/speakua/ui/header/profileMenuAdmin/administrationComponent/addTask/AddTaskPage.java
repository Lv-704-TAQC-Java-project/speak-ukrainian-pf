package com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addTask;

import com.ita.edu.speakua.ui.header.HeaderComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AddTaskPage extends HeaderComponent {

    @FindBy(xpath = "//input[@id='startDate']")
    WebElement startDate;

    @FindBy(xpath = "//input[@type='file']")
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

    @FindBy(xpath = "//div[contains(@class, 'warning')]//span[text()]")
    WebElement errorMessage;

    @FindBy(xpath = "//div[contains(@class, 'warning')]//span[text()]")
    List<WebElement> errorMessages;

    @FindBy(xpath = "//span[contains(text(),'Please')]")
    WebElement errorMessageIsEmpty;

    @FindBy(xpath = "//span[contains(text(),'Помилка')]")
    WebElement errorInvalidCharacters;

    @FindBy(xpath = "//span[contains(text(),'minimum of 40')]")
    WebElement errorLessThenFortyCharacters;

    @FindBy(xpath = "//span[@class='ant-upload-list-item-actions']")
    List<WebElement> imageElements;

    @FindBy(xpath = "//div[@name='id']//span[text()]")
    WebElement challengeInput;

    public AddTaskPage(WebDriver driver) {
        super(driver);
    }

    public AddTaskPage inputStartDate(String date) {
        setNewValueForInput(startDate, date);
        return this;
    }

    public AddTaskPage inputImage(String imagePath) {
        uploadImage.sendKeys(imagePath);
        return this;
    }
    public AddTaskPage inputImageSecondPhoto(){
        uploadImage.sendKeys("C:\\Users\\User\\IdeaProjects\\speak-ukrainian-pf\\smile-png.png");
        return this;
    }

    public AddTaskPage inputName(String name) {
        setNewValueForInput(inputName, name);
        return this;
    }

    public AddTaskPage inputHeading(String value) {
        setNewValueForInput(inputHeading, value);
        return this;
    }

    public AddTaskPage inputDescribing(String value) {
        setNewValueForInput(inputDescribing, value);
        return this;
    }

    public AddTaskPage openChallengeList() {
        actionsClickOnElement(openChallenge);
        return this;
    }

    public WebElement getChallengeItem(String name) {
        waitVisibilityOfWebElement(challengeList);
        return challengeList.findElement(By.xpath(String.format(".//div[contains(text(),'%s')]", name)));
    }

    public AddTaskPage chooseChallenge(String name) {
        openChallengeList()
                .getChallengeItem(name).click();
        return this;
    }

    public boolean allFieldsAreEmpty() {
        waitVisibilityOfWebElement(startDate);
        return startDateIsEmpty() &&
                imageIsEmpty() &&
                nameIsEmpty() &&
                headingIsEmpty() &&
                describeIsEmpty() &&
                challengeIsEmpty();
    }

    public boolean startDateIsEmpty() {
        return startDate.getAttribute("value").isEmpty();
    }

    public boolean nameIsEmpty() {
        return inputName.getAttribute("value").isEmpty();
    }

    public boolean imageIsEmpty() {
        return imageElements.size() == 0;
    }

    public boolean headingIsEmpty() {
        return inputHeading.getText().isEmpty();
    }

    public boolean describeIsEmpty() {
        return inputDescribing.getText().isEmpty();
    }

    public boolean challengeIsEmpty() {
        return challengeInput.getText().equals("Оберіть челендж");
    }

    public String getErrorMessageText() {
        waitVisibilityOfWebElement(errorMessage);
        return errorMessage.getText();
    }

    public boolean errorMessageIsEmptyIsVisible() {
        waitVisibilityOfElement(By.xpath("//span[contains(text(),'Please')]"));
        return errorMessageIsEmpty.isDisplayed();
    }

    public boolean errorMessageInvalidCharactersIsVisible() {
        waitVisibilityOfElement(By.xpath("//span[contains(text(),'Помилка')]"));
        return errorInvalidCharacters.isDisplayed();
    }

    public boolean errorMessageLessThenFortyCharactersIsVisible() {
        waitVisibilityOfElement(By.xpath("//span[contains(text(),'minimum of 40')]"));
        return errorLessThenFortyCharacters.isDisplayed();
    }

    public boolean errorMessageMoreThenThreeThousandCharactersIsVisible() {
        waitVisibilityOfElement(By.xpath("//span[contains(text(),'minimum of 40')]"));
        return errorLessThenFortyCharacters.isDisplayed();
    }

    public boolean errorMessageLessThenFiveCharactersIsVisible(){
        waitVisibilityOfElement(By.xpath("//span[contains(text(),'minimum of 5')]"));
        return errorLessThenFortyCharacters.isDisplayed();
    }

    public boolean errorMessageMoreThenOneHundredCharactersIsVisible(){
        waitVisibilityOfElement(By.xpath("//span[contains(text(),'minimum of 100')]"));
        return errorLessThenFortyCharacters.isDisplayed();
    }

    public boolean errorMessageIsDisplayed() {
        try {
            return errorMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public TaskPage clickSaveButton() {
        saveButton.click();
        return new TaskPage(driver);
    }

    public AddTaskPage tryClickSaveButton() {
        saveButton.click();
        return this;
    }
}

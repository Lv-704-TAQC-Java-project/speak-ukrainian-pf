package com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addTask;

import com.ita.edu.speakua.ui.header.HeaderComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AddTaskPage extends HeaderComponent {

    @FindBy(xpath = "//input[@id='startDate']")
    private WebElement startDate;

    @FindBy(xpath = "//input[@type='file']")
    private WebElement uploadImage;

    @FindBy(xpath = "//input[@id='name']")
    private WebElement taskName;

    @FindBy(xpath = "//label[contains(text(),'Заголовок')]/../following-sibling::div//p")
    private WebElement taskTitle;

    @FindBy(xpath = "//label[contains(text(),'Опис')]/../following-sibling::div//p")
    private WebElement enterDescription;

    @FindBy(xpath = "//input[@id='challengeId']")
    private WebElement challenge;

    @FindBy(xpath = "//div[@class='rc-virtual-list-holder-inner']")
    private WebElement challengeList;

    @FindBy(xpath = "//span[contains(text(),'Зберегти')]/ancestor::button")
    private WebElement saveButton;

    @FindBy(xpath = "//div[contains(@class, 'warning')]//span[text()]")
    private WebElement errorMessage;

    @FindBy(xpath = "//div[contains(@class, 'warning')]//span[text()]")
    private List<WebElement> errorMessages;

    @FindBy(xpath = "//span[contains(text(),'Please')]")
    private WebElement errorMessageIsEmpty;

    @FindBy(xpath = "//span[contains(text(),'Помилка')]")
    private WebElement errorInvalidCharacters;

    @FindBy(xpath = "//span[contains(text(),'minimum of 40')]")
    private WebElement errorLessThenFortyCharacters;

    @FindBy(xpath = "//span[contains(text(),'startDate не може бути відсутнім, має бути задано')]")
    private WebElement errorEmptyDate;

    @FindBy(xpath = "//span[contains(text(),'startDate не може бути відсутнім, має бути задано')]")
    private WebElement errorPastDate;

    @FindBy(xpath = "//span[@class='ant-upload-list-item-actions']")
    private List<WebElement> imageElements;

    @FindBy(xpath = "//div[@name='id']//span[text()]")
    private WebElement challengeInput;

    public AddTaskPage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter start date of challenge {date}")
    public AddTaskPage enterStartDate(String date) {
        setNewValueForInput(startDate, date);
        return this;
    }

    @Step("Upload image")
    public AddTaskPage uploadImage(String imagePath) {
        uploadImage.sendKeys(imagePath);
        return this;
    }

    @Step("Enter name of challenge {name}")
    public AddTaskPage enterName(String name) {
        setNewValueForInput(taskName, name);
        return this;
    }

    @Step("Set heading {value}")
    public AddTaskPage enterTitle(String value) {
        setNewValueForInput(taskTitle, value);
        return this;
    }

    @Step("Enter description of challenge {value}")
    public AddTaskPage enterDescription(String value) {
        setNewValueForInput(enterDescription, value);
        return this;
    }

    private AddTaskPage openChallengeList() {
        actionsClickOnElement(challenge);
        return this;
    }

    private WebElement getChallengeItem(String name) {
        waitVisibilityOfWebElement(challengeList);
        return challengeList.findElement(By.xpath(String.format(".//div[contains(text(),'%s')]", name)));
    }

    @Step("Select challenge")
    public AddTaskPage selectChallenge(String name) {
        openChallengeList()
                .getChallengeItem(name)
                .click();
        return this;
    }

    @Step("Check are allFields empty")
    public boolean areFieldsEmpty() {
        waitVisibilityOfWebElement(startDate);
        return startDateIsEmpty() &&
                imageIsEmpty() &&
                nameIsEmpty() &&
                headingIsEmpty() &&
                describeIsEmpty() &&
                challengeIsEmpty();
    }

    private boolean startDateIsEmpty() {
        return startDate.getAttribute("value").isEmpty();
    }

    private boolean nameIsEmpty() {
        return taskName.getAttribute("value").isEmpty();
    }

    private boolean imageIsEmpty() {
        return imageElements.size() == 0;
    }

    private boolean headingIsEmpty() {
        return taskTitle.getText().isEmpty();
    }

    private boolean describeIsEmpty() {
        return enterDescription.getText().isEmpty();
    }

    private boolean challengeIsEmpty() {
        return challengeInput.getText().equals("Оберіть челендж");
    }

    @Step("Get error message")
    public String getErrorMessageText() {
        waitVisibilityOfWebElement(errorMessage);
        return errorMessage.getText();
    }

    @Step("Get error messages from invalid challenges")
    public boolean errorMessageIsEmptyIsVisible() {
        waitVisibilityOfElement(By.xpath("//span[contains(text(),'Please')]"));
        return errorMessageIsEmpty.isDisplayed();
    }

    public boolean errorMessageInvalidCharactersIsVisible() {
        waitVisibilityOfElement(By.xpath("//span[contains(text(),'Помилка')]"));
        return errorInvalidCharacters.isDisplayed();
    }

    public boolean errorMessageEmptyDate() {
        waitVisibilityOfWebElement(errorEmptyDate);
        return errorEmptyDate.isDisplayed();
    }

    public boolean errorMessagePastDate() {
        waitVisibilityOfWebElement(errorPastDate);
        return errorPastDate.isDisplayed();
    }

    public boolean errorMessageIsDisplayed() {
        try {
            return errorMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Click on 'save' button")
    public AddTaskPage save() {
        saveButton.click();
        return this;
    }
}

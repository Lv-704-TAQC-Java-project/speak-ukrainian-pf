package com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addTask;

import com.ita.edu.speakua.ui.header.HeaderComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
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

    @FindBy(xpath = "//div[@class='ant-message']//div[contains(@class, 'success')]")
    private WebElement successMessage;

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

    public AddTaskPage inputImageSecondPhoto() {
        uploadImage.sendKeys("C:\\Users\\User\\IdeaProjects\\speak-ukrainian-pf\\smile-png.png");
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

    @Step("Select challenge")
    public AddTaskPage selectChallenge(String name) {
        Actions actions = new Actions(driver);
        actions.moveToElement(challenge).click().perform();
        WebElement challenge = safeFind(String.format("//div[contains(@class,'option-content') and contains(text(), '%s')]", name));

        while (challenge == null) {
            actions.scrollToElement(driver.findElement(By.xpath("(//div[contains(@class,'option-content')])[10]"))).perform();
            challenge = safeFind(String.format("//div[contains(@class,'option-content') and contains(text(), '%s')]", name));

        }
        actions.moveToElement(challenge).click().perform();
        return this;
    }

    @Step("Check are allFields empty")
    public boolean areFieldsEmpty() {
        waitVisibility(startDate);
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
        try {
            waitVisibility(errorMessage);
            return errorMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }

    @Step("Get success message")
    public String getSuccessMessage() {
        waitVisibility(successMessage);
        return successMessage.getText();
    }

    @Step("Get error messages from invalid challenges")
    public boolean errorMessageIsEmptyIsVisible() {
        waitVisibility(By.xpath("//span[contains(text(),'Please')]"));
        return errorMessageIsEmpty.isDisplayed();
    }

    public boolean errorMessageInvalidCharactersIsVisible() {
        waitVisibility(By.xpath("//span[contains(text(),'Помилка')]"));
        return errorInvalidCharacters.isDisplayed();
    }

    public boolean errorMessageEmptyDate() {
        waitVisibility(errorEmptyDate);
        return errorEmptyDate.isDisplayed();
    }

    public boolean errorMessagePastDate() {
        waitVisibility(errorPastDate);
        return errorPastDate.isDisplayed();
    }

    public boolean errorMessageLessThenFiveCharactersIsVisible() {
        waitVisibility(By.xpath("//span[contains(text(),'minimum of 5')]"));
        return errorLessThenFortyCharacters.isDisplayed();
    }

    public boolean errorMessageMoreThenOneHundredCharactersIsVisible() {
        waitVisibility(By.xpath("//span[contains(text(),'minimum of 100')]"));
        return errorLessThenFortyCharacters.isDisplayed();
    }

    public boolean errorMessageIsDisplayed() {
        try {
            return errorMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Click on 'save' button")
    public TaskPage save() {
        saveButton.click();
        return new TaskPage(driver);
    }
}

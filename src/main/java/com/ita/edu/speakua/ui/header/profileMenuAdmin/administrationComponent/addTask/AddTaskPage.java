package com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addTask;

import com.ita.edu.speakua.ui.header.Header;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AddTaskPage extends Header {

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
    private WebElement challengeDropdown;

    @FindBy(xpath = "//span[contains(text(),'Зберегти')]/ancestor::button")
    private WebElement saveButton;

    @FindBy(xpath = "//div[contains(@class, 'warning')]//span[text()]")
    private WebElement errorMessage;

    @FindBy(xpath = "//div[@class='ant-message']//div[contains(@class, 'success')]")
    private WebElement successMessage;

    @FindBy(xpath = "//span[contains(text(),'Please')]")
    private WebElement errorMessageIsEmpty;

    @FindBy(xpath = "//span[@class='ant-upload-list-item-actions']")
    private List<WebElement> imageElements;

    @FindBy(xpath = "//div[@name='id']//span[text()]")
    private WebElement challengeInput;

    public AddTaskPage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter task start date: {date}")
    public AddTaskPage enterStartDate(String date) {
        setNewValueForInput(startDate, date);
        return this;
    }

    @Step("Upload task image: {imagePath}")
    public AddTaskPage uploadImage(String imagePath) {
        uploadImage.sendKeys(imagePath);
        return this;
    }

    @Step("Enter task name: {name}")
    public AddTaskPage enterName(String name) {
        setNewValueForInput(taskName, name);
        return this;
    }

    @Step("Enter task title: {value}")
    public AddTaskPage enterTitle(String value) {
        setNewValueForInput(taskTitle, value);
        return this;
    }

    @Step("Enter task description: {value}")
    public AddTaskPage enterDescription(String value) {
        setNewValueForInput(enterDescription, value);
        return this;
    }

    @Step("Select challenge: {name}")
    public AddTaskPage selectChallenge(String name) {
        String challengeXpath = String.format("(//div[contains(@class,'option-content') and contains(text(), '%s')])[1]", name);
        String lastChallengeXpath = "(//div[contains(@class,'option-content')])[last()]";

        actionsClick(challengeDropdown);
        WebElement challenge = safeFind(challengeXpath);

        Actions actions = new Actions(driver);
        while (challenge == null) {
            actions.scrollToElement(driver.findElement(By.xpath(lastChallengeXpath))).perform();
            challenge = safeFind(challengeXpath);
        }
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", challenge);
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

    @Step("Click on 'save' button")
    public TaskPage save() {
        saveButton.click();
        return new TaskPage(driver);
    }
}

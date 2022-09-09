package com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationMenu.addTask;

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

    @Step("Add task page: enter task start date: {date}")
    public AddTaskPage enterStartDate(String date) {
        action.setNewValueForInput(startDate, date);
        return this;
    }

    @Step("Add task page: upload image {imagePath}")
    public AddTaskPage uploadImage(String imagePath) {
        uploadImage.sendKeys(imagePath);
        return this;
    }

    @Step("Add task page: enter task name: {name}")
    public AddTaskPage enterName(String name) {
        action.setNewValueForInput(taskName, name);
        return this;
    }

    @Step("Add task page: enter task title {title}")
    public AddTaskPage enterTitle(String title) {
        action.setNewValueForInput(taskTitle, title);
        return this;
    }

    @Step("Add task page: enter task description {description}")
    public AddTaskPage enterDescription(String description) {
        action.setNewValueForInput(enterDescription, description);
        return this;
    }

    @Step("Add task page: select challenge {name}")
    public AddTaskPage selectChallenge(String name) {
        String challengeXpath = String.format("(//div[contains(@class,'option-content') and contains(text(), '%s')])[1]", name);
        String lastChallengeXpath = "(//div[contains(@class,'option-content')])[last()]";

        action.click(challengeDropdown);
        WebElement challenge = action.safeFind(challengeXpath);

        Actions actions = new Actions(driver);
        while (challenge == null) {
            actions.scrollToElement(driver.findElement(By.xpath(lastChallengeXpath))).perform();
            challenge = action.safeFind(challengeXpath);
        }
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", challenge);
        return this;
    }

    public boolean areFieldsEmpty() {
        wait.visibility(startDate);
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

    public String getErrorMessageText() {
        try {
            wait.visibility(errorMessage);
            return errorMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getSuccessMessage() {
        wait.visibility(successMessage);
        return successMessage.getText();
    }

    public boolean errorMessageIsEmptyIsVisible() {
        wait.visibility(By.xpath("//span[contains(text(),'Please')]"));
        return errorMessageIsEmpty.isDisplayed();
    }

    @Step("Add task page: save task")
    public TaskPage save() {
        saveButton.click();
        return new TaskPage(driver);
    }
}

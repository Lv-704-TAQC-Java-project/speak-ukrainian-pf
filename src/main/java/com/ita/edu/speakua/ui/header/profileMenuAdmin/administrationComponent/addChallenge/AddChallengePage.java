package com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addChallenge;

import com.ita.edu.speakua.ui.header.Header;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AddChallengePage extends Header {

    @FindBy(xpath = "//input[@id='sortNumber']")
    private WebElement sortNumber;

    @FindBy(xpath = "//input[@id='name']")
    private WebElement challengeName;

    @FindBy(xpath = "//input[@id='title']")
    private WebElement titleText;

    @FindBy(xpath = "//div[@class='ql-editor ql-blank']/p")
    private WebElement describe;

    @FindBy(xpath = "//span[@class='upload-label']")
    private WebElement uploadImageButton;

    @FindBy(xpath = "//input[@id='startDate']")
    private WebElement saveBtn;

    @FindBy(xpath = "//span[contains(text(),'Зберегти')]/ancestor::button")
    private WebElement saveButton;

    @FindBy(xpath = "//input[@type='file']")
    private WebElement uploadImageInput;

    @FindBy(xpath = "//span[@class='ant-upload-list-item-actions']")
    private List<WebElement> imageElements;

    @FindBy(xpath = "//div[@class='ant-message']//span[contains(text(), 'успішно')]")
    private WebElement successeMessage;

    public AddChallengePage(WebDriver driver) {
        super(driver);
    }

    @Step("Input sort number")
    public AddChallengePage enterSortNumber(int number) {
        sortNumber.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        sortNumber.sendKeys(Keys.chord(String.valueOf(number)));
        return this;
    }

    @Step("Input challenge name")
    public AddChallengePage enterName(String name) {
        challengeName.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        challengeName.sendKeys(Keys.chord(name));
        return this;
    }

    @Step("Input title text")
    public AddChallengePage enterTitle(String title) {
        titleText.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        titleText.sendKeys(Keys.chord(title));
        return this;
    }

    @Step("Enter a challenge description")
    public AddChallengePage enterDescription(String text) {
        describe.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        describe.sendKeys(Keys.chord(text));
        return this;
    }

    @Step("Upload image")
    public AddChallengePage uploadImage(String path) {
        uploadImageInput.sendKeys(path);
        return this;
    }

    @Step("Save challenge")
    public ChallengePage save() {
        saveButton.click();
        return new ChallengePage(driver);
    }

    @Step("Get message")
    public String getMessageText() {
        waitVisibilityOfWebElement(successeMessage);
        return successeMessage.getText();
    }

    public boolean areFieldsEmpty() {
        return sortNumberIsEmpty() &&
                imageIsEmpty() &&
                nameIsEmpty() &&
                headingIsEmpty() &&
                describeIsEmpty();
    }

    private boolean sortNumberIsEmpty() {
        return sortNumber.getAttribute("value").isEmpty();
    }

    private boolean nameIsEmpty() {
        return challengeName.getAttribute("value").isEmpty();
    }

    private boolean imageIsEmpty() {return imageElements.size() == 0;}

    private boolean headingIsEmpty() {
        return titleText.getText().isEmpty();
    }

    private boolean describeIsEmpty() {
        return describe.getText().isEmpty();
    }
}
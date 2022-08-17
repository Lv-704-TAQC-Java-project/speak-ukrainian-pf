package com.ita.edu.speakua.ui.header.profileMenuAdmin.addClubComponent;

import com.ita.edu.speakua.ui.header.profileMenuAdmin.profilePage.ProfilePage;
import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AddClubDescribeComponent extends AbstractAddClubComponent {

    @FindBy(xpath = "//textarea[@id='basic_description']")
    private WebElement describeArea;

    @FindBy(xpath = "//input[@id='basic_urlLogo']")
    private WebElement logoInput;

    @FindBy(xpath = "//input[@id='basic_urlBackground']")
    private WebElement backgroundInput;

    @FindBy(xpath = "(//span[@role='button']/input)[3]")
    private WebElement gallery;

    @FindBy(xpath = "//span[contains(text(),'Назад')]/ancestor::button")
    private WebElement previousStepButton;

    @FindBy(xpath = "//span[contains(text(),'Завершити')]/ancestor::button")
    private WebElement finishButton;

    @FindBy(xpath = "//div[@class='ant-modal-body']")
    private WebElement addGroupModal;

    @FindBy(xpath = "//textarea[contains(@class,'success')]")
    private WebElement successArea;

    @FindBy(xpath = "//textarea[@id='basic_description']/ancestor::div[contains(@class, 'item-row')]//div[contains(@class, 'explain-error')]")
    private List<WebElement> errorMessagesForDescriptionField;

    public AddClubDescribeComponent(WebDriver driver) {
        super(driver);
    }

    @Step("Set description {text}")
    public AddClubDescribeComponent inputDescription(String text) {
        setNewValueForInput(describeArea,text);
        return new AddClubDescribeComponent(driver);
    }

    @Step("Clear description")
    public AddClubDescribeComponent clearDescription() {
        clearInput(describeArea);
        return new AddClubDescribeComponent(driver);
    }

    @Step("Go back to previous modal 'Contact'")
    public AddClubContactComponent openPreviousStep() {
        previousStepButton.click();
        return new AddClubContactComponent(driver);
    }

    @Step("Add club logo")
    public AddClubDescribeComponent addLogo(String imagePath){
        logoInput.sendKeys(imagePath);
        return this;
    }

    @Step("Add club background")
    public AddClubDescribeComponent addBackground(String imagePath){
        backgroundInput.sendKeys(imagePath);
        return this;
    }

    @Step("Add club gallery")
    public AddClubDescribeComponent addGallery(String imagePath){
        gallery.sendKeys(imagePath);
        return this;
    }

    @Step("Add club")
    public ProfilePage addClub() {
        finishButton.click();
        waitInvisibility(addGroupModal);
        return new ProfilePage(driver);
    }

    public List<String> getErrorMessageDescriptionField() {
        List<String> errorMessages = new ArrayList<>();
        waitVisibility(errorMessagesForDescriptionField);
        if (errorMessagesForDescriptionField != null & errorMessagesForDescriptionField.size() > 0) {
            errorMessagesForDescriptionField.stream().forEach((c) -> errorMessages.add(c.getText()));
        }
        return errorMessages;
    }

    @Step("Check is 'finish' button enable and 'success' area displayed")
    public boolean isButtonEnable() {
        return successArea.isDisplayed() && finishButton.isEnabled();
    }

    @Step("Check description field has errors")
    public boolean areDescriptionErrorsShown() {
        try {
            waitInvisibility(errorMessagesForDescriptionField, 1);
        } catch (TimeoutException ignore) {
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        return errorMessagesForDescriptionField.size() > 0;
    }

    @Step("Check description field error message contains {errorMsg}")
    public boolean descriptionErrorsContainMessage(String errorMsg) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        if (errorMessagesForDescriptionField != null && errorMessagesForDescriptionField.size() > 0) {
            for (WebElement error : errorMessagesForDescriptionField) {
                waitVisibility(error);
                if (error.getText().equals(errorMsg)) {
                    return true;
                }
            }
        }
        return false;
    }
}

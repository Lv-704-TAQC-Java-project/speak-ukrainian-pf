package com.ita.edu.speakua.ui.header.profileMenuAdmin.addClubComponent;

import com.ita.edu.speakua.ui.HomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AddClubDescribeComponent extends AbstractAddClubComponent {

    @FindBy(xpath = "//textarea[@id='basic_description']")
    WebElement describeArea;

    @FindBy(xpath = "//span[contains(text(),'Назад')]/ancestor::button")
    WebElement previousStep;

    @FindBy(xpath = "//span[contains(text(),'Завершити')]/ancestor::button")
    WebElement finishButton;

    @FindBy(xpath = "//div[contains(@class, 'explain-error')]")
    private List<WebElement> errorMessagesForDescriptionField;

    public AddClubDescribeComponent(WebDriver driver) {
        super(driver);
    }

    @Step("Set description {text}")
    public AddClubDescribeComponent inputDescription(String text) {
        setNewValueForInput(describeArea, text);
        return new AddClubDescribeComponent(driver);
    }

    public AddClubContactComponent clickPreviousStep() {
        previousStep.click();
        return new AddClubContactComponent(driver);
    }

    public HomePage clickFinishStep() {
        finishButton.click();
        return new HomePage(driver);
    }

    public List<String> getErrorMessageDescriptionField() {
        List<String> errorMessages = new ArrayList<>();
        waitVisibilityOfWebElements(errorMessagesForDescriptionField);
        if (errorMessagesForDescriptionField != null & errorMessagesForDescriptionField.size() > 0) {
            errorMessagesForDescriptionField.stream().forEach((c) -> errorMessages.add(c.getText()));
        }
        return errorMessages;
    }

    @Step("Check is 'finish' button enable")
    public boolean isFinishButtonEnable() {
        return finishButton.isEnabled();
    }

    public AddClubDescribeComponent clearDescribeField() {
        describeArea.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        return this;
    }

    public boolean errorMessageForDescriptionFieldContainsText(String errorMsg) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        if (errorMessagesForDescriptionField != null && errorMessagesForDescriptionField.size() > 0) {
            for (WebElement error : errorMessagesForDescriptionField) {
                waitVisibilityOfWebElement(error);
                if (error.getText().equals(errorMsg)) {
                    return true;
                }
            }
        }
        return false;
    }
}

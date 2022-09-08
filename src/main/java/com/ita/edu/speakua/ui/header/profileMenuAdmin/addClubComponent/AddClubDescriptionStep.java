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
import java.util.stream.Collectors;

public class AddClubDescriptionStep extends AbstractAddClubStep {

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

    @FindBy(xpath = "//textarea[contains(@class,'success')]")
    private WebElement successArea;

    @FindBy(xpath = "//textarea[@id='basic_description']/ancestor::div[contains(@class, 'item-row')]//div[contains(@class, 'explain-error')]")
    private List<WebElement> descriptionFieldErrors;

    @FindBy(xpath = "//div[@class='ant-modal-body']")
    private WebElement addGroupModal;

    public AddClubDescriptionStep(WebDriver driver) {
        super(driver);
    }

    @Step("Add club 'description' step: enter description {text}")
    public AddClubDescriptionStep enterDescription(String text) {
        action.setNewValueForInput(describeArea, text);
        return new AddClubDescriptionStep(driver);
    }


    @Step("Add club 'description' step: add club logo")
    public AddClubDescriptionStep addLogo(String imagePath) {
        logoInput.sendKeys(imagePath);
        return this;
    }

    @Step("Add club 'description' step: add club background")
    public AddClubDescriptionStep addBackground(String imagePath) {
        backgroundInput.sendKeys(imagePath);
        return this;
    }

    @Step("Add club 'description' step: aAdd club gallery")
    public AddClubDescriptionStep addGallery(String imagePath) {
        gallery.sendKeys(imagePath);
        return this;
    }

    @Step("Add club 'description' step: submit add club form")
    public ProfilePage addClub() {
        finishButton.click();
        wait.invisibility(addGroupModal);
        return new ProfilePage(driver);
    }

    public List<String> getErrorMessageDescriptionField() {
        List<String> errorMessages = new ArrayList<>();
        wait.visibility(descriptionFieldErrors);
        if (descriptionFieldErrors != null & descriptionFieldErrors.size() > 0) {
            descriptionFieldErrors.stream().forEach((c) -> errorMessages.add(c.getText()));
        }
        return errorMessages;
    }

    @Step("Add club 'description' step: check is 'finish' button enable and 'success' area displayed")
    public boolean isButtonEnable() {
        return successArea.isDisplayed() && finishButton.isEnabled();
    }

    @Step("Add club 'description' step: check description field has errors")
    public boolean areDescriptionErrorsShown() {
        try {
            wait.invisibility(descriptionFieldErrors, 1);
        } catch (TimeoutException ignore) {
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        return descriptionFieldErrors.size() > 0;
    }

    @Step("Add club 'description' step: check description field error message contains {errorMsg}")
    public boolean descriptionErrorsContainMessage(String errorMsg) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        if (descriptionFieldErrors != null && descriptionFieldErrors.size() > 0) {
            for (WebElement error : descriptionFieldErrors) {
                wait.visibility(error);
                if (error.getText().equals(errorMsg)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<String> getDescriptionErrors() {
        try {
            wait.visibility(descriptionFieldErrors, 1);
            if (descriptionFieldErrors.size() > 0) {
                wait.staleness(descriptionFieldErrors.get(0), 1);
            }
        } catch (TimeoutException ignore) {
        }
        return descriptionFieldErrors
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
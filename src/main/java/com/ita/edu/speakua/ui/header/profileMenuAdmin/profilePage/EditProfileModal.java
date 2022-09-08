package com.ita.edu.speakua.ui.header.profileMenuAdmin.profilePage;

import com.ita.edu.speakua.ui.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class EditProfileModal extends BasePage {

    @FindBy(xpath = "//input[@id='edit_currentPassword']/parent::span")
    private WebElement oldPasswordFieldWrapper;

    @FindBy(xpath = "//input[@id='edit_confirmPassword']/parent::span")
    private WebElement newPasswordFieldWrapper;

    @FindBy(xpath = "//input[@id='edit_lastName']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@id='edit_firstName']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@id='edit_phone']")
    private WebElement phoneInput;

    @FindBy(xpath = "//input[@name='checkbox']")
    private WebElement changePasswordCheckBox;

    @FindBy(xpath = "//input[@id='edit_currentPassword']")
    private WebElement oldPasswordInput;

    @FindBy(xpath = "//input[@id='edit_password']")
    private WebElement newPasswordInput;

    @FindBy(xpath = "//span[text()='Зберегти зміни']/ancestor::button")
    private WebElement saveButton;

    @FindBy(xpath = "//input[@id='edit_firstName']/ancestor::div[contains(@class, 'row')]//div[contains(@class, 'error')]")
    private WebElement firstnameErrorText;

    @FindBy(xpath = "//input[@id='edit_firstName']/ancestor::div[contains(@class, 'row')]//div[contains(@class, 'error')]")
    private List<WebElement> firstNameErrors;

    @FindBy(xpath = "//input[@id='edit_phone']/ancestor::div[contains(@class, 'row')]//div[contains(@class, 'error')]")
    private List<WebElement> phoneErrors;

    @FindBy(xpath = "//div[contains(@class, 'modal-body')]")
    private WebElement editProfileModalBody;

    @FindBy(xpath = "//input[@id='edit_lastName']/ancestor::div[contains(@class, 'row')]//div[contains(@class, 'error')]")
    private List<WebElement> lastnameErrors;

    public EditProfileModal(WebDriver driver) {
        super(driver);
    }

    public String getFirstName() {
        wait.visibility(firstNameInput);
        return firstNameInput.getAttribute("value");
    }

    public String getLastName() {
        wait.visibility(lastNameInput);
        return lastNameInput.getAttribute("value");
    }

    public String getPhone() {
        try {
            wait.value(phoneInput, "0", 3);
        } catch (TimeoutException ignore) {
        }
        return phoneInput.getAttribute("value");
    }

    @Step("Edit profile modal: enter lastname {lastName}")
    public EditProfileModal enterLastName(String lastName) {
        List<WebElement> previousPhoneErrors = lastnameErrors;
        action.setNewValueForInput(lastNameInput, lastName);
        wait.stalenessOfPreviousErrors(previousPhoneErrors, 1);
        return this;
    }

    @Step("Edit profile modal: enter firstname {firstName}")
    public EditProfileModal enterFirstName(String firstName) {
        List<WebElement> previousFirstNameErrors = firstNameErrors;
        action.setNewValueForInput(firstNameInput, firstName);
        wait.stalenessOfPreviousErrors(previousFirstNameErrors, 1);
        return this;
    }

    @Step("Edit profile modal: enter phone number {phone}")
    public EditProfileModal enterPhone(String phone) {
        List<WebElement> previousLastNameErrors = phoneErrors;
        action.setNewValueForInput(phoneInput, phone);
        wait.stalenessOfPreviousErrors(previousLastNameErrors, 1);
        return this;
    }

    @Step("Edit profile modal: select change password")
    public EditProfileModal togglePasswordChange() {
        changePasswordCheckBox.click();
        return this;
    }

    @Step("Edit profile modal: enter old password {oldPassword}")
    public EditProfileModal enterOldPassword(String oldPassword) {
        oldPasswordInput.sendKeys(oldPassword);
        return this;
    }

    @Step("Edit profile modal: enter new password {newPassword}")
    public EditProfileModal enterNewPassword(String newPassword) {
        newPasswordInput.sendKeys(newPassword);
        return this;
    }

    public String getFirstnameErrorText() {
        wait.visibility(firstnameErrorText, 1);
        return firstnameErrorText.getText();
    }

    public List<String> getLastNameErrorText() {
        wait.visibility(lastnameErrors, 1);
        return lastnameErrors.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getPhoneErrors() {
        wait.visibility(phoneErrors, 1);
        return phoneErrors
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @Step("Edit profile modal: save changes")
    public EditProfileModal save() {
        saveButton.click();
        return this;
    }

    public boolean isSaveChangesButtonEnabled() {
        saveButton.click();
        try {
            wait.invisibility(editProfileModalBody, 1);
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public String getOldPasswordFieldBorderColor(String color) {
        try {
            wait.attributeOfElementContains(oldPasswordFieldWrapper, "border-color", color);
        } catch (TimeoutException ignore) {
        }
        return oldPasswordFieldWrapper.getCssValue("border-color");
    }

    public String getNewPasswordFieldBorderColor(String color) {
        try {
            wait.attributeOfElementContains(newPasswordFieldWrapper, "border-color", color);
        } catch (TimeoutException ignore) {
        }
        return newPasswordFieldWrapper.getCssValue("border-color");
    }

    public String getRepeatNewPasswordFieldBorderColor(String color) {
        try {
            wait.attributeOfElementContains(newPasswordFieldWrapper, "border-color", color);
        } catch (TimeoutException ignore) {
        }
        return newPasswordFieldWrapper.getCssValue("border-color");
    }

    public WebElement getCloseEditProfileButton() {
        return driver.findElement(By.xpath("//button[contains(@class, 'modal-close')]"));
    }
}

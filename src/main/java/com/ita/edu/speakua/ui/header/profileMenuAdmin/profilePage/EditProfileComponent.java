package com.ita.edu.speakua.ui.header.profileMenuAdmin.profilePage;

import com.ita.edu.speakua.ui.utils.BaseMethods;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class EditProfileComponent extends BaseMethods {

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

    public EditProfileComponent(WebDriver driver) {
        super(driver);
    }

    @Step("Get current user first name")
    public String getFirstName() {
        waitVisibility(firstNameInput);
        return firstNameInput.getAttribute("value");
    }

    @Step("Get current user last name")
    public String getLastName() {
        waitVisibility(lastNameInput);
        return lastNameInput.getAttribute("value");
    }

    @Step("Get current user phone number")
    public String getPhone() {
        try {
            waitValue(phoneInput, "0", 3);
        } catch (TimeoutException ignore) {
        }
        return phoneInput.getAttribute("value");
    }

    @Step("Set lastname {lastName}")
    public EditProfileComponent setLastName(String lastName) {
        List<WebElement> previousPhoneErrors = lastnameErrors;
        setNewValueForInput(lastNameInput, lastName);
        waitStalenessOfPreviousErrors(previousPhoneErrors, 1);
        return this;
    }

    @Step("Set firstname {firstName}")
    public EditProfileComponent setFirstName(String firstName) {
        List<WebElement> previousFirstNameErrors = firstNameErrors;
        setNewValueForInput(firstNameInput, firstName);
        waitStalenessOfPreviousErrors(previousFirstNameErrors, 1);
        return this;
    }

    @Step("Set phone number {phone}")
    public EditProfileComponent setPhone(String phone) {
        List<WebElement> previousLastNameErrors = phoneErrors;
        setNewValueForInput(phoneInput, phone);
        waitStalenessOfPreviousErrors(previousLastNameErrors, 1);
        return this;
    }

    @Step("Select change password")
    public EditProfileComponent togglePasswordChange() {
        changePasswordCheckBox.click();
        return this;
    }

    @Step("Enter old password {oldPassword}")
    public EditProfileComponent enterOldPassword(String oldPassword) {
        oldPasswordInput.sendKeys(oldPassword);
        return this;
    }

    @Step("Enter new password {newPassword}")
    public EditProfileComponent enterNewPassword(String newPassword) {
        newPasswordInput.sendKeys(newPassword);
        return this;
    }

    @Step("Get firstname error text")
    public String getFirstnameErrorText() {
        waitVisibility(firstnameErrorText, 1);
        return firstnameErrorText.getText();
    }

    public List<String> getLastNameErrorText() {
        waitVisibility(lastnameErrors, 1);
        return lastnameErrors.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    @Step("Get a list of phone field error messages")
    public List<String> getPhoneErrorText() {
        waitVisibility(phoneErrors, 1);
        return phoneErrors.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    @Step("Save changes")
    public EditProfileComponent save() {
        saveButton.click();
        return this;
    }

    @Step("Check is 'Save' button enabled")
    public boolean saveChangesButtonIsEnable() {
        saveButton.click();
        try {
            waitInvisibility(editProfileModalBody, 1);
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public String getOldPasswordFieldBorderColor(String color) {
        try {
            waitAttributeOfElementContains(oldPasswordFieldWrapper, "border-color", color);
        } catch (TimeoutException ignore) {
        }
        return oldPasswordFieldWrapper.getCssValue("border-color");
    }

    public String getNewPasswordFieldBorderColor(String color) {
        try {
            waitAttributeOfElementContains(newPasswordFieldWrapper, "border-color", color);
        } catch (TimeoutException ignore) {
        }
        return newPasswordFieldWrapper.getCssValue("border-color");
    }

    public String getRepeatNewPasswordFieldBorderColor(String color) {
        try {
            waitAttributeOfElementContains(newPasswordFieldWrapper, "border-color", color);
        } catch (TimeoutException ignore) {
        }
        return newPasswordFieldWrapper.getCssValue("border-color");
    }

    public WebElement getCloseEditProfileButton() {
        return driver.findElement(By.xpath("//button[contains(@class, 'modal-close')]"));
    }
}

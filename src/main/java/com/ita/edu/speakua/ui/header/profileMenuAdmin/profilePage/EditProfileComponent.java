package com.ita.edu.speakua.ui.header.profileMenuAdmin.profilePage;

import com.ita.edu.speakua.ui.BaseMethods;
import com.ita.edu.speakua.ui.HomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class EditProfileComponent extends BaseMethods {

    @FindBy(xpath = "//input[@value='ROLE_USER']/..")
    private WebElement userButton;

    @FindBy(xpath = "//input[@value='ROLE_MANAGER']/..")
    private WebElement managerButton;

    @FindBy(xpath = "//input[@id='edit_lastName']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@id='edit_firstName']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@id='edit_phone']")
    private WebElement phoneInput;

    @FindBy(xpath = "//input[@id='edit_email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@id='edit_urlLogo']/following-sibling::span")
    private WebElement photoButton;

    @FindBy(xpath = "//input[@name='checkbox']")
    private WebElement changePasswordCheckBox;

    @FindBy(xpath = "//input[@id='edit_currentPassword']")
    private WebElement oldPasswordInput;

    @FindBy(xpath = "//input[@id='edit_currentPassword']/following-sibling::span")
    private WebElement oldPasswordViewBox;

    @FindBy(xpath = "//input[@id='edit_password']")
    private WebElement newPasswordInput;

    @FindBy(xpath = "//input[@id='edit_password']/following-sibling::span")
    private WebElement newPasswordViewBox;

    @FindBy(xpath = "//input[@id='edit_confirmPassword']")
    private WebElement repeatPasswordInput;

    @FindBy(xpath = "//input[@id='edit_confirmPassword']/following-sibling::span")
    private WebElement repeatNewPasswordViewBox;

    @FindBy(xpath = "//span[text()='Зберегти зміни']/ancestor::button")
    private WebElement saveButton;

    @FindBy(xpath = "//button[@class='ant-modal-close']")
    private WebElement closeButton;

    @FindBy(xpath = "//input[@id='edit_lastName']/parent::span")
    private WebElement lastnameFieldWrapper;

    @FindBy(xpath = "//input[@id='edit_firstName']/parent::span")
    private WebElement firstnameFieldWrapper;

    @FindBy(xpath = "//input[@id='edit_phone']/parent::span")
    private WebElement phoneFieldWrapper;

    @FindBy(xpath = "//input[@id='edit_lastName']/ancestor::div[contains(@class, 'row')]//div[contains(@class, 'error')]")
    private WebElement lastnameErrorText;

    @FindBy(xpath = "//input[@id='edit_firstName']/ancestor::div[contains(@class, 'row')]//div[contains(@class, 'error')]")
    private WebElement firstnameErrorText;

    @FindBy(xpath = "//input[@id='edit_firstName']/ancestor::div[contains(@class, 'row')]//div[contains(@class, 'error')]")
    private List<WebElement> firstNameErrors;

    @FindBy(xpath = "//input[@id='edit_phone']/ancestor::div[contains(@class, 'row')]//div[contains(@class, 'error')]")
    private List<WebElement> phoneErrors;

    @FindBy(xpath = "//div[contains(@class, 'modal-body')]")
    private WebElement editProfileModalBody;

    public EditProfileComponent(WebDriver driver) {
        super(driver);
    }

    @Step("Open user modal")
    public EditProfileComponent openUser() {
        userButton.click();
        return this;
    }

    @Step("Open manager modal")
    public EditProfileComponent openManager() {
        managerButton.click();
        return this;
    }

    @Step("Set lastname {lastName}")
    public EditProfileComponent fillInLastName(String lastName) {
        setNewValueForInput(lastNameInput, lastName);
        return this;
    }

    @Step("Set firstname {firstName}")
    public EditProfileComponent fillInFirstName(String firstName) {
        setNewValueForInput(firstNameInput, firstName);
        firstNameInput.submit();
        waitStalenessOfPreviousErrors(firstNameErrors);
        return this;
    }

    @Step("Set phone number {phone}")
    public EditProfileComponent fillInPhone(String phone) {
        setNewValueForInput(phoneInput, phone);
        waitStalenessOfPreviousErrors(phoneErrors);
        return this;
    }

    @Step("Enter email {email}")
    public EditProfileComponent fillInEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    @Step("Load photo")
    public EditProfileComponent loadPhoto() {
        photoButton.click();
        return this;
    }

    @Step("Select change password")
    public EditProfileComponent changePassword() {
        changePasswordCheckBox.click();
        return this;
    }

    @Step("Enter old password {oldPassword}")
    public EditProfileComponent enterOldPassword(String oldPassword) {
        oldPasswordInput.sendKeys(oldPassword);
        return this;
    }

    @Step("View old password")
    public EditProfileComponent viewOldPassword() {
        oldPasswordViewBox.click();
        return this;
    }

    @Step("Enter new password {newPassword}")
    public EditProfileComponent enterNewPassword(String newPassword) {
        newPasswordInput.sendKeys(newPassword);
        return this;
    }

    @Step("View new password")
    public EditProfileComponent viewNewPassword() {
        newPasswordViewBox.click();
        return this;
    }

    @Step("Re-enter the password {newPasswordRepeat}")
    public EditProfileComponent reEnterPassword(String newPasswordRepeat) {
        repeatPasswordInput.sendKeys(newPasswordRepeat);
        return this;
    }

    @Step("View new repeated password")
    public EditProfileComponent viewNewPasswordRepeat() {
        repeatNewPasswordViewBox.click();
        return this;
    }

    @Step("Get firstname error text")
    public String getFirstnameErrorText() {
        waitVisibilityOfWebElement(firstnameErrorText);
        return firstnameErrorText.getText();
    }

    public List<String> getPhoneErrorText() {
        waitVisibilityOfWebElements(phoneErrors);
        return phoneErrors.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    @Step("Save changes")
    public EditProfileComponent save() {
        saveButton.click();
        return this;
    }

    @Step("Check is 'save changes' button enabled")
    public boolean saveChangesButtonIsEnable() {
        saveButton.click();
        try {
            waitInvisibilityOfElement(editProfileModalBody, 1);
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public HomePage close() {
        closeButton.click();
        return new HomePage(driver);
    }
}

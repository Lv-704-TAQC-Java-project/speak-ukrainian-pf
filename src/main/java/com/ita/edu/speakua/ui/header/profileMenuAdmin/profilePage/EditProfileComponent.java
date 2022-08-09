package com.ita.edu.speakua.ui.header.profileMenuAdmin.profilePage;

import com.ita.edu.speakua.ui.BaseMethods;
import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.header.PopupMessageComponent;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
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
    private WebElement loadPhotoButton;
    @FindBy(xpath = "//input[@name='checkbox']")
    private WebElement changePasswordCheckBox;
    @FindBy(xpath = "//input[@id='edit_currentPassword']")
    private WebElement oldPasswordInput;
    @FindBy(xpath = "//input[@id='edit_currentPassword']/following-sibling::span")
    private WebElement viewOldPasswordButton;
    @FindBy(xpath = "//input[@id='edit_password']")
    private WebElement newPasswordInput;
    @FindBy(xpath = "//input[@id='edit_password']/following-sibling::span")
    private WebElement viewNewPasswordButton;
    @FindBy(xpath = "//input[@id='edit_confirmPassword']")
    private WebElement repeatNewPasswordInput;
    @FindBy(xpath = "//input[@id='edit_confirmPassword']/following-sibling::span")
    private WebElement viewRepeatNewPasswordButton;
    @FindBy(xpath = "//span[text()='Зберегти зміни']/ancestor::button")
    private WebElement saveChangesButton;
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
    @FindBy(xpath = "//input[@id='edit_lastName']/ancestor::div[contains(@class, 'row')]//div[contains(@class, 'error')]")
    private List<WebElement> lastnameErrors;

    public EditProfileComponent(WebDriver driver) {
        super(driver);
    }

    public EditProfileComponent userButtonClick() {
        userButton.click();
        return this;
    }

    public EditProfileComponent managerButtonClick() {
        managerButton.click();
        return this;
    }

    public EditProfileComponent fillInLastName(String lastName) {
        setNewValueForInput(lastNameInput, lastName);
        return this;
    }

    public EditProfileComponent fillInFirstName(String firstName) {
        setNewValueForInput(firstNameInput, firstName);
        waitForErrorsRefresh(firstNameErrors);
        return this;
    }

    public EditProfileComponent fillInPhone(String phone) {
        setNewValueForInput(phoneInput, phone);
        waitForErrorsRefresh(phoneErrors);
        return this;
    }

    public EditProfileComponent fillInEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public EditProfileComponent loadPhotoClick() {
        loadPhotoButton.click();
        return this;
    }

    public EditProfileComponent changePasswordClick() {
        changePasswordCheckBox.click();
        return this;
    }

    public EditProfileComponent inputOldPassword(String oldPassword) {
        oldPasswordInput.sendKeys(oldPassword);
        return this;
    }

    public EditProfileComponent viewOldPasswordClick() {
        viewOldPasswordButton.click();
        return this;
    }

    public EditProfileComponent inputNewPassword(String newPassword) {
        newPasswordInput.sendKeys(newPassword);
        return this;
    }

    public EditProfileComponent viewNewPasswordClick() {
        viewNewPasswordButton.click();
        return this;
    }

    public EditProfileComponent inputNewPasswordRepeat(String newPasswordRepeat) {
        repeatNewPasswordInput.sendKeys(newPasswordRepeat);
        return this;
    }

    public EditProfileComponent viewNewPasswordRepeatClick() {
        viewRepeatNewPasswordButton.click();
        return this;
    }

    public String getFirstnameErrorText() {
        waitVisibilityOfWebElement(firstnameErrorText);
        return firstnameErrorText.getText();
    }

    public List<String> getLastNameErrorText() {
        waitVisibilityOfWebElements(lastnameErrors);
        return lastnameErrors.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getPhoneErrorText() {
        waitVisibilityOfWebElements(phoneErrors);
        return phoneErrors.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public EditProfileComponent saveChangesButtonClick() {
        saveChangesButton.click();
        return this;
    }

    public boolean saveChangesButtonIsEnable() {
        saveChangesButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        try {
            new PopupMessageComponent(driver).getSuccessPopupMessageText();
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
    public HomePage closeButtonClick() {
        closeButton.click();
        return new HomePage(driver);
    }


}

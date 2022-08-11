package com.ita.edu.speakua.ui.header.profileMenuAdmin.profilePage;

import com.ita.edu.speakua.ui.header.HeaderComponent;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addClubComponent.AddClubMainInfoComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends HeaderComponent {

    @FindBy(xpath = "//div[@class='user-email-data']")
    private WebElement currentUserEmailField;

    @FindBy(xpath = "//div[@class='edit-button']//button")
    private WebElement editProfileButton;

    @FindBy(xpath = "//span[contains(text(),'Додати')]/parent::button")
    private WebElement addButton;

    @FindBy(xpath = "//li[@class = 'ant-dropdown-menu-item ant-dropdown-menu-item-only-child menu-item']//div[contains(text(),'Додати гурток')]")
    private WebElement addClubButton;

    private EditProfileComponent editProfileModalComponent;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step("Open edit profile page")
    public EditProfileComponent openEditProfilePage() {
        waitElementIsClickable(editProfileButton);
        editProfileButton.click();
        return new EditProfileComponent(driver);
    }

    @Step("Open a model for adding club")
    public AddClubMainInfoComponent openAddClubModal() {
        waitVisibilityOfWebElement(addButton);
        addButton.click();
        waitVisibilityOfWebElement(addClubButton);
        addClubButton.click();
        return new AddClubMainInfoComponent(driver);
    }

}

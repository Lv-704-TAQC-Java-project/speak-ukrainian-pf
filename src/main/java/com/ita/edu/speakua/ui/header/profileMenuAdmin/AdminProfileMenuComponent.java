package com.ita.edu.speakua.ui.header.profileMenuAdmin;

import com.ita.edu.speakua.ui.BaseMethods;
import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addCenterComponent.AddCenterMainInfoComponent;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addClubComponent.AddClubMainInfoComponent;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.AdministrationComponent;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.profilePage.ProfilePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminProfileMenuComponent extends BaseMethods {

    @FindBy(xpath = "//div[contains(text(),'Додати гурток')]")
    private WebElement addClubComponent;

    @FindBy(xpath = "//div[contains(text(), 'Додати центр')]")
    private WebElement addCenterModal;

    @FindBy(xpath = "//a[contains(@href, 'user')]")
    private WebElement userProfileBtn;

    @FindBy(xpath = "//span[contains(text(), 'Вийти')]")
    private WebElement logOutButton;

    @FindBy(xpath = "//span[contains(text(), 'Адміністрування')]")
    private WebElement administrationBtn;

    public AdminProfileMenuComponent(WebDriver driver) {
        super(driver);
    }

    @Step("Open a modal for adding club")
    public AddClubMainInfoComponent openAddClubModal() {
        addClubComponent.click();
        return new AddClubMainInfoComponent(driver);
    }

    @Step("Open a modal for adding center")
    public AddCenterMainInfoComponent openAddCenterModal() {
        addCenterModal.click();
        return new AddCenterMainInfoComponent(driver);
    }

    @Step("Open user profile page")
    public ProfilePage openUserProfilePage() {
        waitElementIsClickable(userProfileBtn);
        userProfileBtn.click();
        return new ProfilePage(driver);
    }

    @Step("Logout page")
    public HomePage logout() {
        logOutButton.click();
        return new HomePage(driver);
    }

    @Step("Open administration list")
    public AdministrationComponent openAdministrationModal() {
        waitVisibilityOfWebElement(administrationBtn);
        administrationBtn.click();
        return new AdministrationComponent(driver);
    }

    public AddCenterMainInfoComponent getAddCenterMainInfoComponent() {
        return new AddCenterMainInfoComponent(driver);
    }
}

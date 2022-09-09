package com.ita.edu.speakua.ui.header.profileMenuAdmin;

import com.ita.edu.speakua.ui.BasePage;
import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addCenterModal.AddCenterMainInfoStep;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addClubModal.AddClubMainInfoStep;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationMenu.AdministrationMenu;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.profilePage.ProfilePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminProfileMenu extends BasePage {

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

    public AdminProfileMenu(WebDriver driver) {
        super(driver);
    }

    @Step("Admin profile menu: open a modal for adding club")
    public AddClubMainInfoStep openAddClubModal() {
        addClubComponent.click();
        return new AddClubMainInfoStep(driver);
    }

    @Step("Admin profile menu: open a modal for adding center")
    public AddCenterMainInfoStep openAddCenterModal() {
        addCenterModal.click();
        return new AddCenterMainInfoStep(driver);
    }

    @Step("Admin profile menu: open user profile page")
    public ProfilePage openUserProfilePage() {
        wait.clickable(userProfileBtn);
        userProfileBtn.click();
        return new ProfilePage(driver);
    }

    @Step("Admin profile menu: logout")
    public HomePage logout() {
        logOutButton.click();
        return new HomePage(driver);
    }

    @Step("Admin profile menu: open administration menu")
    public AdministrationMenu openAdministrationMenu() {
        wait.visibility(administrationBtn);
        action.click(administrationBtn);
        return new AdministrationMenu(driver);
    }

    public AddCenterMainInfoStep getAddCenterMainInfoComponent() {
        return new AddCenterMainInfoStep(driver);
    }
}
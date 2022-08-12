package com.ita.edu.speakua.ui.header.profileMenuAdmin;

import com.ita.edu.speakua.ui.BaseMethods;
import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.clubs.PaginationComponent;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addCenterComponent.AddCenterMainInfoComponent;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addClubComponent.AddClubMainInfoComponent;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.AdministrationComponent;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.profilePage.ProfilePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminProfileMenuComponent extends BaseMethods {

    private AddCenterMainInfoComponent addCenterMainInfoComponent;

    @FindBy(xpath = "//div[contains(text(),'Додати гурток')]")
    private WebElement addClubComponent;

    @FindBy(xpath = "//div[contains(text(), 'Додати центр')]")
    private WebElement addCenterModal;

    @FindBy(xpath = "//a[contains(@href, 'user')]")
    private WebElement userProfileBtn;

    @FindBy(xpath = "//span[contains(text(), 'Вийти')]")
    private WebElement logOutBtn;

    @FindBy(xpath = "//span[contains(text(), 'Адміністрування')]")
    private WebElement administrationBtn;

    public AdminProfileMenuComponent(WebDriver driver) {
        super(driver);
    }

    public AddClubMainInfoComponent openAddGroupModal() {
        addClubComponent.click();
        return new AddClubMainInfoComponent(driver);
    }

    @Step("Open add center modal")
    public AddCenterMainInfoComponent openAddCenterModal() {
        addCenterModal.click();
        return new AddCenterMainInfoComponent(driver);
    }

    public ProfilePage openUserProfilePage() {
        waitElementIsClickable(userProfileBtn);
        userProfileBtn.click();
        return new ProfilePage(driver);
    }

    public HomePage clickLogOutBtn() {
        logOutBtn.click();
        return new HomePage(driver);
    }

    public AdministrationComponent openAdministrationModal() {
        waitVisibilityOfWebElement(administrationBtn);
        administrationBtn.click();
        return new AdministrationComponent(driver);
    }

    public AddCenterMainInfoComponent getAddCenterMainInfoComponent() {
        addCenterMainInfoComponent = new AddCenterMainInfoComponent(driver);
        return addCenterMainInfoComponent;
    }




}

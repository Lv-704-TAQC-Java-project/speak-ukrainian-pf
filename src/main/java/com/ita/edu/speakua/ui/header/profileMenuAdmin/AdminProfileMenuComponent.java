package com.ita.edu.speakua.ui.header.profileMenuAdmin;

import com.ita.edu.speakua.ui.BasePage;
import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addCenterComponent.AbstractAddCenterComponent;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addClubComponent.AddClubMainInfoComponent;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.profilePage.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminProfileMenuComponent extends BasePage {

    @FindBy(xpath = "//div[contains(text(),'Додати гурток')]")
    private WebElement addClubComponent;

    @FindBy(xpath = "//div[contains(text(), 'Додати центр')]")
    private WebElement addCenterModal;

    @FindBy(xpath = "//a[contains(@href, 'user')]")
    private WebElement userProfileBtn;

    @FindBy(xpath = "//span[contains(text(), 'Вийти')]")
    private WebElement logOutBtn;

    @FindBy(xpath = "//div[@class='ant-dropdown-menu-submenu-title']")
    private WebElement administrationBtn;

    public AdminProfileMenuComponent(WebDriver driver) {
        super(driver);
    }

    public AddClubMainInfoComponent openAddGroupModal() {
        addClubComponent.click();
        return new AddClubMainInfoComponent(driver);
    }

    public AbstractAddCenterComponent openAddCenterModal() {
        addCenterModal.click();
        return new AbstractAddCenterComponent(driver);
    }

    public ProfilePage openUserProfilePage() {
        userProfileBtn.click();
        return new ProfilePage(driver);
    }

    public HomePage clickLogOutBtn() {
        logOutBtn.click();
        return new HomePage(driver);
    }

    public AdministrationComponent openAdministrationModal() {
        administrationBtn.click();
        return new AdministrationComponent(driver);
    }


}

package com.ita.edu.speakua.ui.header;

import com.ita.edu.speakua.ui.utils.BaseMethods;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.AdminProfileMenuComponent;
import com.ita.edu.speakua.ui.header.profileMenuGuest.GuestProfileMenuComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class Header extends BaseMethods {
    @FindBy(xpath = "//div[contains(@class, 'user-profile')]")
    private WebElement profileMenuButton;

    @FindBy(xpath = "//span[@aria-label='user']")
    private WebElement imageUserComponent;

    @FindBy(xpath = "//span[contains(text(), 'Показати на мапі')]/parent::button")
    private WebElement showOnMapButton;

    @FindBy(xpath = "//div[contains(@class, 'user-profile')]")
    private WebElement profileMenuBtn;


    public Header(WebDriver driver) {
        super(driver);
    }

    public NavigationComponent getNavigationComponent() {
        return new NavigationComponent(driver);
    }

    public LocationComponent getLocationComponent() {

        return new LocationComponent(driver);
    }

    public GuestProfileMenuComponent getGuestProfileMenuComponent() {
        return new GuestProfileMenuComponent(driver);
    }

    public AdminProfileMenuComponent getAdminProfileMenuComponent() {
        return new AdminProfileMenuComponent(driver);
    }

    @Step("Open guest profile menu")
    public GuestProfileMenuComponent openGuestProfileMenu() {
        profileMenuButton.click();
        return getGuestProfileMenuComponent();
    }

    @Step("Open admin profile menu")
    public AdminProfileMenuComponent openAdminProfileMenu() {
        profileMenuButton.click();
        return getAdminProfileMenuComponent();
    }
    public Header getHomePageReload() {
        waitPageReload();
        return new Header(driver);
    }
}

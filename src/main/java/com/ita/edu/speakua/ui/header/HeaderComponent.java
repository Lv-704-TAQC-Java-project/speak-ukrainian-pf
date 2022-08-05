package com.ita.edu.speakua.ui.header;

import com.ita.edu.speakua.ui.BaseMethods;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.AdminProfileMenuComponent;
import com.ita.edu.speakua.ui.header.profileMenuGuest.GuestProfileMenuComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HeaderComponent extends BaseMethods {
    @FindBy(xpath = "//div[contains(@class, 'user-profile')]")
    private WebElement profileMenuButton;

    @FindBy(xpath = "//span[@aria-label='user']")
    private WebElement imageUserComponent;

    @FindBy(xpath = "//span[contains(text(), 'Показати на мапі')]/parent::button")
    private WebElement showOnMapButton;


    public HeaderComponent(WebDriver driver) {
        super(driver);
    }


    public NavigationComponent getNavigationComponent() {
        return new NavigationComponent(driver);
    }

    public LocationComponent getLocationComponent() {

        return new LocationComponent(driver);
    }

    public PopupMessageComponent getPopupMessageComponent() {
        return new PopupMessageComponent(driver);
    }

    public GuestProfileMenuComponent getGuestProfileMenuComponent() {
        return new GuestProfileMenuComponent(driver);
    }

    public AdminProfileMenuComponent getAdminProfileMenuComponent() {
        return new AdminProfileMenuComponent(driver);
    }

    public MapLocationComponent getMapLocationComponent() {
        return new MapLocationComponent(driver);
    }
}

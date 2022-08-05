package com.ita.edu.speakua.ui.header;

import com.ita.edu.speakua.ui.BasePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.AdminProfileMenuComponent;
import com.ita.edu.speakua.ui.header.profileMenuGuest.GuestProfileMenuComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HeaderComponent extends BasePage {
    @FindBy(xpath = "//div[contains(@class, 'user-profile')]")
    private WebElement profileMenuButton;

    @FindBy(xpath = "//span[@aria-label='user']")
    private WebElement imageUserComponent;

    @FindBy(xpath = "//span[contains(text(), 'Показати на мапі')]/parent::button")
    private WebElement showOnMapButton;

    private NavigationComponent navigationComponent;
    private LocationComponent locationComponent;
    private PopupMessageComponent popupMessageComponent;
    private GuestProfileMenuComponent guestProfileMenuComponent;
    private AdminProfileMenuComponent adminProfileMenuComponent;
    private MapLocationComponent mapLocationComponent;


    public HeaderComponent(WebDriver driver) {
        super(driver);
    }


    public NavigationComponent getNavigationComponent() {
        if (navigationComponent == null) {
            navigationComponent = new NavigationComponent(driver);
        }
        return navigationComponent;
    }

    public LocationComponent getLocationComponent() {
        if (locationComponent == null) {
            locationComponent = new LocationComponent(driver);
        }
        return new LocationComponent(driver);
    }

    public PopupMessageComponent getPopupMessageComponent() {
        if (popupMessageComponent == null) {
            popupMessageComponent = new PopupMessageComponent(driver);
        }
        return new PopupMessageComponent(driver);
    }

    public GuestProfileMenuComponent getGuestProfileMenuComponent() {
        if (guestProfileMenuComponent == null) {
            guestProfileMenuComponent = new GuestProfileMenuComponent(driver);
        }
        return guestProfileMenuComponent;
    }

    public AdminProfileMenuComponent getAdminProfileMenuComponent() {
        if (adminProfileMenuComponent == null) {
            adminProfileMenuComponent = new AdminProfileMenuComponent(driver);
        }
        return adminProfileMenuComponent;
    }

    public MapLocationComponent getMapLocationComponent() {
        if (mapLocationComponent == null) {
            mapLocationComponent = new MapLocationComponent(driver);
        }
        return mapLocationComponent;
    }
}

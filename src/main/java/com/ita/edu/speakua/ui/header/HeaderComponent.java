package com.ita.edu.speakua.ui.header;

import com.ita.edu.speakua.ui.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HeaderComponent extends BasePage {
    @FindBy(xpath = "//div[contains(@class, 'user-profile')]")
    private WebElement profileMenuButton;

    @FindBy(xpath = "//span[@aria-label='user']")
    private WebElement imageUserComponent;

    private NavigationComponent navigationComponent;
    private LocationComponent locationComponent;
    private PopupMessageComponent popupMessageComponent;


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

}

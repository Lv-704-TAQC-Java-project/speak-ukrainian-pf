package com.ita.edu.speakua.ui.header;

import com.ita.edu.speakua.ui.BasePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.AdminProfileMenu;
import com.ita.edu.speakua.ui.header.profileMenuGuest.GuestProfileMenu;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class Header extends BasePage {
    @FindBy(xpath = "//div[contains(@class, 'user-profile')]")
    private WebElement profileMenuButton;

    @FindBy(xpath = "//span[@aria-label='user']")
    private WebElement imageUserComponent;

    @FindBy(xpath = "//span[contains(text(), 'Показати на мапі')]/parent::button")
    private WebElement showOnMapButton;

    @FindBy(xpath = "//div[contains(@class, 'user-profile')]")
    private WebElement profileMenuBtn;

    @FindBy(xpath = "//header//div[contains(@class, 'city')]")
    private WebElement city;

    @FindBy(xpath = "//div[@class='search']//input")
    private WebElement searchInputField;

    @FindBy(xpath = "//div[@class='search']//span[contains(@class, 'anticon-search')]")
    private WebElement submitSearchButton;


    public Header(WebDriver driver) {
        super(driver);
    }

    public NavigationComponent getNavigationComponent() {
        return new NavigationComponent(driver);
    }

    public LocationComponent getLocationComponent() {
        return new LocationComponent(driver);
    }

    @Step("Header: open guest profile menu")
    public GuestProfileMenu openGuestProfileMenu() {
        profileMenuButton.click();
        return new GuestProfileMenu(driver);
    }

    @Step("Header: open admin profile menu")
    public AdminProfileMenu openAdminProfileMenu() {
        wait.visibility(profileMenuButton);
        profileMenuButton.click();
        return new AdminProfileMenu(driver);
    }

    public Header getHomePageReload() {
        wait.pageReload();
        return new Header(driver);
    }

    public String getLocationFromHeader() {
        return city.getText();
    }
}
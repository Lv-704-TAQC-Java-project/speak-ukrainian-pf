package com.ita.edu.speakua.ui;

import com.ita.edu.speakua.ui.clubs.ClubsPage;
import com.ita.edu.speakua.ui.header.HeaderComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends HeaderComponent {

    @FindBy(xpath = "//span[contains(@class, 'anticon-search')]")
    private WebElement searchButton;

    @FindBy(xpath = "//input[contains(@class, 'search')]")
    private WebElement searchInput;

    @FindBy(xpath = "//span[contains(@class, 'anticon-control')]")
    private WebElement advancedSearchButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public ClubsPage clickAdvancedSearchInput() {
        searchInput.click();
        return new ClubsPage(driver);
    }

    public ClubsPage clickAdvancedSearchButton() {
        advancedSearchButton.click();
        return new ClubsPage(driver);
    }

    public ClubsPage clickSearchButton() {
        searchButton.click();
        return new ClubsPage(driver);
    }


}

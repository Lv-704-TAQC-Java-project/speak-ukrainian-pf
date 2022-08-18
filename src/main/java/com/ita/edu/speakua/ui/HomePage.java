package com.ita.edu.speakua.ui;

import com.ita.edu.speakua.ui.clubs.ClubsPage;
import com.ita.edu.speakua.ui.header.HeaderComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

    public ClubsPage fillInSearch(String query) {
        setNewValueForInput(searchInput, query);
        return new ClubsPage(driver);
    }

    public ClubsPage clickAdvancedSearchInput() {
        searchInput.click();
        return new ClubsPage(driver);
    }

    @Step("open Advanced Search menu")
    public ClubsPage openAdvancedSearch() {
        advancedSearchButton.click();
        sleep(500);
        return new ClubsPage(driver);
    }

    public ClubsPage clickSearchButton() {
        searchButton.click();
        return new ClubsPage(driver);
    }

    public boolean isHomePageOpened(){
        waitPageReload();
        WebElement banner = driver.findElement(By.xpath("//div[@class='ant-carousel']"));
        return banner.isDisplayed();
    }
}

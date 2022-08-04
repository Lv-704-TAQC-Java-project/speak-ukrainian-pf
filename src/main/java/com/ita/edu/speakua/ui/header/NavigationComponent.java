package com.ita.edu.speakua.ui.header;

import com.ita.edu.speakua.ui.*;
import com.ita.edu.speakua.ui.clubs.ClubsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class NavigationComponent extends BasePage {

    @FindBy(xpath = "//header//div[contains(@class, 'logo')]/parent::a")
    WebElement homePageLink;

    @FindBy(xpath = "//header//a[contains(@href, 'clubs')]")
    WebElement clubsBtn;

    @FindBy(xpath = "//div/span[@class='ant-menu-title-content']")
    WebElement challengesBtn;

    @FindBy(xpath = "//header//a[contains(@href, 'news')]")
    WebElement newsBtn;

    @FindBy(xpath = "//header//a[contains(@href, 'about')]")
    WebElement aboutBtn;

    @FindBy(xpath = "//header//a[contains(@href, 'service')]")
    WebElement servicesBtn;




    public NavigationComponent(WebDriver driver) {
        super(driver);
    }

    public HomePage clickHomePageLink() {
        homePageLink.click();
        return new HomePage(driver);
    }

    public ClubsPage clickClubsBtn() {
        clubsBtn.click();
        return new ClubsPage(driver);
    }

    public NavigationComponent clickChallengesBtn() {
        challengesBtn.click();
        return this;
    }

    public NewsPage clickNewsBtn() {
        newsBtn.click();
        return new NewsPage(driver);
    }

    public AboutPage clickAboutBtn() {
        aboutBtn.click();
        return new AboutPage(driver);
    }

    public ServicesPage clickServicesBtn() {
        servicesBtn.click();
        return new ServicesPage(driver);
    }


}

package com.ita.edu.speakua.ui.clubs.card.components;

import com.ita.edu.speakua.ui.BaseMethods;
import com.ita.edu.speakua.ui.clubs.ExpandedCardComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.util.List;

public class CardComponent extends BaseMethods {
    protected WebElement cardBody;

    @FindBy(xpath = "./parent::div")
    protected WebElement cardWrapper;

    @FindBy(xpath = ".//div[@class='title']")
    protected WebElement cardTitle;

    @FindBy(xpath = ".//div[contains(@class, 'name')]")
    protected WebElement cardName;

    @FindBy(xpath = ".//*[@class='description']")
    protected WebElement cardDescription;

    @FindBy(xpath = ".//div[@class='address']")
    protected WebElement address;

    @FindBy(xpath = ".//li[contains(@class, 'zero')]")
    protected List<WebElement> starRatingZeroList;

    @FindBy(xpath = ".//li[contains(@class, 'full')]")
    protected List<WebElement> starRatingFullList;

    @FindBy(xpath = ".//div[contains(@class, 'club-tags-box')]//span")
    protected List<WebElement> listOfCategoriesOnCard;

    @FindBy(xpath = ".//div[contains(@class, 'club-online')]")
    protected WebElement availableOnline;

    public CardComponent(WebDriver driver, WebElement cardBody) {
        super(driver);
        this.cardBody = cardBody;
        PageFactory.initElements(new DefaultElementLocatorFactory(cardBody), this);
    }

    @Step("Get full card text")
    public String getCardText() {
        waitVisibility(cardWrapper);
        return cardWrapper.getText();
    }

    public WebElement getCardBody() {
        return cardBody;
    }

    public CardComponent waitNameRefresh(long timeoutMillis, int polling) {
        fluentWaitStaleness(cardName, timeoutMillis, polling);
        fluentWaitVisibility(cardName, timeoutMillis, polling);
        return this;
    }

    public String getCardName() {
        waitVisibility(cardName);
        return cardName.getText();
    }

    public List<WebElement> getStarRatingFullList() {
        return starRatingFullList;
    }

    public List<WebElement> getStarRatingZeroList() {
        return starRatingZeroList;
    }

    public ExpandedCardComponent cardTitleClick() {
        cardTitle.click();
        return new ExpandedCardComponent(driver);
    }

    public boolean isClubAvailableOnline() {
        return availableOnline.isDisplayed();
    }
}

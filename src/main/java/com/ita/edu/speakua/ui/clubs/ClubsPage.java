package com.ita.edu.speakua.ui.clubs;

import com.ita.edu.speakua.ui.clubs.cards.ClubCard;
import com.ita.edu.speakua.ui.clubs.cards.CenterCard;
import com.ita.edu.speakua.ui.header.Header;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClubsPage extends Header {

    private List<ClubCard> cards;
    private List<CenterCard> centers;
    private Pagination paginationComponent;
    private SortingPanel sortClubComponent;

    @FindBy(xpath = "//span[@aria-label='search']")
    private WebElement searchIcon;

    @FindBy(xpath = "//div[@class = 'content-clubs-list content-clubs-block']")
    private WebElement blockCardContainer;

    @FindBy(xpath = "//div[@class = 'content-clubs-list false']")
    private WebElement listCardContainer;

    @FindBy(xpath = "//span[contains(@class, 'anticon-control')]")
    private WebElement advancedSearchButton;

    @FindBy(xpath = "//div[contains(@class, 'card-body')]")
    private List<WebElement> cardsBody;

    @FindBy(xpath = "//div[@class='search']//input")
    private WebElement searchInputField;

    @FindBy(xpath = "//div[@class='search']//span[contains(@class, 'anticon-search')]")
    private WebElement submitSearchButton;

    public ClubsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get access to advanced search panel")
    public AdvancedSearchPanel getAdvancedSearchPanelComponent() {
        return new AdvancedSearchPanel(driver);
    }

    public ExpandedClub getExpandedCardComponent() {
        return new ExpandedClub(driver);
    }

    @Step("Get list of cards")
    public List<ClubCard> getCards() {
        wait.sleep(1000);
        this.cards = new ArrayList<>();
        for (WebElement card : cardsBody) {
            this.cards.add(new ClubCard(driver, card));
        }
        return this.cards;
    }

    public List<CenterCard> getCenters() {
        this.centers = new ArrayList<>();
        for (WebElement center : cardsBody) {
            this.centers.add(new CenterCard(driver, center));
        }
        return this.centers;
    }

    @Step("Open advanced search panel")
    public ClubsPage advancedSearchButtonClick() {
        clickManagingClubsPageElement(advancedSearchButton);
        return this;
    }

    public Pagination getPaginationComponent() {
        paginationComponent = new Pagination(driver);
        return paginationComponent;
    }

    public Pagination openPaginationComponent() {
        return getPaginationComponent().waitForPaginationComponentToOpen();
    }

    @Step("Get access to sort cards component")
    public SortingPanel getSortClubComponent() {
        if (sortClubComponent == null) {
            sortClubComponent = new SortingPanel(driver);
        }
        return sortClubComponent;
    }

    public boolean isBlockCardContainerDisplayed() {
        try {
            blockCardContainer.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isListCardContainerDisplayed() {
        try {
            listCardContainer.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickManagingClubsPageElement(WebElement element) {
        List<ClubCard> cards = new ClubsPage(driver).getCards();
        element.click();
        wait.sleep(2000);
    }

    @Step("Is closed advanced search panel")
    public boolean isDisappearsAdvancedSearchPanelComponent() {
        return advancedSearchButton == null;
    }

    @Step("Clubs page: Search by {phrase}")
    public ClubsPage fillInSearch(String phrase) {
        action.clearInput(searchInputField);
        searchInputField.click();
        char[] chars = phrase.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            searchInputField.sendKeys(String.valueOf(chars[i]));
            waitCardsRefresh(i == 0 ? 1000 : 500, 100);
        }
        submitSearchButton.click();
        waitCardsRefresh(1000, 100);
        return this;
    }

    private void waitCardsRefresh(long timeoutMillis, int polling) {
        try {
            getCards().get(0).waitNameRefresh(timeoutMillis, polling);
        } catch (TimeoutException | StaleElementReferenceException ignore) {
        }
    }

    public int getSearchInputLength() {
        return searchInputField.getAttribute("value").length();
    }

    public String[] getCenterNames() {
        return this
                .getCenters()
                .stream()
                .map(CenterCard::getTextCenterName)
                .toArray(String[]::new);
    }

    public List<String> getClubNames() {
        return this
                .getCards()
                .stream()
                .map(ClubCard::getCardName)
                .collect(Collectors.toList());
    }

    @Step("Clubs page: Search by {phrase}")
    public ClubsPage search(String phrase) {
        action.clearInput(searchInputField);
        searchInputField.click();

        enterSeparateChars(phrase);
        submitSearchButton.click();
        waitCardsRefresh(1000, 50);

        return new ClubsPage(driver);
    }

    @Step("Clubs page: Search {phrase} using JS set search field value. Maximum search field length {inputFieldMaxLength} chars.")
    public ClubsPage searchUsingJavaScript(String phrase, int inputLengthLimit) {
        action.clearInput(searchInputField);
        searchInputField.click();

        String underLimitSubstring = phrase.length() < inputLengthLimit
                ? phrase
                : phrase.substring(0, inputLengthLimit - 1);
        String overLimitSubstring = phrase.length() < inputLengthLimit
                ? phrase.substring(phrase.length() - 1)
                : phrase.substring(inputLengthLimit - 2);

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript(String.format("arguments[0].value='%s'", underLimitSubstring), searchInputField);

        searchInputField.sendKeys(Keys.BACK_SPACE);
        waitCardsRefresh(1000, 50);

        enterSeparateChars(overLimitSubstring);
        submitSearchButton.click();
        waitCardsRefresh(1000, 50);

        return new ClubsPage(driver);
    }

    private void enterSeparateChars(String phrase) {
        char[] chars = phrase.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            searchInputField.sendKeys(String.valueOf(chars[i]));
            waitCardsRefresh(i == 0 ? 1000 : 100, 50);
        }
    }
}
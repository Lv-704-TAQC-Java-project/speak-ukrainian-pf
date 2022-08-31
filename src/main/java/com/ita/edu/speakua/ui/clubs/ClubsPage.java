package com.ita.edu.speakua.ui.clubs;

import com.ita.edu.speakua.ui.clubs.card.components.CardComponent;
import com.ita.edu.speakua.ui.clubs.card.components.CenterComponent;
import com.ita.edu.speakua.ui.header.Header;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ClubsPage extends Header {

    private List<CardComponent> cards;
    private List<CenterComponent> centers;
    private PaginationComponent paginationComponent;
    private SortClubComponent sortClubComponent;

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

    @FindBy(xpath = "//input[contains(@id, 'rc_select')]")
    private WebElement searchInput;

    public ClubsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get access to advanced search panel")
    public AdvancedSearchPanelComponent getAdvancedSearchPanelComponent() {
        return new AdvancedSearchPanelComponent(driver);
    }

    public ExpandedCardComponent getExpandedCardComponent() {
        return new ExpandedCardComponent(driver);
    }

    @Step("Get list of cards")
    public List<CardComponent> getCards() {
        this.cards = new ArrayList<>();
        for (WebElement card : cardsBody) {
            this.cards.add(new CardComponent(driver, card));
        }
        return this.cards;
    }

    public List<CenterComponent> getCenters() {
        this.centers = new ArrayList<>();
        for (WebElement center : cardsBody) {
            this.centers.add(new CenterComponent(driver, center));
        }
        return this.centers;
    }

    @Step("Open advanced search panel")
    public ClubsPage advancedSearchButtonClick() {
        clickManagingClubsPageElement(advancedSearchButton);
        return this;
    }

    public PaginationComponent getPaginationComponent() {
        paginationComponent = new PaginationComponent(driver);
        return paginationComponent;
    }

    public PaginationComponent openPaginationComponent() {
        return getPaginationComponent().waitForPaginationComponentToOpen();
    }

    @Step("Get access to sort cards component")
    public SortClubComponent getSortClubComponent() {
        if (sortClubComponent == null) {
            sortClubComponent = new SortClubComponent(driver);
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
        List<CardComponent> cards = new ClubsPage(driver).getCards();
        element.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        try {
            wait.staleness(cards.get(0).getCardBody());
            wait.staleness(cards.get(cards.size() - 1).getCardBody());
        } catch (TimeoutException ignored) {
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Step("Is closed advanced search panel")
    public boolean isDisappearsAdvancedSearchPanelComponent() {
        return advancedSearchButton == null;
    }

    @Step("Search phrase by entering separate characters in search field: {query}")
    public ClubsPage fillInSearch(String query) {
        action.clearInput(searchInput);
        searchInput.click();
        char[] chars = query.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            searchInput.sendKeys(String.valueOf(chars[i]));
            waitCardsRefresh(i == 0 ? 1000 : 500, 100);
        }
        return this;
    }

    @Step("Search phrase by pasting input in search field: {query}. Maximum input length is {maxLength}.")
    public ClubsPage pasteInSearch(String query, int maxLength) {
        action.clearInput(searchInput);
        searchInput.click();
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        String pasteQuery = query.length() < maxLength
                ? query
                : query.substring(0, maxLength - 1);
        String typeQuery = query.length() < maxLength
                ? query.substring(query.length() - 2)
                : query.substring(maxLength - 2);
        executor.executeScript(String.format("arguments[0].value='%s'", pasteQuery), searchInput);

        searchInput.sendKeys(Keys.BACK_SPACE);
        waitCardsRefresh(500, 100);

        char[] chars = typeQuery.toCharArray();
        for (char aChar : chars) {
            searchInput.sendKeys(String.valueOf(aChar));
            waitCardsRefresh(500, 100);
        }
        return this;
    }

    private void waitCardsRefresh(long timeoutMillis, int polling) {
        try {
            getCards().get(0).waitNameRefresh(timeoutMillis, polling);
        } catch (TimeoutException | StaleElementReferenceException ignore) {
        }
    }

    public int getSearchInputLength() {
        return searchInput.getAttribute("value").length();
    }

    public String[] getCenterNames() {
        return this
                .getCenters()
                .stream()
                .map(CenterComponent::getTextCenterName)
                .toArray(String[]::new);
    }
}

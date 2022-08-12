package com.ita.edu.speakua.ui.clubs;

import com.ita.edu.speakua.ui.clubs.card.components.CardComponent;
import com.ita.edu.speakua.ui.clubs.card.components.CenterComponent;
import com.ita.edu.speakua.ui.header.HeaderComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ClubsPage extends HeaderComponent {

    private List<CardComponent> cards;
    private List<CenterComponent> centers;
    private PaginationComponent paginationComponent;

    private SortClubComponent sortClubComponent;

    @FindBy(xpath = "//div[@class = 'content-clubs-list false']")
    private WebElement blockCardContainer;

    @FindBy(xpath = "//div[@class = 'content-clubs-list content-clubs-block']")
    private WebElement listCardContainer;

    @FindBy(xpath = "//span[contains(@class, 'anticon-control')]")
    private WebElement advancedSearchButton;

    @FindBy(xpath = ".//div[contains(@class, 'card-body')]")
    private List<WebElement> cardsBody;

    public ClubsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open advanced search panel")
    public AdvancedSearchPanelComponent getAdvancedSearchPanelComponent() {
        return new AdvancedSearchPanelComponent(driver);
    }

    public ExpandedCardComponent getExpandedCardComponent() {
        return new ExpandedCardComponent(driver);
    }

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

    @Step("Close advanced search panel")
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

    public SortClubComponent getSortClubComponent() {
        if (sortClubComponent == null) {
            sortClubComponent = new SortClubComponent(driver);
        }
        return sortClubComponent;
    }

    public void clickManagingClubsPageElement(WebElement element) {
        List<CardComponent> cards = new ClubsPage(driver).getCards();
        element.click();
        driver.manage().timeouts().implicitlyWait(SHORT_TIMEOUT);
        for (CardComponent card : cards) {
            try {
                waitStalenessOfElement(card.getCardBody());
            } catch (TimeoutException ignored) {
            }
        }
    }

    public void clickManagingCenterPageElement(WebElement element) {
        List<CenterComponent> cards = new ClubsPage(driver).getCenters();
        element.click();
        driver.manage().timeouts().implicitlyWait(SHORT_TIMEOUT);
        sleep(2000);
    }

    @Step("Is closed advanced search panel")
    public boolean isDisappearsAdvancedSearchPanelComponent() {
        return advancedSearchButton == null;
    }
}

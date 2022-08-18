package com.ita.edu.speakua.ui.header.profileMenuAdmin.profilePage;

import com.ita.edu.speakua.ui.clubs.ExpandedCardComponent;
import com.ita.edu.speakua.ui.clubs.PaginationComponent;
import com.ita.edu.speakua.ui.clubs.card.components.CardComponent;
import com.ita.edu.speakua.ui.clubs.card.components.CenterComponent;
import com.ita.edu.speakua.ui.header.HeaderComponent;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addCenterComponent.AddCenterMainInfoComponent;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addClubComponent.AddClubMainInfoComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ProfilePage extends HeaderComponent {

    @FindBy(xpath = "//div[@class='user-email-data']")
    private WebElement currentUserEmailField;

    @FindBy(xpath = "//div[@class='edit-button']//button")
    private WebElement editProfileButton;

    @FindBy(xpath = "//span[contains(text(),'Додати')]/parent::button")
    private WebElement addButton;

    @FindBy(xpath = "//li[@class = 'ant-dropdown-menu-item ant-dropdown-menu-item-only-child menu-item']//div[contains(text(),'Додати гурток')]")
    private WebElement addClubButton;

    @FindBy(xpath = "//div[contains(text(),'Додати центр')]")
    private WebElement addCenterButton;

    @FindBy(xpath = "//div[contains(@class, 'center cards')]")
    private WebElement blockCardContainer;

    @FindBy(xpath = ".//div[contains(@class, 'card-body')]")
    private List<WebElement> cardsBody;

    private EditProfileComponent editProfileModalComponent;
    private List<CardComponent> cards;
    private List<CenterComponent> centers;
    private PaginationComponent paginationComponent;


    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step("Open edit profile page")
    public EditProfileComponent openEditProfilePage() {
        sleep(1000);
        waitClickable(editProfileButton);
        editProfileButton.click();
        return new EditProfileComponent(driver);
    }

    @Step("Open a model for adding club")
    public AddClubMainInfoComponent openAddClubModal() {
        waitVisibility(addButton);
        addButton.click();
        waitVisibility(addClubButton);
        addClubButton.click();
        return new AddClubMainInfoComponent(driver);
    }

    @Step("Open a modal for adding center")
    public AddCenterMainInfoComponent openAddCenterModal() {
        waitVisibility(addButton);
        addButton.click();
        sleep(5000);
        waitVisibility(addCenterButton);
        addCenterButton.click();
        return new AddCenterMainInfoComponent(driver);
    }

    public boolean isEditProfileButtonVisible() {
        try {
            return editProfileButton.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isAddButtonVisible(){
        try {
            return addButton.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
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

    public PaginationComponent getPaginationComponent() {
        paginationComponent = new PaginationComponent(driver);
        return paginationComponent;
    }

    public boolean isClubAvailableOnCurrentPage(String clubName){
        boolean isClubAdded = false;
        getPaginationComponent().clickLastPageButton();
        for(CardComponent card:getCards()){
            isClubAdded = card.getCardName().contains(clubName);
        }
        return isClubAdded;
    }


}

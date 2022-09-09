package com.ita.edu.speakua.ui.header.profileMenuAdmin.profilePage;

import com.ita.edu.speakua.ui.clubs.Pagination;
import com.ita.edu.speakua.ui.clubs.cards.ClubCard;
import com.ita.edu.speakua.ui.header.Header;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addClubModal.AddClubMainInfoStep;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProfilePage extends Header {

    private List<ClubCard> cards;
    private Pagination paginationComponent;

    @FindBy(xpath = "//div[@class='edit-button']//button")
    private WebElement editProfileButton;

    @FindBy(xpath = "//span[contains(text(),'Додати')]/parent::button")
    private WebElement addButton;

    @FindBy(xpath = "//li[@class = 'ant-dropdown-menu-item ant-dropdown-menu-item-only-child menu-item']//div[contains(text(),'Додати гурток')]")
    private WebElement addClubButton;

    @FindBy(xpath = ".//div[contains(@class, 'card-body')]")
    private List<WebElement> cardsBody;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step("Profile page: open edit profile page")
    public EditProfileModal openEditProfilePage() {
        wait.sleep(1000);
        wait.clickable(editProfileButton);
        editProfileButton.click();
        return new EditProfileModal(driver);
    }

    @Step("Profile page: open add club model")
    public AddClubMainInfoStep openAddClubModal() {
        wait.visibility(addButton);
        addButton.click();
        wait.visibility(addClubButton);
        addClubButton.click();
        return new AddClubMainInfoStep(driver);
    }

    public ClubCard getLastCard() {
        return new ClubCard(driver, cardsBody.get(cardsBody.size() - 1));
    }

    public Pagination getPaginationComponent() {
        paginationComponent = new Pagination(driver);
        return paginationComponent;
    }

    public boolean isClubAvailableOnCurrentPage(String clubName) {
        getPaginationComponent().clickLastPageButton();
        return getLastCard().getCardName().equals(clubName);
    }

    public boolean isClubWithCurrentDescription(String description) {
        getPaginationComponent().clickLastPageButton();
        return getLastCard().getCardDescription().equals(description);
    }

    public boolean isClubAvailable(String name, String description) {
        return isClubAvailableOnCurrentPage(name) && isClubWithCurrentDescription(description);
    }
}
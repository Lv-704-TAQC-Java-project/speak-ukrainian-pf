package com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addChallenge;

import com.ita.edu.speakua.ui.header.HeaderComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChallengePage extends HeaderComponent {
    @FindBy(xpath = "//a[contains(text(),'Додати челендж')]/ancestor::button")
    private WebElement addChallengeButton;

    public ChallengePage(WebDriver driver) {
        super(driver);
    }

    @Step("Open a model for adding challenges")
    public AddChallengePage openChallengeAddPage() {
        addChallengeButton.click();
        return new AddChallengePage(driver);
    }
}

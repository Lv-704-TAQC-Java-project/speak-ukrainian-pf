package com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent;

import com.ita.edu.speakua.ui.BaseMethods;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addChallenge.ChallengePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addTask.TaskPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdministrationComponent extends BaseMethods {

    @FindBy(xpath = "//a[contains(text(),'Завдання')]")
    private WebElement taskButton;

    @FindBy(xpath = "//a[contains(text(),'Челенджі')]")
    private WebElement challengeButton;

    public AdministrationComponent(WebDriver driver) {
        super(driver);
    }

    @Step("Open task page")
    public TaskPage openTaskPage() {
        waitVisibilityOfWebElement(taskButton);
        actionsClickOnElement(taskButton);
        return new TaskPage(driver);
    }

    @Step("Open challenge page")
    public ChallengePage openChallengePage() {
        challengeButton.click();
        return new ChallengePage(driver);
    }

}

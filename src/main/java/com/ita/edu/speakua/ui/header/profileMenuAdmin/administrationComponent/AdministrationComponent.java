package com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent;

import com.ita.edu.speakua.ui.BasePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addChallenge.ChallengePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addTask.TasksPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdministrationComponent extends BasePage {

    @FindBy(xpath = "//a[contains(text(),'Завдання')]")
    private WebElement taskButton;

    @FindBy(xpath = "//a[contains(text(),'Челенджі')]")
    private WebElement challengeButton;

    public AdministrationComponent(WebDriver driver) {
        super(driver);
    }

    @Step("Open tasks page")
    public TasksPage openTasksPage() {
        wait.visibility(taskButton);
        action.click(taskButton);
        return new TasksPage(driver);
    }

    @Step("Open challenge page")
    public ChallengePage openChallengePage() {
        wait.visibility(challengeButton);
        action.click(challengeButton);
        return new ChallengePage(driver);
    }
}

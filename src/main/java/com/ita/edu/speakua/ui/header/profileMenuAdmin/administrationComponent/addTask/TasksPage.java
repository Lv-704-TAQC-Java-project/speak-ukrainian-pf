package com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addTask;

import com.ita.edu.speakua.ui.header.HeaderComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class TasksPage extends HeaderComponent {
    @FindBy(xpath = "//a[contains(text(),'Додати завдання')]/ancestor::button")
    private WebElement addTaskButton;

    public TasksPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open page for adding a task")
    public AddTaskPage openTaskAddingPage(){
        waitVisibility(addTaskButton);
        addTaskButton.click();
        return new AddTaskPage(driver);
    }
}

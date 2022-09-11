package com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationMenu.addTask;

import com.ita.edu.speakua.ui.header.Header;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class TasksPage extends Header {
    @FindBy(xpath = "//a[contains(text(),'Додати завдання')]/ancestor::button")
    private WebElement addTaskButton;

    public TasksPage(WebDriver driver) {
        super(driver);
    }

    @Step("Tasks page: open page for adding a new task")
    public AddTaskPage openAddTaskPage(){
        wait.visibility(addTaskButton);
        addTaskButton.click();
        return new AddTaskPage(driver);
    }
}

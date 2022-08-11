package com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationComponent.addTask;

import com.ita.edu.speakua.ui.header.HeaderComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TaskPage extends HeaderComponent {

    @FindBy(xpath = "//a[contains(text(),'Додати завдання')]/ancestor::button")
    WebElement addTaskButton;

    public TaskPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click on 'add task' button")
    public AddTaskPage clickAddTaskButton(){
        waitVisibilityOfWebElement(addTaskButton);
        addTaskButton.click();
        return new AddTaskPage(driver);
    }
}

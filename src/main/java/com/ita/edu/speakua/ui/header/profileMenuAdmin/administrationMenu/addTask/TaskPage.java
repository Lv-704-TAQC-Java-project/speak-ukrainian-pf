package com.ita.edu.speakua.ui.header.profileMenuAdmin.administrationMenu.addTask;

import com.ita.edu.speakua.ui.header.Header;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class TaskPage extends Header {
    @FindBy(xpath = "//div[@class='task-header']")
    private WebElement name;

    @FindBy(xpath = "//div[@class='header-content']//div[@class='task-text']")
    private WebElement title;

    @FindBy(xpath = "//div[@class='task-content']//p")
    private WebElement description;

    @FindBy(xpath = "//div[@class='task-image-par']//img")
    private WebElement image;

    public TaskPage(WebDriver driver) {
        super(driver);
    }

    public String getNameText() {
        wait.visibility(name);
        return name.getText();
    }

    public String getTitleText() {
        wait.visibility(title);
        return title.getText();
    }

    public String getDescriptionText() {
        wait.visibility(description);
        return description.getText();
    }

    public String getImageURL() {
        wait.visibility(image);
        return image.getAttribute("src");
    }
}
package com.ita.edu.speakua.ui.clubs;

import com.ita.edu.speakua.ui.BaseMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ExpandedCardComponent extends BaseMethods {

    @FindBy(xpath = "//button[contains(@class,'close')]")
    private WebElement exitButton;

    @FindBy(xpath = "//div[@class='container']//span[@class='name']")
    private List<WebElement> listOfCategories;

    @FindBy(xpath = "//div[@class='ant-modal-body']//div[@class='age']//span[@class='years']")
    private WebElement ageDescription;

    public ExpandedCardComponent(WebDriver driver) {
        super(driver);
    }

    public Integer[] getAgeRestriction() {
        String line = ageDescription.getText();
        String regex = "[^\\d]+";
        String[] splitedLine = line.split(regex);
        Integer[] intLine = new Integer[2];

        for (int i = 1; i <= splitedLine.length - 1; i++) {
            intLine[i - 1] = Integer.parseInt(splitedLine[i]);
        }
        return intLine;
    }

    public List<String> getListOfNamesOfCategories() {
        List<String> listOfNameOfCategories = new ArrayList<>();
        for (WebElement category : listOfCategories) {
            listOfNameOfCategories.add(category.getText());
        }
        return listOfNameOfCategories;
    }

    public ClubsPage exitButtonClick() {
        exitButton.click();
        return new ClubsPage(driver);
    }
}
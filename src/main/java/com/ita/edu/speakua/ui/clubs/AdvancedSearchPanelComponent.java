package com.ita.edu.speakua.ui.clubs;

import com.ita.edu.speakua.ui.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AdvancedSearchPanelComponent extends BasePage {

    private WebElement districtListSectionChildren;
    private WebElement metroListSectionChildren;
    private List<WebElement> listOfCategoriesCheckList;

    @FindBy(xpath = "//section[contains(@class, 'club-list')]/child::*")
    private List<WebElement> clubListSectionChildren;

    @FindBy(xpath = "//aside")
    private WebElement asideAdvancedSearchMenu;

    @FindBy(xpath = "//div[@class='club-list-label']")
    private WebElement advancedSearchHeader;

    @FindBy(xpath = "//input[@id='basic_cityName']//ancestor::div[contains(@class, 'selector')]")
    private WebElement citySelector;

    @FindBy(xpath = "//input[@id='basic_districtName']")
    private WebElement districtSelector;

    @FindBy(xpath = "//input[@id='basic_stationName']")
    private WebElement metroSelector;

    @FindBy(xpath = "//input[@id='basic_districtName']/../../..//div")
    private WebElement clearDistrictSelector;

    @FindBy(xpath = "//div[@id='basic_categoriesName']")
    private WebElement categoriesCheckList;

    @FindBy(xpath = "//label/span[contains(text(),'Гурток')]")
    private WebElement clubRadioButton;

    @FindBy(xpath = "//label/span[contains(text(),'Центр')]")
    private WebElement centerRadioButton;

    @FindBy(xpath = "//div[@id='basic_isOnline']")
    private WebElement availableOnline;

    @FindBy(xpath = "//label[contains(text(),'Категорії')]/../..")
    private WebElement categoriesBlock;

    @FindBy(xpath = "//label[contains(text(),'Вік дитини')]/../..")
    private WebElement childAgeBlock;

    @FindBy(xpath = "//span[@id='basic_age']//input")
    private WebElement childAgeInput;

    @FindBy(xpath = "//div[@id='basic_districtName_list']/following-sibling::div//div[@class='rc-virtual-list-holder-inner']")
    private WebElement scrollDistrictSelector;

    public AdvancedSearchPanelComponent(WebDriver driver) {
        super(driver);
    }

    public WebElement getDistrictListSectionChildren(String district) {
        districtListSectionChildren = driver.findElement(By.xpath(String.format("//div[@title='%s']", district)));
        return districtListSectionChildren;
    }

    public WebElement getMetroListSectionChildren(String metroStation) {
        metroListSectionChildren = driver.findElement(By.xpath(String.format("//div[@title='%s']", metroStation)));
        return metroListSectionChildren;
    }

    public List<WebElement> getListOfCategoriesCheckList() {
        if (listOfCategoriesCheckList == null) {
            listOfCategoriesCheckList = categoriesCheckList.findElements(By.xpath(".//input[@type='checkbox']"));
        }
        return listOfCategoriesCheckList;
    }

    public String getAdvancedSearchHeaderText() {
        return advancedSearchHeader.getText();
    }

    public boolean citySelectionInputIsVisible() {
        return citySelector.isDisplayed();
    }

    public boolean basicCategoriesCheckListIsVisible() {
        return categoriesCheckList.isDisplayed();
    }

    public boolean availableOnlineIsVisible() {
        return availableOnline.isDisplayed();
    }

    public boolean advancedAsideMenuIsVisible() {
        try {
            return asideAdvancedSearchMenu.isDisplayed();
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }

    public AdvancedSearchPanelComponent availableOnlineCheckboxClick() {
        clickManagingClubsPageElement(availableOnline);
        return this;
    }

    public boolean availableOnlineCheckboxIsDisplayed() {
        try {
            return availableOnline.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean categoriesBlockIsDisplayed() {
        try {
            return categoriesBlock.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean childAgeBlockIsDisplayed() {
        try {
            return childAgeBlock.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public AdvancedSearchPanelComponent openDistrictInputSelect() {
        clickManagingClubsPageElement(districtSelector);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public AdvancedSearchPanelComponent clickDistrictWithName(String district) {
        clickManagingClubsPageElement(getDistrictListSectionChildren(district));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public AdvancedSearchPanelComponent openMetroInputSelect() {
        clickManagingClubsPageElement(metroSelector);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public AdvancedSearchPanelComponent clickMetroWithName(String district) {
        clickManagingClubsPageElement(getDistrictListSectionChildren(district));
        return this;
    }

    public AdvancedSearchPanelComponent openDistrictInputSelectCenter() {
        clickManagingCenterPageElement(districtSelector);
        return this;
    }

    public AdvancedSearchPanelComponent clickDistrictWithNameCenter(String district) {
        clickManagingCenterPageElement(getDistrictListSectionChildren(district));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public AdvancedSearchPanelComponent openMetroInputSelectCenter() {
        clickManagingCenterPageElement(metroSelector);
        return this;
    }

    public AdvancedSearchPanelComponent clickMetroWithNameCenter(String district) {
        clickManagingCenterPageElement(getDistrictListSectionChildren(district));
        return this;
    }

    public AdvancedSearchPanelComponent openCityInputSelect() {
        Actions actions = new Actions(driver);
        actions.moveToElement(citySelector).click().perform();
        return this;
    }

    public AdvancedSearchPanelComponent scrollDistrictInputSelect() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop=arguments[1].offsetTop",
                scrollDistrictSelector);
        return this;
    }

    public WebElement getCategoryFromString(String name) {
//        waitVisibilityOfElement(By.xpath(String.format("//input[@value='%s']", name)), Duration.ofSeconds(2));
        return driver.findElement(By.xpath(String.format("//input[@value='%s']", name)));
    }

    public AdvancedSearchPanelComponent categoryClick(String categoryName) {
        clickManagingClubsPageElement(getCategoryFromString(categoryName));
        return this;
    }

    public AdvancedSearchPanelComponent clearDistrictInputSelect() {
        clearDistrictSelector.click();
        return this;
    }

    public AdvancedSearchPanelComponent clubRadioButtonClick() {
        waitVisibilityOfWebElement(clubRadioButton);
        clubRadioButton.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return this;
    }

    public AdvancedSearchPanelComponent centerRadioButtonClick() {
        waitVisibilityOfWebElement(centerRadioButton);
        clickManagingClubsPageElement(centerRadioButton);
        return this;
    }

    public AdvancedSearchPanelComponent centerRadioButtonClickAnother() {
        waitVisibilityOfWebElement(centerRadioButton);
        centerRadioButton.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public boolean IsClubButtonSelected() {
        try {
            return driver.findElement(
                            By.xpath("//label[contains(@class,'ant-radio-wrapper-checked')]/span[contains(text(),'Гурток')]"))
                    .isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean IsCenterButtonSelected() {
        try {
            return driver.findElement(
                            By.xpath("//label[contains(@class,'ant-radio-wrapper-checked')]/span[contains(text(),'Центр')]"))
                    .isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public AdvancedSearchPanelComponent enterChildAge(Integer age) {
        childAgeInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        childAgeInput.sendKeys(age.toString());
        clickManagingClubsPageElement(childAgeInput);
        return this;
    }

}
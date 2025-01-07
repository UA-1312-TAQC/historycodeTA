package com.historycode.ui.page.adminpanel;

import com.historycode.ui.component.DropdownBase;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

@Getter
public class StreetcodeEditPage extends BasePageAdminPanel {

    @Setter
    @FindBy(id = "streetcodeNumber")
    private WebElement streetcodeNumber;

    @Setter
    @FindBy(id = "streetcodeType")
    private WebElement streetcodeTypeRadio;

    @Setter
    @FindBy(id = "mainTitle")
    private WebElement mainTitle;

    @Setter
    @FindBy(id = "name")
    private WebElement firstName;

    @Setter
    @FindBy(id = "surname")
    private WebElement surname;

    @Setter
    @FindBy(id = "alias")
    private WebElement summery;

    @Setter
    @FindBy(id = "streetcodeUrlName")
    private WebElement streetcodeUrlName;

    @FindBy(id = "dateString")
    private WebElement dateString;

    @Setter
    @FindBy(id = "streetcodeFirstDate")
    private WebElement streetcodeFirstDate;

    @Setter
    @FindBy(id = "streetcodeSecondDate")
    private WebElement streetcodeSecondDate;

    @FindBy(id = "teaser")
    private WebElement teaser;

    @FindBy(id = "animations")
    private WebElement pictureColor;

    @FindBy(id = "pictureBlackWhite")
    private WebElement pictureBlackWhite;

    @FindBy(id = "pictureRelations")
    private WebElement pictureRelations;

    @FindBy(id = "audio")
    private WebElement audio;

    @FindBy(id = "title")
    private WebElement title;

    @FindBy(xpath = "//button/span[text()='Попередній перегляд тексту']")
    private WebElement previewButton;

    @FindBy(xpath = "//div[@class='TextContainer']")
    private WebElement textContainer;

    @FindBy(xpath = "//input[@title='video']")
    private WebElement videoUrl;

    @FindBy(xpath = "//div[h4[text()='Попередній перегляд']]/div/iframe")
    private WebElement videoPreview;

    @FindBy(xpath = "//button/span[text()='Попередній перегляд']")
    private WebElement videoPreviewButton;

    @FindBy(tagName = "iframe")
    private WebElement videoIframe;

    @FindBy(xpath = "//div[h2[text()='Бігуча стрічка']]/span/input")
    private WebElement ticker;

    @FindBy(id = "arlink")
    private WebElement arlink;

    @FindBy(xpath = "//button[@name='Зберегти як чернетку']")
    private WebElement saveDraftButton;

    @FindBy(xpath = "//button[@name='Опублікувати']")
    private WebElement publishButton;


    public StreetcodeEditPage(WebDriver driver) {super(driver);}
        private DropdownBase dropdownBase;

    public StreetcodeEditPage (WebDriver driver, DropdownBase dropdownBase) {
        super(driver);
        this.dropdownBase = dropdownBase;
    }

    public void selectFirstFormatdateDropdown(String text) {
        String firstDropDownXpath = "(//div[@class='ant-select-selector'])[1]";
        dropdownBase.selectOptionFromDropdown(firstDropDownXpath, text);
    }

    public void selectSecondFormatdateDropdown(String text) {
        String firstDropDownXpath = "(//div[@class='ant-select-selector'])[2]";
        dropdownBase.selectOptionFromDropdown(firstDropDownXpath, text);
    }

    public void selectTagsDropdown(String text) {
        String firstDropDownXpath = "(//div[@class='ant-select-selector'])[3]";
        dropdownBase.selectOptionFromDropdown(firstDropDownXpath, text);
    }

    public boolean checkAllTags(List<String> expectedTags) {
        List<WebElement> tagElements = driver.findElements(By.xpath("//div/button[@type='button' and @class='ant-btn css-k7429z ant-btn-default tagItem']"));
        List<String> actualTags = new ArrayList<>();
        for (WebElement element : tagElements) {
            actualTags.add(element.getText());
        }
        return actualTags.containsAll(expectedTags);
    }

    public boolean checkAllWowFacts(List<String> expectedTags) {
        List<WebElement> wowfactsElements = driver.findElements(By.xpath("//div[h2[text()='Wow-факти']]/div/div[@data-rbd-draggable-context-id]"));
        List<String> actualWowfacts = new ArrayList<>();
        for (WebElement element : wowfactsElements) {
            actualWowfacts.add(element.getText());
        }
        return actualWowfacts.containsAll(expectedTags);
    }

    public WebElement clickWowfactEditButton(int index) {
        WebElement element = driver.findElement(By.xpath("(//div[h2[text()='Wow-факти']]/div/div/div/div/div/span[@aria-label='edit'])[" + index + "]"));
        element.click();
        return element;
    }

    public WebElement clickWowfactDeleteButton(int index) {
        WebElement element = driver.findElement(By.xpath("(//div[h2[text()='Wow-факти']]/div/div/div/div/div/span[@aria-label='delete'])[" + index + "]"));
        element.click();
        return element;
    }

    public boolean checkAllChonologyItems(List<String> expectedTags) {
        List<WebElement> chronologyElements = driver.findElements(By.xpath("//div[h2[text()='Хронологія']]/div/div[@class='textBlockButton']"));
        List<String> actualChronology = new ArrayList<>();
        for (WebElement element : chronologyElements) {
            actualChronology.add(element.getText());
        }
        return actualChronology.containsAll(expectedTags);
    }

    public WebElement clickChronologyEditButton(int index) {
        WebElement element = driver.findElement(By.xpath("(//div[h2[text()='Хронологія']]/div/div/div/div/span[@aria-label='edit'])[" + index + "]"));
        element.click();
        return element;
    }

    public WebElement clickChronologyDeleteButton(int index) {
        WebElement element = driver.findElement(By.xpath("(//div[h2[text()='Хронологія']]/div/div/div/div/span[@aria-label='delete'])[" + index + "]"));
        element.click();
        return element;
    }

    public void selectConnectionsDropdown(String text) {
        String firstDropDownXpath = "(//div[@class='ant-select-selector'])[4]";
        dropdownBase.selectOptionFromDropdown(firstDropDownXpath, text);
    }

    public WebElement clickFansEditButton(int index) {
        WebElement element = driver.findElement(By.xpath("((//div[h2[text()='Для фанатів']]/div/div/div/div/span[@aria-label='edit'])[" + index + "]"));
        element.click();
        return element;
    }

    public WebElement clickFansDeleteButton(int index) {
        WebElement element = driver.findElement(By.xpath("((//div[h2[text()='Для фанатів']]/div/div/div/div/span[@aria-label='delete'])[" + index + "]"));
        element.click();
        return element;
    }

    public void selectParntersDropdown(String text) {
        String firstDropDownXpath = "(//div[@class='ant-select-selector'])[5]";
        dropdownBase.selectOptionFromDropdown(firstDropDownXpath, text);
    }
}

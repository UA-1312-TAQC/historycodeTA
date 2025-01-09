package com.historycode.ui.page.adminpanel;

import com.historycode.ui.component.DropdownBase;
import com.historycode.ui.component.streetcodeEditor.*;
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

    @Setter
    @FindBy(id = "teaser")
    private WebElement teaser;

    @FindBy(xpath = "//input[@id='animations']")
    private WebElement AddPictureColorButton;

    @FindBy(xpath = "//div[div/label[@title='Кольорове']]/div/div/div/span/div/div[@class='ant-upload-list-item-container']")
    private WebElement pictureColor;

    @FindBy(xpath = "//input[@id='pictureBlackWhite']")
    private WebElement addPictureBwButton;

    @FindBy (xpath = "//div[div/label[@title='Чорнобіле']]/div/div/div/span/div/div[@class='ant-upload-list-item-container']")
    private WebElement pictureBW;

    @FindBy(xpath = "//input[@id='pictureRelations']")
    private WebElement pictureRelations;

    @FindBy(id = "//input[@id='audio']")
    private WebElement audio;

    @Setter
    @FindBy(id = "title")
    private WebElement title;

    @FindBy(xpath = "//button/span[text()='Попередній перегляд тексту']")
    private WebElement textPreviewButton;

    @FindBy(xpath = "//div[@class='TextContainer']")
    private WebElement textContainer;

    @FindBy(xpath = "//input[@title='video']")
    private WebElement videoUrl;

    @FindBy(xpath = "//div[h4[text()='Попередній перегляд']]/div/iframe")
    private WebElement videoPreview;

    @FindBy(xpath = "//button/span[text()='Попередній перегляд']")
    private WebElement videoPreviewButton;

    @FindBy(xpath = "//div[h2[text()='Wow-факти']]/div/button")
    private WebElement addWowfactButton;

    @FindBy(xpath = "//div[h2[text()='Хронологія']]/div/button")
    private WebElement addChronologyButton;

    @FindBy(xpath = "//div[h2[text()='Арт-галерея']]/div/span/div/div/span/input")
    private WebElement addArtItemButton;

    @FindBy(xpath = "//button/span[text()='Обрати шаблон']")
    private WebElement chooseTemplateButton;

    @FindBy(xpath = "//div[h2[text()='Для фанатів']]/div/button")
    private WebElement addForfansButton;

    @FindBy(xpath = "//button/span[text()='Додати']")
    private WebElement addPartnersButton;

    @Setter
    @FindBy(xpath = "//div[h2[text()='Бігуча стрічка']]/span/input")
    private WebElement ticker;

    @Setter
    @FindBy(id = "arlink")
    private WebElement arlink;

    @FindBy(xpath = "//button[@name='Зберегти як чернетку']")
    private WebElement saveDraftButton;

    @FindBy(xpath = "//button[@name='Опублікувати']")
    private WebElement publishButton;


    public StreetcodeEditPage(WebDriver driver) {super(driver);}

    /** Dropdowns */

    public DropdownBase getFirstFormatdateDropdow() {
        WebElement firstDropdownRoot = driver.findElement(By.xpath("(//div[@class='ant-select-selector'])[1]"));
        return new DropdownBase(driver, firstDropdownRoot);
    }

    public DropdownBase getSecondFormatdateDropdown() {
        WebElement secondDropdownRoot = driver.findElement(By.xpath("(//div[@class='ant-select-selector'])[2]"));
        return new DropdownBase(driver, secondDropdownRoot);
    }

    public DropdownBase getTagsDropdown() {
        WebElement tagsDropdownRoot = driver.findElement(By.xpath("(//div[@class='ant-select-selector'])[3]"));
        return new DropdownBase(driver, tagsDropdownRoot);
    }

    public boolean checkAllTags(List<String> expectedTags) {
        List<WebElement> tagElements = driver.findElements(By.xpath("//div/button[@type='button' and @class='ant-btn css-k7429z ant-btn-default tagItem']"));
        List<String> actualTags = new ArrayList<>();
        for (WebElement element : tagElements) {
            actualTags.add(element.getText());
        }
        return actualTags.containsAll(expectedTags);
    }

    public DropdownBase getConnectionsDropdown() {
        WebElement connectionsRootElement = driver.findElement(By.xpath("(//div[@class='ant-select-selector'])[4]"));
        return new DropdownBase(driver, connectionsRootElement);
    }

    public DropdownBase getParntersDropdown() {
        WebElement partnersRootElement = driver.findElement(By.xpath("(//div[@class='ant-select-selector'])[5]"));
        return new DropdownBase(driver, partnersRootElement);
    }

    /** Datepickers */

    public DatePicker getFirstDatePicker() {
        WebElement firstDatepicker = driver.findElement(By.xpath("(//div[@class='ant-picker-input'])[1]"));
        return new DatePicker(driver, firstDatepicker);
    }

    public DatePicker getSecondDatePicker() {
        WebElement secondDatepicker = driver.findElement(By.xpath("(//div[@class='ant-picker-input'])[2]"));
        return new DatePicker(driver, secondDatepicker);
    }

    /** Datepickers */

    public ResolutionPopover getResolutionPopover360() {
        WebElement resol360 = driver.findElement(By.xpath("//p[@class='device-size' and text()='360']"));
        resol360.click();
        WebElement popover = driver.findElement(By.xpath("//span[@class='ant-popover-open']"));
        return new ResolutionPopover(driver, popover);
    }

    public ResolutionPopover getResolutionPopover768() {
        WebElement resol360 = driver.findElement(By.xpath("//p[@class='device-size' and text()='768']"));
        resol360.click();
        WebElement popover = driver.findElement(By.xpath("//span[@class='ant-popover-open']"));
        return new ResolutionPopover(driver, popover);
    }

    public ResolutionPopover getResolutionPopover1600() {
        WebElement resol360 = driver.findElement(By.xpath("//p[@class='device-size' and text()='1600']"));
        resol360.click();
        WebElement popover = driver.findElement(By.xpath("//span[@class='ant-popover-open']"));
        return new ResolutionPopover(driver, popover);
    }

    /** Wow-Fact methods */

    public WowFactsModal getWowFactsModal() {
        WebElement wowRootElement = driver.findElement(By.xpath("//div[@role='dialog']/div/div/div/form/div/h2[text()='Wow-Факт']"));
        return new WowFactsModal(driver, wowRootElement);
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

    /** Chronology methods */

    public ChronologyModal getChronologyModal() {
        WebElement chronologyRootElement = driver.findElement(By.xpath("//div[@role='dialog']/div/div/div/form/div/h2[text()='Хронологія']"));
        return new ChronologyModal(driver, chronologyRootElement);
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

    /** Templates methods */

    public TemplateModal getTemplateModal() {
        WebElement templatesRootElement = driver.findElement(By.xpath("//div[@class='ArtGalleryImgContainer']"));
        return new TemplateModal(driver, templatesRootElement);
    }


    /** ForFans methods */

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

}

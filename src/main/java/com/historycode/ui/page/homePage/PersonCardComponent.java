package com.historycode.ui.page.homePage;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.page.HistoryCodePage.HistoryCodePage;
import lombok.Getter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class PersonCardComponent extends BaseComponent {

    @FindBy(xpath = ".//p[contains(@class, 'cardTextContainerTitle')]")
    private WebElement personName;

    @FindBy(xpath = ".//p[contains(@class, 'cardTextContainerSubTitle')]")
    private WebElement category;

    @FindBy(xpath = ".//p[contains(@class, 'cardTextContainerText')]")
    private WebElement description;

    @FindBy(xpath = ".//a[@class = 'cardTextContainerButton']")
    private WebElement moreLink;

    @FindBy(xpath = ".//img")
    private WebElement personImage;
    @Getter
    @FindBy(xpath = ".//a[contains(@class, 'cardTextContainerButton')]")
    private WebElement toHistoryCodePage;

    public PersonCardComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);
    }

    public String getPersonName() {
        return personName.getText().trim();
    }

    public String getCategory() {
        if (category == null || !category.isDisplayed()) {
            return null; // Повертаємо null, якщо елемент відсутній або не відображається
        }
        return category.getText().trim();
    }

    public String getDescription() {
        return description.getText().trim();
    }

    public void clickMore() {
        moreLink.click();
    }

    public String getImageSrc() {
        return personImage.getAttribute("src");
    }

    public HistoryCodePage clickToHistoryCode() {
            waitUntilElementVisible(toHistoryCodePage);
            toHistoryCodePage.click();
            return new HistoryCodePage(driver);

    }
}

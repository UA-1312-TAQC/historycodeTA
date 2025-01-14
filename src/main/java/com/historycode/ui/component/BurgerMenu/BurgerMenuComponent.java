package com.historycode.ui.component.BurgerMenu;

import com.historycode.ui.page.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BurgerMenuComponent extends BasePage {

    @FindBy(xpath = "//div[contains(@class, 'drawerContainer')]")
    private WebElement containerMenu;

    @FindBy(xpath = "//a[contains(@class, 'headerItem')]")
    private List<WebElement> menuItems;

    public BurgerMenuComponent(WebDriver driver, WebElement rootElement) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);
    }

    public void clickMenuItem(String itemName) {
        WebElement menuItem = wait.until(ExpectedConditions.visibilityOfAllElements(menuItems))
                .stream()
                .filter(item -> item.getText().equalsIgnoreCase(itemName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Menu item " + itemName + " not found"));
        wait.until(ExpectedConditions.elementToBeClickable(menuItem));
        menuItem.click();
    }
}
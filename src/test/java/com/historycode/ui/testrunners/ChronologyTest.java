package com.historycode.ui.testrunners;

import com.historycode.ui.component.BurgerMenu.BurgerMenuComponent;
import com.historycode.ui.page.BasePage;
import com.historycode.ui.page.streetcodes.CatalogComponent;
import com.historycode.ui.page.streetcodes.StreetCodesPage;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ChronologyTest extends BaseTestRunner {

    @Issue("91")
    @Test
    @Step("Open History-Code Page")
    public void testClickMenuItem() {
        BasePage basePage = new BasePage(driver) {
        };

        basePage.openBurgerMenu();
        WebElement burgerMenuRoot = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'drawerContainer')]")));
        BurgerMenuComponent burgerMenuComponent = new BurgerMenuComponent(driver, burgerMenuRoot);
        burgerMenuComponent.clickMenuItem("Головна");
        Assert.assertTrue(driver.getCurrentUrl().contains("/"), "Page did not navigate to 'Головна'!");
    }

    @Issue("91")
    @Test
    @Step("Navigate to Роман Рáтушний «Сенека» Page")
    public void testStreetCodesPageNavigation() {

        BasePage basePage = new BasePage(driver) {
        };

        basePage.openBurgerMenu();
        WebElement burgerMenuRoot = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'drawerContainer')]")));

        BurgerMenuComponent burgerMenuComponent = new BurgerMenuComponent(driver, burgerMenuRoot);
        burgerMenuComponent.clickMenuItem("History-коди");
        Assert.assertTrue(driver.getCurrentUrl().contains("/catalog"), "Page did not navigate to 'History-коди'!");

        StreetCodesPage streetCodesPage = new StreetCodesPage(driver);
        CatalogComponent catalogComponent = streetCodesPage.getStreetCodesCatalogComponent();
        Assert.assertTrue(catalogComponent.isCatalogElementVisible(0), "Catalog item 0 is not visible!");

        streetCodesPage.clickOnCatalogItem(0);
        Assert.assertTrue(driver.getCurrentUrl().contains("/roman-ratushnyi-seneka"), "Page did not navigate to 'Роман Рáтушний «Сенека»'!");

    }

    @Issue("91")
    @Test
    @Step("Navigate to Роман Chronology Block")
    public void testChronologyNavigation() {

    }
}


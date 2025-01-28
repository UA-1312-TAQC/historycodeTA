package com.historycode.ui;

import com.historycode.ui.page.homePage.HomePage;
import com.historycode.ui.page.streetCodePage.StreetCodePage;
import com.historycode.ui.page.streetcodecatalogpage.StreetCodeCatalogPage;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class StreetCodeWowFactsTest extends BaseTestRunner {

    private StreetCodePage streetPage;
    private SoftAssert softAssert;
    private Actions actions;
    private WebDriverWait wait;
    private StreetCodeCatalogPage streetCodeCatalogPage;


    @BeforeMethod
    public void navigateToStreetCodePage() {
        softAssert = new SoftAssert();
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        streetCodeCatalogPage = new StreetCodeCatalogPage(driver);

        driver.get(testValueProvider.getBaseUIUrl());

        HomePage homePage = new HomePage(driver);
        homePage.openBurgerMenu();
        homePage.getHistoryCodeBurgerButton().click();

        streetCodeCatalogPage.waitUntilElementVisible(streetCodeCatalogPage.getContainerRootNode());
        streetCodeCatalogPage.clickCatalogItemByIndex(0);

        streetPage = new StreetCodePage(driver);


    }


    @Issue("85")
    @Test
    @Description("Checks that hovering over the carousel card reveals a tooltip, which then disappears after 4 seconds, and verifies the same behavior on subsequent hovers.")
    public void testHoverDescriptionAppearsAndDisappears() {

        streetPage.waitUntilElementVisible(streetPage.getMainCard().getName());


        actions.moveToElement(streetPage.getFacts().getCarouselRoot()).perform();


        WebElement hintElement = streetPage.getFactsCard().getHoverDescription();


        streetPage.waitUntilElementVisible(hintElement);
        softAssert.assertTrue(hintElement.isDisplayed(), "Підказка не з'явилася");

        actions.moveToElement(streetPage.getFacts().getTitle()).perform();

        wait.until(ExpectedConditions.invisibilityOf(hintElement));
        softAssert.assertFalse(hintElement.isDisplayed(), "Підказка не зникла через 4 секунди.");


        actions.moveToElement(streetPage.getFactsCard().getCurrentImage()).perform();

        softAssert.assertTrue(hintElement.isDisplayed(), "Підказка не з'явилася після наведення.");

        actions.moveToElement(streetPage.getFacts().getTitle()).perform();

        softAssert.assertTrue(!hintElement.isDisplayed(), "Підказка не зникла.");
        softAssert.assertAll();

    }

}

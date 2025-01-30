package com.historycode.ui;


import com.historycode.ui.page.homePage.HomePage;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Objects;

public class SmokeTest extends BaseTestRunner {

    @Issue("91")
    @Test
    public void testOpenPage(){
        HomePage homePage = new HomePage(driver);
        homePage.getHeader().scrollToEndOfPage();

        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[1]/div[1]/div[1]")).isDisplayed());
    }

    @Test
    @Issue("78")
    @Epic("(Epic#1) StreetCode page")
    @Description("Verify that after clicking on the StreetCodes, the Catalog page with street codes will be displayed.")
    public void testOpenPreviousPage(){
        new HomePage(driver)
                .clickPersonCardCarouselItem(0)
                .getBreadcrumbs()
                .clickCatalog();
        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("/catalog"), "Navigation to catalog failed");
    }

    @Test
    public void testOpenPreviousPageBMenu(){
        new HomePage(driver).openBurgerMenu().goToStreetCodeCatalogPage();

        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("/catalog"), "Navigation to catalog failed");
    }
}

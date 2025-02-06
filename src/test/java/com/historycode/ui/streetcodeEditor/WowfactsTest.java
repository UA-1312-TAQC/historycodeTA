package com.historycode.ui.streetcodeEditor;

import com.historycode.ui.page.adminpanel.streetcodeeditpage.StreetcodeEditPage;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import io.qameta.allure.Issue;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class WowfactsTest extends TestRunnerWithAdmin {

    @Test
    @Issue("58")
    public void wowfactsEditorTest() {
        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel/new-streetcode");
        StreetcodeEditPage streetcodeEditPage = new StreetcodeEditPage(driver);
        streetcodeEditPage.waitForPageToLoad(5);
        streetcodeEditPage.scrollToElement(streetcodeEditPage.getAddWowfactButton());
        streetcodeEditPage.getAddWowfactButton().click();
        streetcodeEditPage.waitUntilElementVisible(streetcodeEditPage.getModalRootElement());
        streetcodeEditPage.getWowFactsModal().checkTitleOfModal("Wow-Факт");
        /* перевірка поля Title */
        streetcodeEditPage.getWowFactsModal().checkAttribute(streetcodeEditPage
                .getWowFactsModal().getTitle(), "aria-required", "true");
        streetcodeEditPage.getWowFactsModal().checkAttribute(streetcodeEditPage
                .getWowFactsModal().getTitle(), "maxlength", "68");
        streetcodeEditPage.getWowFactsModal().getTitle().sendKeys("Виступ в Києві");
        streetcodeEditPage.getWowFactsModal().isSymbolsLeftInTitle("14 / 68");
        /* перевірка поля FactContent */
        streetcodeEditPage.getWowFactsModal().checkAttribute(streetcodeEditPage
                .getWowFactsModal().getFactContent(), "aria-required", "true");
        streetcodeEditPage.getWowFactsModal().checkAttribute(streetcodeEditPage
                .getWowFactsModal().getFactContent(), "maxlength", "600");
        streetcodeEditPage.getWowFactsModal().getFactContent().sendKeys("Це було весною 1992.");
        streetcodeEditPage.getWowFactsModal().isSymbolsLeftInContent("20 / 600");
        /* перевірка завантаження фото */
        streetcodeEditPage.getWowFactsModal().checkAttribute(streetcodeEditPage
                .getWowFactsModal().getFileuploader(), "accept", ".jpeg,.png,.jpg,.webp");
        File file = new File("src/test/resources/uploadfiles/test.webp");
        String absolutePath = file.getAbsolutePath();
        streetcodeEditPage.getWowFactsModal().getFileuploader().sendKeys(absolutePath);
        Assert.assertTrue(streetcodeEditPage.getWowFactsModal().getUploadedImage().isDisplayed());
        Assert.assertFalse(streetcodeEditPage.getWowFactsModal().getFileuploader().isDisplayed());


        /* перевірка поля FactContent */
        streetcodeEditPage.getWowFactsModal().checkAttribute(streetcodeEditPage
                .getWowFactsModal().getImageDescription(), "maxlength", "200");
        streetcodeEditPage.getWowFactsModal().isAttributeAbsent(streetcodeEditPage
                .getWowFactsModal().getImageDescription(), "aria-required");
        streetcodeEditPage.getWowFactsModal().getImageDescription().sendKeys("Фото події");
        streetcodeEditPage.getWowFactsModal().isSymbolsLeftImgDesc("10 / 200");

        streetcodeEditPage.getWowFactsModal().clickSaveButton();
        Assert.assertTrue(streetcodeEditPage.isElementInvisible(streetcodeEditPage.getModalRootElement()));

        List<String> expectedWowFacts = Arrays.asList(
                "Виступ в Києві"
        );
        Assert.assertTrue(streetcodeEditPage.checkAllWowFacts(expectedWowFacts));

        /* перевірка редагування в модалці*/
        streetcodeEditPage.clickWowfactEditButton(1);
        streetcodeEditPage.waitUntilElementVisible(streetcodeEditPage.getModalRootElement());
        streetcodeEditPage.getWowFactsModal().checkTitleOfModal("Wow-Факт");
        streetcodeEditPage.getWowFactsModal().getTitle().getText().equals("Виступ в Києві");
        streetcodeEditPage.getWowFactsModal().getFactContent().getText().equals("Це було весною 1992.");
        Assert.assertTrue(streetcodeEditPage.getWowFactsModal().getUploadedImage().isDisplayed());
        Assert.assertFalse(streetcodeEditPage.getWowFactsModal().getFileuploader().isDisplayed());
        streetcodeEditPage.getWowFactsModal().getImageDescription().getText().equals("Фото події");

        WebElement titleElement = streetcodeEditPage.getWowFactsModal().getTitle();
        titleElement.click(); // Клік на елемент
        titleElement.sendKeys(Keys.CONTROL + "a");
        titleElement.sendKeys(Keys.BACK_SPACE);
        streetcodeEditPage.getWowFactsModal().getTitle().sendKeys("Виступ в Житомирі");

        streetcodeEditPage.getWowFactsModal().clickSaveButton();
        Assert.assertTrue(streetcodeEditPage.isElementInvisible(streetcodeEditPage.getModalRootElement()));

        List<String> newexpectedWowFacts = Arrays.asList(
                "Виступ в Житомирі"
        );
        Assert.assertTrue(streetcodeEditPage.checkAllWowFacts(newexpectedWowFacts));

        /* перевірка видалення */
        streetcodeEditPage.clickWowfactDeleteButton(1);
        streetcodeEditPage.waitUntilElementVisible(streetcodeEditPage.getModalRootElement());
        streetcodeEditPage.getDeleteItemModal().clickOkButton();
        streetcodeEditPage.isElementInvisible(streetcodeEditPage.getModalRootElement());
        List<String> new2expectedWowFacts = Arrays.asList(
        ); // список пустий, бо елемент видалено
        Assert.assertTrue(streetcodeEditPage.checkAllWowFacts(new2expectedWowFacts));
    }
}

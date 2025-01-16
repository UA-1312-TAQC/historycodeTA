package com.historycode.ui;

import com.historycode.ui.page.streetcodespage.CatalogItemComponent;
import com.historycode.ui.page.streetcodespage.StreetCodesPage;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

public class StreetCodesCssTest extends BaseTestRunner {

    private StreetCodesPage streetCodesPage;

    private static final Map<String, String> EXPECTED_TITLE_CSS = Map.of(
            "color", "rgba(221, 221, 221, 1)",
            "font-family", "\"Closer Text\", Roboto, \"Helvetica Neue\", sans-serif",
            "font-size", "96px"
    );

    private static final Map<String, String> EXPECTED_NAME_CSS = Map.of(
            "color", "rgba(255, 255, 255, 1)",
            "font-family", "\"Closer Text\", Roboto, \"Helvetica Neue\", sans-serif",
            "font-size", "20px"
    );

    private static final Map<String, String> EXPECTED_DESCRIPTION_CSS = Map.of(
            "color", "rgba(255, 255, 255, 1)",
            "font-family", "\"Closer Text\", Roboto, \"Helvetica Neue\", sans-serif",
            "font-size", "14px"
    );

    private static final Map<String, String> EXPECTED_CARD_BG_CSS = Map.of(
            "background-color", "rgba(137, 31, 22, 1)"
    );

    @BeforeMethod
    public void openStreetCodesPage() {
        driver.get(testValueProvider.getBaseUIUrl() + "catalog");
        streetCodesPage = new StreetCodesPage(driver);
    }

    @Issue("67")
    @Test(description = "Verify the StreetCodes page title CSS.")
    public void testStreetCodesPageTitle() {
        SoftAssert softAssert = new SoftAssert();
        WebElement titleElement = streetCodesPage.getStreetCodesTitleElement();

        verifyCssProperties(
                softAssert,
                titleElement,
                EXPECTED_TITLE_CSS,
                "StreetCodes Page Title"
        );

        softAssert.assertAll();
    }
    @Issue("67")
    @Test(description = "Verify that each catalog item has correct font-size, color, and background color.")
    public void testCatalogItems() {
        SoftAssert softAssert = new SoftAssert();

        streetCodesPage
                .ScrollDownStreetCodes();

        List<CatalogItemComponent> items = streetCodesPage
                .getStreetCodesCatalogComponent()
                .getItemComponents();

        for (int i = 0; i < items.size(); i++) {
            CatalogItemComponent item = items.get(i);
            checkCatalogItem(softAssert, item, i);
        }

        softAssert.assertAll();
    }

    @Step("Check catalog item #{index}")
    private void checkCatalogItem(SoftAssert softAssert, CatalogItemComponent item, int index) {
        verifyCssProperties(
                softAssert,
                item.getNameNode(),
                EXPECTED_NAME_CSS,
                String.format("Item [%d] Name", index)
        );

        WebElement descriptionNode = item.getDescriptionNode();
        if (descriptionNode != null) {
            verifyCssProperties(
                    softAssert,
                    descriptionNode,
                    EXPECTED_DESCRIPTION_CSS,
                    String.format("Item [%d] Description", index)
            );
        }

        verifyCssProperties(
                softAssert,
                item.getCatalogItemTextArea(),
                EXPECTED_CARD_BG_CSS,
                String.format("Item [%d] Card Background", index)
        );
    }

    @Step("Verify multiple CSS properties for {elementDescription}")
    private void verifyCssProperties(SoftAssert softAssert,
                                     WebElement element,
                                     Map<String, String> expectedCss,
                                     String elementDescription) {
        for (Map.Entry<String, String> entry : expectedCss.entrySet()) {
            String property = entry.getKey();
            String expectedValue = entry.getValue();
            String actualValue = element.getCssValue(property);

            softAssert.assertEquals(
                    actualValue,
                    expectedValue,
                    elementDescription + String.format(
                            " - %s mismatch. Expected '%s', got '%s'",
                            property, expectedValue, actualValue
                    )
            );
        }
    }
}

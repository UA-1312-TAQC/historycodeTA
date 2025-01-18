package com.historycode.ui;

import com.historycode.ui.page.streetcodespage.CatalogItemComponent;
import com.historycode.ui.page.streetcodespage.StreetCodesPage;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

@Slf4j
public class StreetCodesCssTest extends BaseTestRunner {

    private StreetCodesPage streetCodesPage;

    private static final Map<String, String> TITLE_CSS_EXPECTATIONS = Map.of(
            "color", "rgba(221, 221, 221, 1)",
            "font-family", "\"Closer Text\", Roboto, \"Helvetica Neue\", sans-serif",
            "font-size", "96px"
    );

    private static final Map<String, String> NAME_CSS_EXPECTATIONS = Map.of(
            "color", "rgba(255, 255, 255, 1)",
            "font-family", "\"Closer Text\", Roboto, \"Helvetica Neue\", sans-serif",
            "font-size", "20px"
    );

    private static final Map<String, String> DESCRIPTION_CSS_EXPECTATIONS = Map.of(
            "color", "rgba(255, 255, 255, 1)",
            "font-family", "\"Closer Text\", Roboto, \"Helvetica Neue\", sans-serif",
            "font-size", "14px"
    );

    private static final Map<String, String> CARD_BG_CSS_EXPECTATIONS = Map.of(
            "background-color", "rgba(137, 31, 22, 1)"
    );

    @BeforeMethod
    public void openStreetCodesPage() {
        driver.get(testValueProvider.getBaseUIUrl() + "/catalog");
        streetCodesPage = new StreetCodesPage(driver);
    }

    @Test
    @Issue("#67")
    @Step("Verify the visual consistency of the StreetCode page with the design mockup")
    public void verifyStreetCodesPageVisualConsistency() {
        SoftAssert softAssert = new SoftAssert();

        log.info("Verifying CSS properties for the title element");
        verifyCssProperties(softAssert, streetCodesPage.getStreetCodesTitleElement(), TITLE_CSS_EXPECTATIONS, "Title");

        streetCodesPage
                .scrollDownStreetCodes();

        List<CatalogItemComponent> catalogItems = streetCodesPage
                .getStreetCodesCatalogComponent()
                .getItemComponents();

        for (int i = 0; i < catalogItems.size(); i++) {
            CatalogItemComponent item = catalogItems.get(i);

            log.info("Verifying catalog item at index {}", i);
            log.debug("Catalog item details: Name='{}', Description='{}'",
                    item.getName(),
                    item.hasDescriptionNode() ? item.getDescription() : "No description");

            verifyCssProperties(softAssert, item.getNameNode(), NAME_CSS_EXPECTATIONS, "Name for item " + i);

            if (item.hasDescriptionNode()) {
                verifyCssProperties(softAssert, item.getDescriptionNode(), DESCRIPTION_CSS_EXPECTATIONS, "Description for item " + i);
            } else {
                log.debug("DescriptionNode not found for item {}, skipping verification.", i);
            }

            verifyCssProperties(softAssert, item.getCatalogItemTextArea(), CARD_BG_CSS_EXPECTATIONS, "Background for item " + i);
        }

        softAssert.assertAll();
    }


    private void verifyCssProperties(SoftAssert softAssert, WebElement element, Map<String, String> expectedCss, String elementName) {
        expectedCss.forEach((property, expectedValue) -> {
            String actualValue = element.getCssValue(property);
            log.info("Verifying CSS property '{}' for element '{}': Expected='{}', Actual='{}'",
                    property, elementName, expectedValue, actualValue);
            softAssert.assertEquals(
                    actualValue,
                    expectedValue,
                    String.format("%s %s mismatch", elementName, property)
            );
        });
    }

}

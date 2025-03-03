package com.historycode.cucumber.steps;

import com.historycode.ui.page.streetcodecatalogpage.CatalogItemComponent;
import com.historycode.ui.page.streetcodecatalogpage.StreetCodeCatalogPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

@Slf4j
public class StreetCodesVisualSteps extends BaseStep {

    private StreetCodeCatalogPage streetCodesPage;
    private SoftAssert softAssert;

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

    public StreetCodesVisualSteps() {
        this.streetCodesPage = new StreetCodeCatalogPage(driver);
        this.softAssert = new SoftAssert();
    }

    @Given("I open the StreetCodes catalog page")
    public void openStreetCodesPage() {
        log.info("Opening StreetCodes catalog page");
        driver.get(provider.getBaseUIUrl() + "/catalog");
    }

    @When("I check the title CSS properties")
    public void verifyTitleCss() {
        log.info("Verifying CSS properties for the title element");
        WebElement titleElement = streetCodesPage.getStreetCodesTitle();

        if (titleElement != null) {
            verifyCssProperties(titleElement, TITLE_CSS_EXPECTATIONS, "Title");
        } else {
            log.warn("Title element not found on the page");
        }
    }

    @When("I scroll to the end of the page")
    public void scrollToEndOfPage() {
        log.info("Scrolling to the end of the page");
        streetCodesPage.scrollToEndOfPage();
    }

    @Then("all catalog items should have correct CSS properties")
    public void verifyCatalogItemsCss() {
        log.info("Verifying CSS properties for all catalog items");

        List<CatalogItemComponent> catalogItems = streetCodesPage.getStreetCodesCatalogComponent().getItemComponents();
        if (catalogItems.isEmpty()) {
            log.warn("No catalog items found on the page");
        }

        for (int i = 0; i < catalogItems.size(); i++) {
            CatalogItemComponent item = catalogItems.get(i);
            log.info("Verifying catalog item at index {}", i);

            if (item.getNameNode() != null) {
                verifyCssProperties(item.getNameNode(), NAME_CSS_EXPECTATIONS, "Name for item " + i);
            } else {
                log.warn("Name node not found for item {}", i);
            }

            if (item.hasDescriptionNode() && item.getDescriptionNode() != null) {
                verifyCssProperties(item.getDescriptionNode(), DESCRIPTION_CSS_EXPECTATIONS, "Description for item " + i);
            } else {
                log.debug("DescriptionNode not found for item {}, skipping verification.", i);
            }

            if (item.getCatalogItemTextArea() != null) {
                verifyCssProperties(item.getCatalogItemTextArea(), CARD_BG_CSS_EXPECTATIONS, "Background for item " + i);
            } else {
                log.warn("Background node not found for item {}", i);
            }
        }

        softAssert.assertAll();
    }

    private void verifyCssProperties(WebElement element, Map<String, String> expectedCss, String elementName) {
        expectedCss.forEach((property, expectedValue) -> {
            String actualValue = element.getCssValue(property);
            log.info("Verifying CSS property '{}' for element '{}': Expected='{}', Actual='{}'",
                    property, elementName, expectedValue, actualValue);
            softAssert.assertEquals(actualValue, expectedValue, String.format("%s %s mismatch", elementName, property));
        });
    }
}

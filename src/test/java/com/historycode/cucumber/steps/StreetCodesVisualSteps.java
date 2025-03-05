package com.historycode.cucumber.steps;

import com.historycode.ui.page.streetcodecatalogpage.CatalogItemComponent;
import com.historycode.ui.page.streetcodecatalogpage.StreetCodeCatalogPage;
import io.cucumber.datatable.DataTable;
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
    private final SoftAssert softAssert = new SoftAssert();

    @Given("I open the StreetCodes catalog page")
    public void openStreetCodesPage() {
        initDriver();
        log.info("Opening StreetCodes catalog page");
        driver.get(provider.getBaseUIUrl() + "/catalog");
        streetCodesPage = new StreetCodeCatalogPage(driver);
    }

    @When("I check the title CSS properties")
    public void verifyTitleCss(DataTable table) {
        log.info("Verifying CSS properties for the title element");
        WebElement titleElement = streetCodesPage.getStreetCodesTitle();

        if (titleElement == null) {
            log.warn("Title element not found on the page");
            softAssert.fail("Title element not found on the page");
            return;
        }

        Map<String, String> expectedCss = table.asMap(String.class, String.class);
        verifyCssProperties(titleElement, expectedCss, "Title");
    }

    @When("I scroll to the end of the page")
    public void scrollToEndOfPage() {
        log.info("Scrolling to the end of the page");
        streetCodesPage.scrollToEndOfPage();
    }

    @Then("all catalog items should have correct CSS properties")
    public void verifyCatalogItemsCss(DataTable table) {
        log.info("Verifying CSS properties for all catalog items");

        List<CatalogItemComponent> catalogItems = streetCodesPage.getStreetCodesCatalogComponent().getItemComponents();
        if (catalogItems.isEmpty()) {
            log.warn("No catalog items found on the page");
            softAssert.fail("No catalog items found on the page");
            return;
        }

        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        for (int i = 0; i < catalogItems.size(); i++) {
            CatalogItemComponent item = catalogItems.get(i);
            log.info("Verifying catalog item at index {}", i);

            for (Map<String, String> row : rows) {
                String itemType = row.get("itemType");
                String property = row.get("property");
                String expectedValue = row.get("expectedValue");

                WebElement elementToCheck = switch (itemType) {
                    case "Name" -> item.getNameNode();
                    case "Description" -> item.hasDescriptionNode() ? item.getDescriptionNode() : null;
                    case "Card" -> item.getCatalogItemTextArea();
                    default -> null;
                };

                if (elementToCheck != null) {
                    verifyCssProperties(elementToCheck, Map.of(property, expectedValue), itemType + " for item " + i);
                } else {
                    log.warn("{} node not found for item {}", itemType, i);
                    softAssert.fail(itemType + " node not found for item " + i);
                }
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

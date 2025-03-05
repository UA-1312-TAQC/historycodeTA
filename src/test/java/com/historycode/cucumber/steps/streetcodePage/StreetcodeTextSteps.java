package com.historycode.cucumber.steps.streetcodePage;

import com.historycode.cucumber.steps.BaseStep;
import com.historycode.ui.page.streetCodePage.StreetCodePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class StreetcodeTextSteps extends BaseStep {
    private StreetCodePage streetCodePage;
    private int initialParagraphCount;

    @When("I click on the {string} button")
    public void iClickOnTheButton(String buttonName) {
        streetCodePage = new StreetCodePage(driver);
        switch (buttonName) {
            case "Трохи ще":
                streetCodePage.getTextBlock().clickReadMoreButton();
                break;
            case "Дещо менше":
                streetCodePage.getTextBlock().clickReadLessButton();
                break;
            default:
                throw new IllegalArgumentException("Unsupported button name: " + buttonName);
        }
    }

    @Then("all available text should be visible")
    public void allAvailableTextShouldBeVisible() {
        initialParagraphCount = streetCodePage.getTextBlock().getParagraphCount();
        Assert.assertTrue(streetCodePage.getTextBlock().checkExpanded(initialParagraphCount), "Text should be fully expanded but it is not.");
    }

    @And("the {string} button should be present")
    public void theButtonShouldBePresent(String buttonName) {
        boolean isDisplayed = switch (buttonName) {
            case "Трохи ще" -> streetCodePage.getTextBlock().isReadMoreButtonDisplayed();
            case "Дещо менше" -> streetCodePage.getTextBlock().isReadLessButtonDisplayed();
            default -> throw new IllegalArgumentException("Unsupported button name: " + buttonName);
        };
        Assert.assertTrue(isDisplayed, buttonName + " button should be displayed but is not.");
    }

    @Then("the text should collapse \\(fit on one screen)")
    public void theTextShouldCollapseFitOnOneScreen() {
        Assert.assertTrue(streetCodePage.getTextBlock().checkCollapsed(initialParagraphCount), "Text should be collapsed but it is still expanded.");
    }
}

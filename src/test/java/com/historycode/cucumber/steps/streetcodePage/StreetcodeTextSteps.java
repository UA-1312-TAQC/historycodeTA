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

    @Given("the StreetCode page is opened")
    public void theStreetCodePageIsOpen() {
        initDriver();
        driver.get(provider.getBaseUIUrl() + "/roman-ratushnyi-seneka");
        streetCodePage = new StreetCodePage(driver);
        sleep(5);
    }

    @When("I click on the {string} button")
    public void iClickOnTheButton(String buttonName) {
        switch (buttonName) {
            case "Трохи ще" -> streetCodePage.getTextBlock().clickReadMoreButton();
            case "Дещо менше" -> streetCodePage.getTextBlock().clickReadLessButton();
        }
    }

    @Then("all available text should be visible")
    public void allAvailableTextShouldBeVisible() {
        initialParagraphCount = streetCodePage.getTextBlock().getParagraphCount();
        Assert.assertTrue(streetCodePage.getTextBlock().checkExpanded(initialParagraphCount));
    }

    @And("the {string} button should be present")
    public void theButtonShouldBePresent(String buttonName) {
        switch (buttonName) {
            case "Трохи ще" -> Assert.assertTrue(streetCodePage.getTextBlock().isReadMoreButtonDisplayed());
            case "Дещо менше" -> Assert.assertTrue(streetCodePage.getTextBlock().isReadLessButtonDisplayed());
        }
    }

    @Then("the text should collapse \\(fit on one screen)")
    public void theTextShouldCollapseFitOnOneScreen() {
        Assert.assertTrue(streetCodePage.getTextBlock().checkCollapsed(initialParagraphCount));
    }
}

package com.historycode.cucumber.steps.streetcodePage;

import com.historycode.cucumber.steps.BaseStep;
import com.historycode.ui.page.streetCodePage.StreetCodePage;
import com.historycode.ui.page.streetCodePage.components.StreetCodeTextBlockComponent;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class StreetcodeSourceLinkSteps extends BaseStep {
    private StreetCodePage streetCodePage;
    private String expectedUrl;


    @When("I click on the source link under the text")
    public void iClickOnTheSourceLinkUnderTheText() {
        StreetCodeTextBlockComponent textBlock = streetCodePage.getTextBlock();
        String link = textBlock.getLinksInNewsContent().getFirst();
        expectedUrl = link;
        driver.get(link);
    }

    @Then("I should be redirected to the source page")
    public void iShouldBeRedirectedToTheSourcePage() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, expectedUrl, "Redirection failed or URL mismatch for link: " + expectedUrl);
    }
}

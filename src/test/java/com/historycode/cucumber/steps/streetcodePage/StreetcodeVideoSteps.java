package com.historycode.cucumber.steps.streetcodePage;

import com.historycode.cucumber.steps.BaseStep;
import com.historycode.ui.page.streetCodePage.StreetCodePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class StreetcodeVideoSteps extends BaseStep {
    private StreetCodePage streetCodePage;

    @When("I click on the play button")
    public void iClickOnTheVideo() {
        streetCodePage = new StreetCodePage(driver);
        streetCodePage.getVideoBlock().clickPlayButton();
    }

    @And("the video is playing")
    public void theVideoIsPlaying() {
        Assert.assertTrue(streetCodePage.getVideoBlock().isActionPauseButtonVisible(), "Video is not playing");
    }


    @Then("I click on the pause button")
    public void iClickOnThePauseButton() {
        streetCodePage.getVideoBlock().clickPauseButton();
    }

    @And("the video is paused")
    public void theVideoIsPaused() {
        Assert.assertTrue(streetCodePage.getVideoBlock().isActionPlayButtonVisible(), "Video is not paused");
    }
}

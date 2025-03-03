package com.historycode.cucumber.steps.streetcodePage;

import com.historycode.ui.page.streetCodePage.StreetCodePage;
import com.historycode.ui.page.streetCodePage.components.InterestingFactsComponent;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class StreetcodeWowFactCarouselSteps {
    private StreetCodePage streetCodePage;
    private InterestingFactsComponent interestingFactsComponent;
    private SoftAssert softAssert = new SoftAssert();
    private int cardExpectedIndex;
    private int squareExpectedIndex;
    private int cardIndexAfterClick;
    private int squareIndexAfterClick;

    @When("I click on the {string} card (on the left side from the one that is in the front)")
    public void iClickOnThePreviousCardOnTheLeftSideFromTheOneThatIsInTheFront() {
        interestingFactsComponent = streetCodePage.getFacts();

        cardExpectedIndex = interestingFactsComponent.getPreviousCardIndex();
        interestingFactsComponent.clickPreviousCard();
        cardIndexAfterClick = interestingFactsComponent.getCurrentCardIndex();
    }

    @Then("the clicked card should move to the front")
    public void theClickedCardShouldMoveToTheFront() {
        softAssert.assertEquals(cardIndexAfterClick, cardExpectedIndex, "Card is not changing.");
        softAssert.assertAll();
    }

    @When("I click on the right arrow")
    public void iClickOnTheRightArrow() {
        interestingFactsComponent = streetCodePage.getFacts();
        interestingFactsComponent.waitUntilPageLouder();
        streetCodePage.scrollToWowFactCarousel();

        cardExpectedIndex = interestingFactsComponent.getNextCardIndex();
        squareExpectedIndex = interestingFactsComponent.getNextWowFactsSquareIndex();

        interestingFactsComponent.clickNextSlide();
    }

    @Then("the cards should change along with the squares under the cards")
    public void theCardsShouldChangeAlongWithTheSquaresUnderTheCards() {
        squareIndexAfterClick = interestingFactsComponent.getActiveWowFactsSquareIndex();
        cardIndexAfterClick = interestingFactsComponent.getCurrentCardIndex();

        softAssert.assertEquals(cardIndexAfterClick, cardExpectedIndex, "Card is not changing.");
        softAssert.assertEquals(squareExpectedIndex, squareIndexAfterClick, "Square is not changing.");

        softAssert.assertAll();
    }

    @When("I click on the left arrow")
    public void iClickOnTheLeftArrow() {
        interestingFactsComponent = streetCodePage.getFacts();
        interestingFactsComponent.waitUntilPageLouder();
        streetCodePage.scrollToWowFactCarousel();

        squareExpectedIndex = interestingFactsComponent.getPreviousSquareIndex();
        cardExpectedIndex = interestingFactsComponent.getPreviousCardIndex();

        interestingFactsComponent.clickPreviousSlide();
    }

    @When("I click on any navigation indicator under the cards")
    public void iClickOnAnyNavigationIndicatorUnderTheCards() {
        interestingFactsComponent = streetCodePage.getFacts();
        interestingFactsComponent.waitUntilPageLouder();
        streetCodePage.scrollToWowFactSquare();

        int cardIndexBeforeClick = interestingFactsComponent.getCurrentCardIndex();

        interestingFactsComponent.clickRandomWowFactsSquare();

        cardIndexAfterClick = interestingFactsComponent.getCurrentCardIndex();

        Assert.assertNotEquals(cardIndexBeforeClick, cardIndexAfterClick, "Card is not changing.");
    }

}

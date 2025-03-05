package com.historycode.cucumber.steps.streetcodePage;

import com.historycode.cucumber.steps.BaseStep;
import com.historycode.ui.page.streetCodePage.StreetCodePage;
import com.historycode.ui.page.streetCodePage.components.InterestingFactsComponent;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

public class StreetcodeWowFactCarouselSteps extends BaseStep {
    private StreetCodePage streetCodePage;
    private InterestingFactsComponent interestingFactsComponent;
    private SoftAssert softAssert = new SoftAssert();
    private int cardExpectedIndex;
    private int squareExpectedIndex;
    private int cardIndexAfterClick;
    private int squareIndexAfterClick;

    private int cardIndexBeforeClick;

    // TODO: method setUp does not work if we use the @Before annotation,
    // so I changed the annotation to @BeforeClass
    @BeforeClass
    public void setUp() {
        streetCodePage = new StreetCodePage(driver);
        interestingFactsComponent = streetCodePage.getFacts();
    }

    @When("I click on the previous card \\(on the left side from the one that is in the front)")
    public void iClickOnThePreviousCardOnTheLeftSideFromTheOneThatIsInTheFront() {
        streetCodePage.scrollToWowFactCarousel();

        cardExpectedIndex = interestingFactsComponent.getPreviousCardIndex();
        interestingFactsComponent.clickPreviousCard();
        cardIndexAfterClick = interestingFactsComponent.getCurrentCardIndex();
    }

    @Then("the clicked card should move to the front")
    public void theClickedCardShouldMoveToTheFront() {
        softAssert.assertEquals(cardIndexAfterClick, cardExpectedIndex, "Card is not changing.");
    }

    @When("I click on the right arrow")
    public void iClickOnTheRightArrow() {
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
    }

    @When("I click on the left arrow")
    public void iClickOnTheLeftArrow() {
        squareExpectedIndex = interestingFactsComponent.getPreviousSquareIndex();
        cardExpectedIndex = interestingFactsComponent.getPreviousCardIndex();

        interestingFactsComponent.clickPreviousSlide();
    }

    @When("I click on any navigation indicator under the cards")
    public void iClickOnAnyNavigationIndicatorUnderTheCards() {
        cardIndexBeforeClick = interestingFactsComponent.getCurrentCardIndex();

        interestingFactsComponent.clickRandomWowFactsSquare();
    }

    @Then("the cards should change")
    public void theCardsShouldChange() {
        cardIndexAfterClick = interestingFactsComponent.getCurrentCardIndex();

        Assert.assertNotEquals(cardIndexBeforeClick, cardIndexAfterClick,
                "Expected the card to change after clicking the navigation indicator, but it didn't.");
    }

}

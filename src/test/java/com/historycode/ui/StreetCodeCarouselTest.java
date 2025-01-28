package com.historycode.ui;

import com.historycode.ui.data_provider.StreetCodeDP;
import com.historycode.ui.page.streetCodePage.StreetCodePage;
import com.historycode.ui.page.streetCodePage.components.InterestingFactsComponent;
import com.historycode.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class StreetCodeCarouselTest extends BaseTestRunner {
    private StreetCodePage streetCodePage;
    private SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        softAssert = new SoftAssert();
    }

    @Step("Navigate to the 'StreetCode' page")
    private void navigateToStreetCodePage(String addUIUrl) {
        driver.navigate().to(testValueProvider.getBaseUIUrl() + addUIUrl);
        streetCodePage = new StreetCodePage(driver);
    }

    @Issue("89")
    @Test(dataProvider = "urlProviderForTextBlock", dataProviderClass = StreetCodeDP.class)
    @Description("Click on any navigation indicator under cards, verify that cards are changing.")
    public void testStreetCodeClickPreviousCard(String addUIUrl) {
        navigateToStreetCodePage(addUIUrl);

        InterestingFactsComponent interestingFactsComponent = streetCodePage.getFacts();
        interestingFactsComponent.waitUntilPageLouder();
        streetCodePage.scrollToElement(interestingFactsComponent.getCarousel().getActiveWowFactsSlickSquare());

        int cardIndexBeforeClick = interestingFactsComponent.getCurrentCardIndex();

        interestingFactsComponent.clickPreviousCard();

        int cardIndexAfterClick = interestingFactsComponent.getCurrentCardIndex();

        softAssert.assertNotEquals(cardIndexBeforeClick,cardIndexAfterClick);
        softAssert.assertAll();
    }

    @Issue("89")
    @Test(dataProvider = "urlProviderForTextBlock", dataProviderClass = StreetCodeDP.class)
    @Description("Click on right arrow and verify that the cards are changing along with squares under the cards")
    public void testStreetCodeClickRightArrow(String addUIUrl) {
        navigateToStreetCodePage(addUIUrl);

        InterestingFactsComponent interestingFactsComponent = streetCodePage.getFacts();
        interestingFactsComponent.waitUntilPageLouder();
        streetCodePage.scrollToElement(interestingFactsComponent.getCarouselRoot());

        int cardIndexBeforeClick = interestingFactsComponent.getCurrentCardIndex();
        int cardExpectedIndex = cardIndexBeforeClick + 1;

        int dotIndexBeforeClick = interestingFactsComponent.getActiveWowFactsSquareIndex();
        int dotExpectedIndex = dotIndexBeforeClick + 1;

        interestingFactsComponent.clickNextSlide();
        int dotIndexAfterClick = interestingFactsComponent.getActiveWowFactsSquareIndex();
        int cardIndexAfterClick = interestingFactsComponent.getCurrentCardIndex();

        softAssert.assertEquals(cardIndexAfterClick, cardExpectedIndex, "Card is not changing.");
        softAssert.assertEquals(dotExpectedIndex, dotIndexAfterClick, "Square is not changing.");

        softAssert.assertAll();
    }


    @Issue("89")
    @Test(dataProvider = "urlProviderForTextBlock", dataProviderClass = StreetCodeDP.class)
    @Description("Click on left arrow and verify that the cards are changing along with squares under the cards")
    public void testStreetCodeClickLeftArrow(String addUIUrl) {
        navigateToStreetCodePage(addUIUrl);

        InterestingFactsComponent interestingFactsComponent = streetCodePage.getFacts();
        interestingFactsComponent.waitUntilPageLouder();
        streetCodePage.scrollToElement(interestingFactsComponent.getCarouselRoot());

        int dotExpectedIndex = interestingFactsComponent.getLastWowFactsSquareIndex();

        int cardExpectedIndex = interestingFactsComponent.getLastSlideIndex();

        interestingFactsComponent.clickPreviousSlide();
        interestingFactsComponent.sleep(2000);

        int dotIndexAfterClick = interestingFactsComponent.getActiveWowFactsSquareIndex();
        int cardIndexAfterClick = interestingFactsComponent.getCurrentCardIndex();

        softAssert.assertEquals(cardIndexAfterClick, cardExpectedIndex, "Card is not changing.");
        softAssert.assertEquals(dotExpectedIndex, dotIndexAfterClick, "Square is not changing.");

        softAssert.assertAll();
    }


    @Issue("89")
    @Test(dataProvider = "urlProviderForTextBlock", dataProviderClass = StreetCodeDP.class)
    @Description("Click on any navigation indicator under cards, verify that cards are changing.")
    public void testStreetCodeClickRandomDot(String addUIUrl) {
        navigateToStreetCodePage(addUIUrl);

        InterestingFactsComponent interestingFactsComponent = streetCodePage.getFacts();
        interestingFactsComponent.waitUntilPageLouder();
        streetCodePage.scrollToElement(interestingFactsComponent.getCarousel().getActiveWowFactsSlickSquare());

        int cardIndexBeforeClick = interestingFactsComponent.getCurrentCardIndex();

        interestingFactsComponent.clickRandomWowFactsSquare();

        int cardIndexAfterClick = interestingFactsComponent.getCurrentCardIndex();

        softAssert.assertNotEquals(cardIndexBeforeClick, cardIndexAfterClick, "Card is not changing.");

        softAssert.assertAll();
    }

}

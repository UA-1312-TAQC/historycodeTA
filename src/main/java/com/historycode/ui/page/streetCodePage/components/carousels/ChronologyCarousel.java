//package com.historycode.ui.page.streetCodePage.components.carousels;
//
//import com.historycode.ui.component.BaseComponent;
//import com.historycode.ui.page.streetCodePage.components.ChronologyCardComponent;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class ChronologyCarousel extends BaseComponent {
//
//    @FindBy(xpath = ".//div[@class='timelineItem']")
//    private List<WebElement> cardNodes;
//
//
//
//    private List<ChronologyCardComponent> cards;
//
//    public ChronologyCarousel(WebDriver driver, WebElement rootElement) {
//        super(driver, rootElement);
//        this.cards = cardNodes.stream()
//                .map(node -> new ChronologyCardComponent(driver, node))
//                .collect(Collectors.toList());
//    }
//
/// /    public void scrollToNext() {
/// /
/// /    }
/// /
/// /    public void scrollToPrevious() {
/// /
/// /    }
//
//    public List<ChronologyCardComponent> getVisibleCards() {
//        return cards.stream()
//                .filter(ChronologyCardComponent::isDisplayed)
//                .collect(Collectors.toList());
//    }
//
//    public int getTotalCards() {
//        return cards.size();
//    }
//}

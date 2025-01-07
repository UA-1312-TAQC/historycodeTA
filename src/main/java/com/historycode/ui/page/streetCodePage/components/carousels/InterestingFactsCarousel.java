package com.historycode.ui.page.streetCodePage.components.carousels;

import com.historycode.ui.page.streetCodePage.components.InterestingFactsCardComponent;
import com.historycode.ui.page.streetCodePage.components.PaginationComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class InterestingFactsCarousel extends BaseCarousel {
    @FindBy(xpath = ".//div[@class='interestingFactSlide']")
    private List<WebElement> factCardNodes;

    private List<InterestingFactsCardComponent> factCards;

    public InterestingFactsCarousel(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.factCards = factCardNodes.stream()
                .map(node -> new InterestingFactsCardComponent(driver, node))
                .collect(Collectors.toList());
        initializePagination(driver);
    }

    public List<InterestingFactsCardComponent> getCards() {
        return factCards;
    }
}

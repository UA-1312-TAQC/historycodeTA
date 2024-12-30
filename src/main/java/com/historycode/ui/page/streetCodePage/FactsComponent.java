package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class FactsComponent extends BaseComponent {
    @FindBy(xpath = ".//h1[@class='blockHeadingText']")
    private WebElement title;

    @FindBy(xpath = ".//div[@class='interestingFactSlide']")
    private List<WebElement> factCardNodes;

    @FindBy(xpath = ".//ul[@class='slick-dots']")
    private WebElement paginationNode;
    private List<FactCardComponent> factCards;
    private PaginationComponent pagination;
    private FactCardModal factCardModal;

    public FactsComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.factCards = factCardNodes.stream()
                .map(node -> new FactCardComponent(driver, node))
                .collect(Collectors.toList());
        this.pagination = new PaginationComponent(driver, paginationNode);
        this.factCardModal = new FactCardModal(driver, rootElement);
    }

    public String getTitle() {
        return title.getText();
    }

    public FactCardComponent getCurrentFact() {
        int currentIndex = pagination.getActiveDotIndex();
        return factCards.get(currentIndex);
    }

    public List<FactCardComponent> getAllFacts() {
        return factCards;
    }

    public int getTotalFacts() {
        return factCards.size();
    }
}

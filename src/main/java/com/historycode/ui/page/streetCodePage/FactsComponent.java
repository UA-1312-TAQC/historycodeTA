package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class FactsComponent extends BaseComponent {
    @FindBy(xpath = ".//h2")
    private WebElement title;

    @FindBy(xpath = ".//div[@class='fact-card']")
    private List<WebElement> factCardNodes;

    @FindBy(xpath = ".//div[@class='pagination']")
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
}

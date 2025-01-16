package com.historycode.ui.page.streetCodePage.modals;

import com.historycode.ui.component.BaseModal;
import com.historycode.ui.page.streetCodePage.components.RelatedPersonasCardComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class RelatedPersonasModal extends BaseModal {
    @FindBy(xpath = ".//div[@class='ant-modal css-k7429z relatedFiguresModal']")
    private WebElement modalRoot;

    @FindBy(xpath = ".//div[@class='relatedFiguresReadMoreContentContainer']/div/a")
    private List<WebElement> personCardNodes;

    @FindBy(xpath = ".//div[@class='ant-modal css-k7429z relatedFiguresModal']//button")
    private WebElement closeButton;

    private final List<RelatedPersonasCardComponent> personCards;

    public RelatedPersonasModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.personCards = personCardNodes.stream()
                .map(node -> new RelatedPersonasCardComponent(driver, node))
                .collect(Collectors.toList());
    }

    public boolean isDisplayed() {
        return modalRoot.isDisplayed();
    }

    public void close() {
        closeButton.click();
    }

    public List<RelatedPersonasCardComponent> getAllCards() {
        return personCards;
    }

    public void clickCard(int index) {
        if (index >= 0 && index < personCards.size()) {
            personCards.get(index).click();
        }
    }
}

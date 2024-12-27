package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class AdditionalInfoComponent extends BaseComponent {
    @FindBy(xpath = ".//div[@class='sourcesSliderItem']")
    private List<WebElement> infoCardNodes;

    private List<InfoCardComponent> infoCards;
    private AdditionalInfoModal additionalInfoModal;

    public AdditionalInfoComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.infoCards = infoCardNodes.stream()
                .map(node -> new InfoCardComponent(driver, node))
                .collect(Collectors.toList());
        this.additionalInfoModal = new AdditionalInfoModal(driver, rootElement);
    }
}

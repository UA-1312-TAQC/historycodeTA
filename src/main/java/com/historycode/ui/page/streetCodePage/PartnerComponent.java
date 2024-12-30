package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class PartnerComponent extends BaseComponent {
    @FindBy(xpath = ".//div[@class='partnerItem']")
    private List<WebElement> partnerLogoNodes;

    @FindBy(xpath = "")
    private WebElement carouselContainer;

    private List<PartnerLogoComponent> partnerLogos;

    public PartnerComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.partnerLogos = partnerLogoNodes.stream()
                .map(node -> new PartnerLogoComponent(driver, node))
                .collect(Collectors.toList());
    }

    public void pauseCarousel() {
        Actions actions = new Actions(driver);
        actions.moveToElement(carouselContainer).perform();
    }

    public List<String> getAllPartnerNames() {
        return partnerLogos.stream()
                .map(PartnerLogoComponent::getPartnerName)
                .collect(Collectors.toList());
    }

    public void clickPartnerByName(String partnerName) {
        partnerLogos.stream()
                .filter(partner -> partner.getPartnerName().equals(partnerName))
                .findFirst()
                .ifPresent(PartnerLogoComponent::clickPartnerLink);
    }
}

package com.historycode.ui.page.streetCodePage.components.carousels;

import com.historycode.ui.page.streetCodePage.components.PartnerLogoComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class PartnersCarousel extends BaseCarousel {
    @FindBy(xpath = ".//div[@class='partnerItem']")
    private List<WebElement> partnerLogoNodes;

    private final List<PartnerLogoComponent> partnerLogos;
    private boolean isAutoScrollPaused;

    public PartnersCarousel(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.isAutoScrollPaused = false;
        this.partnerLogos = partnerLogoNodes.stream()
                .map(node -> new PartnerLogoComponent(driver, node))
                .collect(Collectors.toList());
    }

    public void hoverLogo(int index) {
        if (index >= 0 && index < partnerLogos.size()) {
            partnerLogos.get(index).hover();
            isAutoScrollPaused = true;
        }
    }

    public boolean isAutoScrollActive() {
        return !isAutoScrollPaused;
    }

    public List<PartnerLogoComponent> getLogos() {
        return partnerLogos;
    }

    public PartnerLogoComponent getLogo(int index) {
        if (index >= 0 && index < partnerLogos.size()) {
            return partnerLogos.get(index);
        }
        return null;
    }
}

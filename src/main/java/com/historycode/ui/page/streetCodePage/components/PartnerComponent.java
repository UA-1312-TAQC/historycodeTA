package com.historycode.ui.page.streetCodePage.components;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.page.streetCodePage.components.carousels.PartnersCarousel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PartnerComponent extends BaseComponent {
    @FindBy(xpath = ".//div[@class='partnerContainer']")
    private WebElement carouselRoot;
    private PartnersCarousel carousel;

    public PartnerComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.carousel = new PartnersCarousel(driver, carouselRoot);
    }

    public List<PartnerLogoComponent> getPartnerLogos() {
        return carousel.getLogos();
    }

    public void hoverPartnerLogo(int index) {
        carousel.hoverLogo(index);
    }

    public boolean isCarouselScrolling() {
        return carousel.isAutoScrollActive();
    }

    public void clickPartnerLogo(int index) {
        PartnerLogoComponent logo = carousel.getLogo(index);
        if (logo != null) {
            logo.click();
        }
    }

    public String getPartnerDescription(int index) {
        PartnerLogoComponent logo = carousel.getLogo(index);
        return logo != null ? logo.getPartnerDescription() : "";
    }

    public boolean isTooltipDisplayed(int index) {
        PartnerLogoComponent logo = carousel.getLogo(index);
        return logo != null && logo.isTooltipDisplayed();
    }
}

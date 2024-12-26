package com.historycode.ui.page.streetCodePage;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class PartnerComponent extends BaseComponent {
    @FindBy(xpath = "")
    private List<WebElement> partnerLogoNodes;

    private List<PartnerLogoComponent> partnerLogos;

    public PartnerComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.partnerLogos = partnerLogoNodes.stream()
                .map(node -> new PartnerLogoComponent(driver, node))
                .collect(Collectors.toList());
    }
}

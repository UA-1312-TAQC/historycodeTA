package com.historycode.ui.page.adminpanel.partnerspage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.historycode.ui.component.BaseComponent;

import lombok.Getter;

@Getter
public class PartnersPagination extends BaseComponent {

    public PartnersPagination(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}

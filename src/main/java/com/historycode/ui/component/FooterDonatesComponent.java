package com.historycode.ui.component;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@Getter
public class FooterDonatesComponent {


    public FooterDonatesComponent(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
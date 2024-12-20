package com.historycode.ui.elements;

import com.historycode.ui.Base;
import org.openqa.selenium.WebDriver;

public abstract class BaseElement extends Base {
    public BaseElement(WebDriver driver) {
        super(driver);
    }
}

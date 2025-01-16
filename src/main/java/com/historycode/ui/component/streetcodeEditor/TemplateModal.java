package com.historycode.ui.component.streetcodeEditor;

import com.historycode.ui.component.BaseModal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TemplateModal extends BaseModal {

    public TemplateModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public WebElement getTemplateByInt(int index) {
        return rootElement.findElement(By.xpath("(//img[@class='template-image'])[" + index + "]"));
    }

}

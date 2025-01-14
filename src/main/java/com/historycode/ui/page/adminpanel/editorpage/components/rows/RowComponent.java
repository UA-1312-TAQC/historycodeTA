package com.historycode.ui.page.adminpanel.editorpage.components.rows;

import com.historycode.ui.component.BaseComponent;
import jdk.jfr.Frequency;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class RowComponent extends BaseComponent {
    private static final String TITLE_XPATH = ".//td[@class='ant-table-cell'][1]//div";

    @FindBy(xpath = TITLE_XPATH)
    private WebElement title;

    public RowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getTitle() {
        return title.getText();
    }

    // TODO: Create method to get delete picture (e.g., SVG or image source)
    // TODO: Create method to get edit picture (e.g., SVG or image source)
}

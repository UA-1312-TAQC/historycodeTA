package com.historycode.ui.page.adminpanel.editorpage.components.rows;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RowComponent extends BaseComponent {
    private static final String TITLE_XPATH = ".//td[@class='ant-table-cell'][1]//div";  // Relative to rootElement

    private final WebElement title;

    public RowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.title = rootElement.findElement(By.xpath(TITLE_XPATH));
    }

    public String getTitle() {
        return title.getText();
    }

    // TODO: Create method to get delete picture (e.g., SVG or image source)
    // TODO: Create method to get edit picture (e.g., SVG or image source)
}

package com.historycode.ui.page.adminpanel.editorpage.components.grids;

import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseGridComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class GridComponent extends BaseGridComponent {
    private static final String ROOT_ROW_XPATH = "//tbody//tr";
    private static final String HEADER_ITEM_XPATH = "//thead//th";

    @FindBy(xpath = ROOT_ROW_XPATH)
    public List<WebElement> rowElements;

    @FindBy(xpath = HEADER_ITEM_XPATH)
    public List<WebElement> headerItems;

    public GridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public List<WebElement> getHeaderItems() {
        return headerItems;
    }

    public List<String> getHeaderItemsString() {
        return headerItems.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}

package com.historycode.ui.page.adminpanel.editorpage.components.grids;

import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseGridComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GridComponent extends BaseGridComponent {

    @FindBy(xpath = "//tbody//tr")
    public List<WebElement> rowElements;
    @FindBy(xpath = "//thead//th")
    private List<WebElement> headerItemsAll;

    private List<WebElement> headerItems;

    public GridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        headerItems = new ArrayList<>();
        initHeaderItems();
    }

    private void initHeaderItems() {
        for (WebElement item : headerItemsAll) {
            if (item.isDisplayed()) {
                headerItems.add(item);
            }
        }
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

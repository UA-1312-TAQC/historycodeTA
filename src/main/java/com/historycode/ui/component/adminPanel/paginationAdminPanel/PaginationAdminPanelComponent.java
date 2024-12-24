package com.historycode.ui.component.adminPanel.paginationAdminPanel;

import com.historycode.ui.component.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PaginationAdminPanelComponent extends BaseComponent {
    @FindBy(xpath = "")
    WebElement nextPage;
    @FindBy(xpath = "")
    WebElement prevPage;
    @FindBy(xpath = "")
    List<WebElement> paginationItems;
    public PaginationAdminPanelComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    //TODO ask about return type after going to other pages
}

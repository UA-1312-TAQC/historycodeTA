package com.historycode.ui.component.adminPanel.gridAdminPanel;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Getter
public abstract class BaseRowComponent extends BaseComponent {
    //TODO ask do we need this?
    List<WebElement> rowElements;

    public BaseRowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    protected WebElement getRowElementById(int rowId) {
        return null;
    }

    protected WebElement getRowElementByName(String name) {
        return null;
    }
}

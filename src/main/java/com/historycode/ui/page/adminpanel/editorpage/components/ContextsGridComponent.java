package com.historycode.ui.page.adminpanel.editorpage.components;

import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseGridComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Getter
public class ContextsGridComponent extends BaseGridComponent {
    List<ContextsRowComponent> contextsRowComponents;

    public ContextsGridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}



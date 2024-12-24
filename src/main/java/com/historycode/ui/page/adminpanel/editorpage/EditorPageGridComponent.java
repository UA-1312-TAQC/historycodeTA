package com.historycode.ui.page.adminpanel.editorpage;

import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseGridComponent;
import com.historycode.ui.component.adminPanel.paginationAdminPanel.PaginationAdminPanelComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Getter
public class EditorPageGridComponent extends BaseGridComponent {
    List<EditorPageRowComponent> editorPageRowComponents;

    public EditorPageGridComponent(WebDriver driver, WebElement rootElement, List<WebElement> headerItems, PaginationAdminPanelComponent pagination) {
        super(driver, rootElement, headerItems, pagination);
    }
}



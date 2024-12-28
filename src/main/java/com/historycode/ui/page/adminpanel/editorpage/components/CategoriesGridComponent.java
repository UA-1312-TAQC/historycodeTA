package com.historycode.ui.page.adminpanel.editorpage.components;

import com.historycode.ui.component.adminPanel.gridAdminPanel.BaseGridComponent;
import com.historycode.ui.component.adminPanel.paginationAdminPanel.PaginationAdminPanelComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Getter
public class CategoriesGridComponent extends BaseGridComponent {
    List<CategoriesRowComponent> categoriesRowComponents;

    public CategoriesGridComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}



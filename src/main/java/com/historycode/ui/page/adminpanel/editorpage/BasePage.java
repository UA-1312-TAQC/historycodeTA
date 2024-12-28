package com.historycode.ui.page.adminpanel.editorpage;

import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import com.historycode.ui.page.adminpanel.editorpage.components.SectionsComponent;
import org.openqa.selenium.WebDriver;

public abstract class BasePage extends BasePageAdminPanel {
    SectionsComponent sectionsComponent;

    public BasePage(WebDriver driver) {
        super(driver);
    }
}

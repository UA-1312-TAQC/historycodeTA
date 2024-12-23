package com.historycode.ui.page.adminpanel;

import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import lombok.Getter;
import org.openqa.selenium.WebDriver;


@Getter
public abstract class EditorPageAdminPanel extends BasePageAdminPanel {

    public EditorPageAdminPanel(WebDriver driver) {
        super(driver);
    }
}
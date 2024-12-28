package com.historycode.ui.page.adminpanel.editorpage;

import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import org.openqa.selenium.WebDriver;

public abstract class BaseEditorPage extends BasePageAdminPanel {
    EditorPageSectionsComponent editorPageSectionsComponent;

    public BaseEditorPage(WebDriver driver) {
        super(driver);
    }
}

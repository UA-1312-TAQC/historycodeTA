package com.historycode.ui.page.adminpanel.editorpage;

import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class EditorPageAdminPanel extends BasePageAdminPanel {
    @FindBy(xpath="")
    WebElement addNewCategoryButton;
    EditorPageSectionsComponent editorPageSectionsComponent;
    EditorPageGridComponent editorPageGridComponent;

    public EditorPageAdminPanel(WebDriver driver) {
        super(driver);
    }
}

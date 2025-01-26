package com.historycode.ui.testrunners;

import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import org.testng.annotations.BeforeMethod;

public class TestRunnerWithAdminEditor extends TestRunnerWithAdmin{

    @BeforeMethod
    public void goToEditor() {
        new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToEditorPage();
    }

}

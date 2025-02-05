package com.historycode.ui.testrunners;

import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import io.qameta.allure.Step;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestRunnerWithAdminEditor extends BaseTestRunnerAdminPanel{

    @Step("Go to Editor page.")
    @BeforeMethod
    public void goToEditor() {
        new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToEditorPage();
    }

    @Step("Back to Admin Panel Page.")
    @AfterMethod
    public void goToBaseAdminPage() {
        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel");
    }

}

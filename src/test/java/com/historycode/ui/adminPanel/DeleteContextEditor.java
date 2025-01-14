package com.historycode.ui.adminPanel;

import com.historycode.ui.page.adminpanel.editorpage.ContextsPage;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.ContextsRowComponent;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;

import io.qameta.allure.Issue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class DeleteContextEditor extends TestRunnerWithAdmin {

    @BeforeMethod
    public void setupForDeleteContext() throws InterruptedException {
        login();
        driver.get(testValueProvider.getBaseUIUrl() + "admin-panel/editor");
        ContextsPage contextsPage = new ContextsPage(driver);
        contextsPage.addContext()
                .inputNewContext("Context1")
                .saveNewContext()
                .closeModal();
    }

    @Test
    @Issue("105")
    public void testDeleteContext() {
        ContextsPage contextsPage = new ContextsPage(driver);
        ContextsRowComponent contextToDelete = contextsPage.getTableRowByTitle("Context1");
        contextsPage.deleteTableRow(contextToDelete).clickOkButton();
        assertNull(contextsPage.getTableRowByTitle("Context1"), "Context was not deleted successfully.");
    }
}


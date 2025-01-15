package com.historycode.ui.adminPanel;

import com.historycode.ui.page.adminpanel.editorpage.ContextsPage;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.ContextsRowComponent;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;

import io.qameta.allure.Issue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.Random;

public class DeleteContextEditor extends TestRunnerWithAdmin {
    String contextName;
    @BeforeMethod
    public void setupForDeleteContext() throws InterruptedException {
        login();
        Random rand = new Random();
        int n = rand.nextInt(50);
        contextName = "Context_" + n;
        driver.get(testValueProvider.getBaseUIUrl() + "admin-panel/editor");
        ContextsPage contextsPage = new ContextsPage(driver);
        contextsPage.addContext()
                .inputNewContext(contextName)
                .saveNewContext()
                .closeModal();
    }

    @Test
    @Issue("105")
    public void testDeleteContext() {
        ContextsPage contextsPage = new ContextsPage(driver);
        ContextsRowComponent contextToDelete = contextsPage.getTableRowByTitle(contextName);
        contextsPage.deleteTableRow(contextToDelete).clickOkButton();
        assertNull(contextsPage.getTableRowByTitle(contextName), "Context was not deleted successfully.");
    }
}


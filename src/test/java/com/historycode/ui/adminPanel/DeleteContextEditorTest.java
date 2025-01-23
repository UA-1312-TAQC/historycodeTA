package com.historycode.ui.adminPanel;

import com.historycode.ui.page.adminpanel.editorpage.CategoriesPage;
import com.historycode.ui.page.adminpanel.editorpage.ContextsPage;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.ContextsRowComponent;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import io.qameta.allure.Issue;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

public class DeleteContextEditorTest extends TestRunnerWithAdmin {
    String contextName;
    @BeforeMethod
    public void setupForDeleteContext() throws InterruptedException {
        login();
        Random rand = new Random();
        int n = rand.nextInt(50);
        contextName = "Context_" + n;
        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel/editor");
        ContextsPage contextsPage = new CategoriesPage(driver).moveToContexts();
        contextsPage.clickAddContext()
                .enterContext(contextName)
                .save()
                .close();
    }

    @Test
    @Issue("105")
    public void testDeleteContext() {
        ContextsPage contextsPage = new ContextsPage(driver);
        ContextsRowComponent contextToDelete = contextsPage.getTableRowByTitle(contextName);
        assertNotNull(contextToDelete, "Context should exist before deletion: " + contextName);
        contextsPage.deleteTableRow(contextToDelete).clickOkButton();
        assertNull(contextsPage.getTableRowByTitle(contextName),
                String.format("Context '%s' still exists after deletion.", contextName));
    }

    @AfterMethod
    public void cleanup() {
    // Clean up any contexts that might have been left over from failed tests
        ContextsPage contextsPage = new ContextsPage(driver);
        ContextsRowComponent leftoverContext = contextsPage.getTableRowByTitle(contextName);
        if (leftoverContext != null) {
            contextsPage.deleteTableRow(leftoverContext).clickOkButton();
        }
    }
}


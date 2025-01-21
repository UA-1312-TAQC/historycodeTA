package com.historycode.ui.adminPanel;

import com.historycode.ui.page.adminpanel.editorpage.CategoriesPage;
import com.historycode.ui.page.adminpanel.editorpage.ContextsPage;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.ContextsRowComponent;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;

import io.qameta.allure.Issue;

import org.testng.annotations.AfterMethod;
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
        contextName = "я";
        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel/editor");
        ContextsPage contextsPage = new CategoriesPage(driver).moveToContexts();
        contextsPage.clickAddContext()
                .enterContext(contextName)
                .save()
                .close();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    @Issue("105")
    public void testDeleteContext() {
        ContextsPage contextsPage = new ContextsPage(driver);
        ContextsRowComponent contextToDelete = contextsPage.getTableRowByTitle(contextName);
        assertNotNull(contextToDelete, "Context should exist before deletion: " + contextName);
        contextsPage.deleteTableRow(contextToDelete).clickOkButton();
        assertNull(contextsPage.getTableRowByTitle(contextName),
                String.format("Context '%s' still exists after deletion", contextName));
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


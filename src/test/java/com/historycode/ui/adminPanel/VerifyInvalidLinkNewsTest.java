package com.historycode.ui.adminPanel;

import com.historycode.ui.page.adminpanel.newspage.NewsPageAdminPanel;
import com.historycode.ui.page.adminpanel.newspage.modal.CreateEditNewsModal;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;

import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class VerifyInvalidLinkNewsTest extends TestRunnerWithAdmin {

    private String newsTitle;
    private String newsText;
    private CreateEditNewsModal editNewsModal;

    @BeforeMethod
    public void setupForCreateNews() {
        login();

        newsTitle = "Test News";
        newsText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";

        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel/news");
        NewsPageAdminPanel newsPage = new NewsPageAdminPanel(driver);
        editNewsModal = newsPage.clickAddNewInfo();

        editNewsModal.inputNewsTitle(newsTitle);
        editNewsModal.inputNewsTextEditor(newsText);
        editNewsModal.inputNewsCreationDate(new java.sql.Date(System.currentTimeMillis()));
    }

    private static final String EXPECTED_ERROR_MESSAGE = 
        "Транслітерація має містити лише малі латинські літери, цифри та дефіс";
    @DataProvider(name = "invalidLinks")
    public Object[][] getInvalidLinks() {
        return new Object[][] {
            {"TESTLINK", "158.1"},
            {"Тестлінк", "158.2"},
            {"№\"?:*", "158.3"}
        };
    }
    @Test(dataProvider = "invalidLinks")
    @Issue("#{1}")
    public void testInvalidLink(String invalidLink, String issueId) {
        editNewsModal.inputNewsLinkTranslit(invalidLink);
        assertFalse(editNewsModal.isSaveButtonEnabled());
        assertEquals(editNewsModal.getNewsLinkTranslitErrorMessage(), 
            EXPECTED_ERROR_MESSAGE);
    }
}

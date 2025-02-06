package com.historycode.ui.adminPanel.NewsPage;

import com.historycode.ui.data_provider.NewsPageAdminDP;
import com.historycode.ui.page.adminpanel.newspage.NewsPageAdminPanel;
import com.historycode.ui.page.adminpanel.newspage.modal.EditNewsModal;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;

import io.qameta.allure.Issue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class VerifyInvalidLinkNewsTest extends TestRunnerWithAdmin {

    private String newsTitle;
    private String newsText;
    private String invalidNewsLink;
    private EditNewsModal editNewsModal;

    private static final String EXPECTED_ERROR_MESSAGE = 
        "Транслітерація має містити лише малі латинські літери, цифри та дефіс";

    @BeforeMethod
    public void setupForCreateNews() {
        login();

        newsTitle = "Test News";
        newsText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
        invalidNewsLink = "invalid-link@123";

        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel/news");
        NewsPageAdminPanel newsPage = new NewsPageAdminPanel(driver);
        editNewsModal = newsPage.clickAddNewInfo();

        editNewsModal.inputNewsTitle(newsTitle);
        editNewsModal.inputNewsTextEditor(newsText);
        editNewsModal.inputNewsLinkTranslit(invalidNewsLink);
    }

    @Test(dataProvider = "invalidLinks", dataProviderClass = NewsPageAdminDP.class)
    @Issue("#{issueId}")
    public void testInvalidLink(String invalidLink, String issueId) {
        assertFalse(editNewsModal.isSaveButtonEnabled());
        editNewsModal.inputNewsLinkTranslit(invalidLink);
        assertEquals(editNewsModal.getNewsLinkTranslitErrorMessage(), EXPECTED_ERROR_MESSAGE);
    }
}

package com.historycode.ui.adminPanel.NewsPage;

import com.historycode.ui.data_provider.NewsPageAdminDP;
import com.historycode.ui.page.adminpanel.newspage.NewsPageAdminPanel;
import com.historycode.ui.page.adminpanel.newspage.modal.EditNewsModal;
import com.historycode.ui.page.adminpanel.newspage.NewsPageGridComponent;
import com.historycode.ui.page.adminpanel.newspage.NewsRowComponent;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;

import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class VerifyInvalidLinkNewsTest extends TestRunnerWithAdmin {

    private String newsTitle;
    private String newsText;
    private String newsLink;
    private String imagePath = "src/test/resources/newsTest.png";
    private EditNewsModal editNewsModal;

    private static final String EXPECTED_ERROR_MESSAGE = 
        "Транслітерація має містити лише малі латинські літери, цифри та дефіс";

    @BeforeMethod
    public void setupForCreateNews() {
        login();

        newsTitle = "Test News";
        newsText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
        newsLink = "valid-link-123";

        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel/news");
        NewsPageAdminPanel newsPage = new NewsPageAdminPanel(driver);
        editNewsModal = newsPage.clickAddNewInfo();

        editNewsModal.inputNewsTitle(newsTitle);
        editNewsModal.inputNewsTextEditor(newsText);
        editNewsModal.inputNewsLinkTranslit(newsLink);
        editNewsModal.inputNewsCreationDate(new java.sql.Date(System.currentTimeMillis()));

        editNewsModal.clickUploadNewsPhoto(imagePath);
        editNewsModal.waitUntilPhotoIsUploaded();

        editNewsModal.saveNews();
    }

    @Test(dataProvider = "invalidLinks", dataProviderClass = NewsPageAdminDP.class)
    @Issue("#{issueId}")
    public void testInvalidLink(String invalidLink, String issueId) {
        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel/news");
        NewsPageAdminPanel newsPage = new NewsPageAdminPanel(driver);
        NewsPageGridComponent newsGrid = newsPage.getNewsPageGridComponent();

        int index = -1;
        for (int i = 0; i < newsGrid.getRowCount(); i++) {
            NewsRowComponent newsRow = newsGrid.getRowById(i);
            if (newsRow.getName().getText().equals(newsTitle)) {
                index = i;
                break;
            }
        }

        assertTrue(index >= 0, "Created news was not found.");

        editNewsModal = newsPage.editNewsByIndex(index);
        editNewsModal.inputNewsLinkTranslit(invalidLink);
        assertFalse(editNewsModal.isSaveButtonEnabled());
        assertEquals(editNewsModal.getNewsLinkTranslitErrorMessage(), EXPECTED_ERROR_MESSAGE);
    }
}

package com.historycode.ui.adminPanel;

import com.historycode.ui.page.adminpanel.newspage.NewsPageAdminPanel;
import com.historycode.ui.page.adminpanel.newspage.modal.CreateEditNewsModal;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;

import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class VerifyInvalidLinkNewsTest extends TestRunnerWithAdmin {

    private String newsTitle;
    private String newsText;
    private String imagePath = "src/test/resources/newsTest.png";
    private CreateEditNewsModal editNewsModal;

    private static final String EXPECTED_ERROR_MESSAGE = 
        "Посилання має містити лише малі латинські літери, цифри та дефіс";

    @BeforeMethod
    public void setupForCreateNews() {
        login();

        newsTitle = "Тестова новина";
        newsText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras et commodo ex. Pellentesque id sagittis ex. Morbi tincidunt volutpat ante, ut elementum turpis pulvinar et.";
        imagePath = "src/test/resources/newsTest.png";

        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel/news");
        NewsPageAdminPanel newsPage = new NewsPageAdminPanel(driver);
        editNewsModal = newsPage.clickAddNewInfo();

        editNewsModal.inputNewsTitle(newsTitle);
        editNewsModal.inputNewsTextEditor(newsText);
        editNewsModal.inputNewsCreationDate(new java.sql.Date(System.currentTimeMillis()));
        editNewsModal.clickUploadNewsPhoto(imagePath);
        editNewsModal.waitUntilPhotoIsUploaded();
    }

    @Test
    @Issue("#{issueId}")
    public void testUppercaseLatinInLink() {
        String invalidLink = "TESTLINK";
        editNewsModal.inputNewsLinkTranslit(invalidLink);

        assertFalse(editNewsModal.isSaveButtonEnabled(), "Save button should be disabled for invalid link.");

        assertEquals(editNewsModal.getNewsLinkTranslitErrorMessage(), EXPECTED_ERROR_MESSAGE, "Error message is incorrect for uppercase Latin letters.");
    }

    @Test
    @Issue("#{issueId}")
    public void testCyrillicCharactersInLink() {

        String invalidLink = "Тестлінк";
        editNewsModal.inputNewsLinkTranslit(invalidLink);

        assertFalse(editNewsModal.isSaveButtonEnabled(), "Save button should be disabled for Cyrillic characters.");

        assertEquals(editNewsModal.getNewsLinkTranslitErrorMessage(), EXPECTED_ERROR_MESSAGE, "Error message is incorrect for Cyrillic characters.");
    }

    @Test
    @Issue("#{issueId}")
    public void testSpecialCharactersInLink() {
        String invalidLink = "№\"?:*";
        editNewsModal.inputNewsLinkTranslit(invalidLink);

        assertFalse(editNewsModal.isSaveButtonEnabled(), "Save button should be disabled for special characters.");

        assertEquals(editNewsModal.getNewsLinkTranslitErrorMessage(), EXPECTED_ERROR_MESSAGE, "Error message is incorrect for special characters.");
    }
}

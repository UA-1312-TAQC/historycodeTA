package com.historycode.ui.adminPanel;

import com.historycode.ui.page.adminpanel.newspage.NewsPageAdminPanel;
import com.historycode.ui.page.adminpanel.newspage.modal.EditNewsModal;
import com.historycode.ui.page.adminpanel.newspage.NewsPageGridComponent;
import com.historycode.ui.page.adminpanel.newspage.NewsRowComponent;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import io.qameta.allure.Issue;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.Random;
import java.sql.Date;

public class EditNewsTest extends TestRunnerWithAdmin {

    private String originalTitle;
    private String originalLink;
    private String originalText;
    private String editedTitle;
    private String editedLink;
    private String editedText;

    @BeforeMethod
    public void setupForEditNews() throws InterruptedException {
        login();

        Random rand = new Random();
        int n = rand.nextInt(50);
        originalTitle = "Test News " + n;
        originalLink = "test-link-" + n;
        originalText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";

        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel/news");
        NewsPageAdminPanel newsPage = new NewsPageAdminPanel(driver);

        EditNewsModal editNewsModal = newsPage.clickAddNewInfo();
        editNewsModal.inputNewsTitle(originalTitle);
        editNewsModal.inputNewsLinkTranslit(originalLink);
        editNewsModal.inputNewsTextEditor(originalText);
        editNewsModal.inputNewsCreationDate(new Date(System.currentTimeMillis()));
        editNewsModal.clickUploadNews();
        editNewsModal.saveNews();
    }

    @Test
    @Issue("157")
    public void testEditNews() {
        NewsPageAdminPanel newsPage = new NewsPageAdminPanel(driver);

        NewsPageGridComponent newsGrid = newsPage.getNewsPageGridComponent();
        newsGrid.updateNewsRows(driver);

        NewsRowComponent newsToEdit = newsGrid.getRowById(0);
        assertNotNull(newsToEdit, "News should exist before editing.");

        EditNewsModal editNewsModal = newsPage.editNewsByIndex(0);

        editedTitle = "Edited Test News Title";
        editedLink = "edited-test-link";
        editedText = "Updated text. Lorem ipsum dolor sit amet, consectetur adipiscing elit. This is an added sentence.";

        editNewsModal.inputNewsTitle(editedTitle);
        editNewsModal.inputNewsLinkTranslit(editedLink);
        editNewsModal.inputNewsTextEditor(editedText);

        editNewsModal.clickBoldIcon(); 
        editNewsModal.clickItalicIcon();
        editNewsModal.clickStrikethroughIcon();
        editNewsModal.clickUnderlineIcon();
        editNewsModal.clickClearTextFormatIcon();
        editNewsModal.clickNumberedListIcon();
        editNewsModal.clickBulletedListIcon();

        editNewsModal.clickDeletePhoto();
        editNewsModal.clickUploadNews();

        editNewsModal.inputNewsCreationDate(new Date(System.currentTimeMillis() + 86400000));

        editNewsModal.saveNews();
        editNewsModal.clickCloseButton();

        newsGrid.updateNewsRows(driver);
        NewsRowComponent editedNews = newsGrid.getRowById(0);

        assertNotNull(editedNews, "Edited news should exist.");
        assertEquals(editedNews.getName().getText(), editedTitle, "The title was not updated.");
        String expectedYear = String.valueOf(java.time.Year.now().getValue());
        assertTrue(editedNews.getDateOfCreation().getText().contains(expectedYear), "Date was not updated correctly.");
    }

    @AfterMethod
    public void cleanup() {
        NewsPageAdminPanel newsPage = new NewsPageAdminPanel(driver);
        NewsPageGridComponent newsGrid = newsPage.getNewsPageGridComponent();
        newsGrid.updateNewsRows(driver);

        for (int i = 0; i < newsGrid.getRowCount(); i++) {
            NewsRowComponent leftoverNews = newsGrid.getRowById(i);
            if (leftoverNews.getName().getText().equals(editedTitle)) {
                newsPage.deleteNewsByIndex(i).clickOkButton();
                break;
            }
        }
    }
}


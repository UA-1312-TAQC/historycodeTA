package com.historycode.ui.adminPanel;

import com.historycode.ui.page.adminpanel.newspage.NewsPageAdminPanel;
import com.historycode.ui.page.adminpanel.newspage.NewsPageGridComponent;
import com.historycode.ui.page.adminpanel.newspage.NewsRowComponent;
import com.historycode.ui.page.adminpanel.newspage.modal.CreateEditNewsModal;
import com.historycode.ui.testrunners.BaseTestRunnerWithAdmin;
import io.qameta.allure.Issue;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Date;
import java.util.Random;

import static org.testng.Assert.*;

public class EditNewsTest extends BaseTestRunnerWithAdmin {

    private String originalTitle;
    private String originalLink;
    private String originalText;
    private String editedTitle;
    private String editedLink;
    private String editedText;
    private String imagePath = "src/test/resources/logo.png";

    @BeforeMethod
    public void setupForEditNews() throws InterruptedException {

        Random rand = new Random();
        int n = rand.nextInt(50);
        originalTitle = "Тестова новина " + n;
        originalLink = "test-link-" + n;
        originalText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";

        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel/news");
        NewsPageAdminPanel newsPage = new NewsPageAdminPanel(driver);

        CreateEditNewsModal editNewsModal = newsPage.clickAddNewInfo();
        editNewsModal.inputNewsTitle(originalTitle);
        editNewsModal.inputNewsLinkTranslit(originalLink);
        editNewsModal.inputNewsTextEditor(originalText);
        editNewsModal.inputNewsCreationDate(new Date(System.currentTimeMillis()));

        editNewsModal.clickUploadNewsPhoto(imagePath);
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

        CreateEditNewsModal editNewsModal = newsPage.editNewsByIndex(0);

        editedTitle = "Edited Test News Title";
        editedLink = "edited-test-link";
        editedText = "Updated text. Lorem ipsum dolor sit amet, consectetur adipiscing elit. This is an added sentence.";

        editNewsModal.inputNewsTitle(editedTitle);
        editNewsModal.inputNewsLinkTranslit(editedLink);
        editNewsModal.inputNewsTextEditor(editedText);

        editNewsModal.getTextEditorElements().clickTextEditorButton("bold");
        editNewsModal.getTextEditorElements().clickTextEditorButton("italic");
        editNewsModal.getTextEditorElements().clickTextEditorButton("strikethrough");
        editNewsModal.getTextEditorElements().clickTextEditorButton("underline");
        editNewsModal.getTextEditorElements().clickTextEditorButton("clear");
        editNewsModal.getTextEditorElements().clickTextEditorButton("numberedlist");
        editNewsModal.getTextEditorElements().clickTextEditorButton("bulletedlist");

        editNewsModal.clickDeleteButton(); 
        editNewsModal.clickUploadNewsPhoto(imagePath);

        editNewsModal.inputNewsCreationDate(new Date(System.currentTimeMillis() + 86400000));

        editNewsModal.saveNews();
        editNewsModal.clickCloseButton();

        newsGrid.updateNewsRows(driver);
        NewsRowComponent editedNews = newsGrid.getRowById(0);

        assertNotNull(editedNews, "Edited news should exist.");
        assertEquals(editedNews.getName().getText(), editedTitle, "The title was not updated.");
        String expectedYear = String.valueOf(java.time.Year.now().getValue());
        assertTrue(editedNews.getDateOfCreation().getText().contains(expectedYear), "Date was not updated correctly.");

        String uploadedImageUrl = editedNews.getUploadedImageUrl();
        assertTrue(uploadedImageUrl.contains(imagePath), "The uploaded image does not match the provided image.");
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

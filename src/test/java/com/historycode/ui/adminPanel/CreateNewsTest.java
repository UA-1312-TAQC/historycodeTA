package com.historycode.ui.adminPanel;

import com.historycode.ui.page.adminpanel.newspage.NewsPageAdminPanel;
import com.historycode.ui.page.adminpanel.newspage.NewsPageGridComponent;
import com.historycode.ui.page.adminpanel.newspage.NewsRowComponent;
import com.historycode.ui.page.adminpanel.newspage.modal.EditNewsModal;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import io.qameta.allure.Issue;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Date;
import java.util.Random;

import static org.testng.Assert.*;

public class CreateNewsTest extends TestRunnerWithAdmin {

    private String createdTitle;
    private String createdLink;
    private String createdText;
    private String imagePath = "src/test/resources/newsTest.png";

    @BeforeMethod
    public void setupForCreateNews() {
        login();

        Random rand = new Random();
        int n = rand.nextInt(50);
        createdTitle = "Тестова новина " + n;
        createdLink = "test-link-" + n;
        createdText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";

        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel/news");
        NewsPageAdminPanel newsPage = new NewsPageAdminPanel(driver);

        EditNewsModal editNewsModal = newsPage.clickAddNewInfo();
        editNewsModal.inputNewsTitle(createdTitle);
        editNewsModal.inputNewsLinkTranslit(createdLink);
        editNewsModal.inputNewsTextEditor(createdText);
        editNewsModal.inputNewsCreationDate(new Date(System.currentTimeMillis()));
        
        editNewsModal.clickUploadNewsPhoto(imagePath);
        editNewsModal.waitUntilPhotoIsUploaded();

        editNewsModal.saveNews();
    }

    @Test
    @Issue("156")
    public void testCreateNews() {
        NewsPageAdminPanel newsPage = new NewsPageAdminPanel(driver);

        NewsPageGridComponent newsGrid = newsPage.getNewsPageGridComponent();
        newsGrid.updateNewsRows(driver);

        NewsRowComponent createdNews = newsGrid.getRowById(0);
        assertNotNull(createdNews, "Created news should exist.");
        assertEquals(createdNews.getName().getText(), createdTitle, "The title is not correct.");
        String expectedYear = String.valueOf(java.time.Year.now().getValue());
        assertTrue(createdNews.getDateOfCreation().getText().contains(expectedYear), "Date was not correct.");

        String uploadedImageUrl = createdNews.getUploadedImageUrl(); 

        assertTrue(uploadedImageUrl.contains(imagePath), "The uploaded image does not match the provided image.");
    }

    @AfterMethod
    public void cleanup() {
        NewsPageAdminPanel newsPage = new NewsPageAdminPanel(driver);
        NewsPageGridComponent newsGrid = newsPage.getNewsPageGridComponent();
        newsGrid.updateNewsRows(driver);

        for (int i = 0; i < newsGrid.getRowCount(); i++) {
            NewsRowComponent leftoverNews = newsGrid.getRowById(i);
            if (leftoverNews.getName().getText().equals(createdTitle)) {
                newsPage.deleteNewsByIndex(i).clickOkButton();
                break;
            }
        }
    }
}

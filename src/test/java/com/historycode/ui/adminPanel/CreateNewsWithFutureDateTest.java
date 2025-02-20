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

public class CreateNewsWithFutureDateTest extends BaseTestRunnerWithAdmin {

    private String createdTitle;
    private String createdLink;
    private String createdText;
    private String imagePath = "src/test/resources/newsTest.png"; 

    final long ONE_DAY_IN_MILLIS = 24 * 60 * 60 * 1000L;

    @BeforeMethod
    public void setupForCreateNewsWithFutureDate() {
        

        Random rand = new Random();
        int n = rand.nextInt(50);
        createdTitle = "Тестова новина " + n;
        createdLink = "test-link-" + n;
        createdText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras et commodo ex. Pellentesque id sagittis ex. Morbi tincidunt volutpat ante, ut elementum turpis pulvinar et.";

        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel/news");
        NewsPageAdminPanel newsPage = new NewsPageAdminPanel(driver);

        CreateEditNewsModal editNewsModal = newsPage.clickAddNewInfo();
        editNewsModal.inputNewsTitle(createdTitle);
        editNewsModal.inputNewsLinkTranslit(createdLink);
        editNewsModal.inputNewsTextEditor(createdText);
        Date futureDate = new Date(System.currentTimeMillis() + ONE_DAY_IN_MILLIS);
        editNewsModal.inputNewsCreationDate(futureDate);

        // Upload the photo
        editNewsModal.clickUploadNewsPhoto(imagePath);
        editNewsModal.waitUntilPhotoIsUploaded();

        // Save the news
        editNewsModal.saveNews();
    }

    @Test
    @Issue("162")
    public void testCreateNewsWithFutureDate() {
        NewsPageAdminPanel newsPage = new NewsPageAdminPanel(driver);

        NewsPageGridComponent newsGrid = newsPage.getNewsPageGridComponent();
        newsGrid.updateNewsRows(driver);

        NewsRowComponent createdNews = newsGrid.getRowById(0);
        assertNotNull(createdNews, "Created news should exist.");
        assertEquals(createdNews.getName().getText(), createdTitle, "The title is not correct.");

        Date expectedDate = new Date(System.currentTimeMillis() + ONE_DAY_IN_MILLIS);
        String expectedDateString = new java.text.SimpleDateFormat("yyyy-MM-dd").format(expectedDate);
        assertTrue(createdNews.getDateOfCreation().getText().contains(expectedDateString), "Future date was not set correctly");

        assertFalse(createdNews.getDateOfCreation().getText().contains("published"), "The news should not be published yet.");

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

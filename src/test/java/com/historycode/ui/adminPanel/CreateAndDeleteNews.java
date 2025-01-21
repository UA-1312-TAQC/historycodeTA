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

public class CreateAndDeleteNews extends TestRunnerWithAdmin {

    private String newsTitle;
    private String newsLink;

    @BeforeMethod
    public void setupForCreateNews() throws InterruptedException {
        login();

        Random rand = new Random();
        int n = rand.nextInt(50);
        newsTitle = "Test News " + n;
        newsLink = "test-link-" + n;

        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel/news");
        NewsPageAdminPanel newsPage = new NewsPageAdminPanel(driver);

        EditNewsModal editNewsModal = newsPage.clickAddNewInfo();
        editNewsModal.inputNewsTitle(newsTitle);
        editNewsModal.inputNewsLinkTranslit(newsLink);
        editNewsModal.inputNewsTextEditor("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        editNewsModal.inputNewsCreationDate(new Date(System.currentTimeMillis()));

        editNewsModal.clickUploadNews();
        editNewsModal.saveNews();
        editNewsModal.clickCloseButton();
    }

    @Test
    @Issue("156")
    public void testDeleteNews() {
        NewsPageAdminPanel newsPage = new NewsPageAdminPanel(driver);

        NewsPageGridComponent newsGrid = newsPage.getNewsPageGridComponent();
        newsGrid.updateNewsRows(driver);

        NewsRowComponent newsToDelete = newsGrid.getRowById(0);

        assertNotNull(newsToDelete, "News should exist before deletion: " + newsTitle);

        newsPage.deleteNewsByIndex(0).clickOkButton();

        newsGrid.updateNewsRows(driver);
        assertNull(newsGrid.getRowById(0), String.format("News '%s' was not deleted", newsTitle));
    }

    @AfterMethod
    public void cleanup() {
        NewsPageAdminPanel newsPage = new NewsPageAdminPanel(driver);
        NewsPageGridComponent newsGrid = newsPage.getNewsPageGridComponent();
        newsGrid.updateNewsRows(driver);

        for (int i = 0; i < newsGrid.getRowCount(); i++) {
            NewsRowComponent leftoverNews = newsGrid.getRowById(i);
            if (leftoverNews.getName().getText().equals(newsTitle)) {
                newsPage.deleteNewsByIndex(i).clickOkButton();
                break;
            }
        }
    }
}

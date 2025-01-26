package com.historycode.ui.adminPanel;

import com.historycode.ui.page.adminpanel.newspage.NewsPageAdminPanel;
import com.historycode.ui.page.adminpanel.newspage.NewsPageGridComponent;
import com.historycode.ui.page.adminpanel.newspage.NewsRowComponent;
import com.historycode.ui.page.adminpanel.newspage.modal.CreateEditNewsModal;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;

import io.qameta.allure.Issue;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.util.Random;

public class DeleteNewsTest extends TestRunnerWithAdmin {

    private String createdTitle;
    private String createdLink;
    private String createdText;

    @BeforeMethod
    public void setupForDeleteNews() {
        login();

        Random rand = new Random();
        int n = rand.nextInt(50);
        createdTitle = "Тестова новина " + n;
        createdLink = "test-link-" + n;
        createdText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";

        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel/news");
        NewsPageAdminPanel newsPage = new NewsPageAdminPanel(driver);

        CreateEditNewsModal editNewsModal = newsPage.clickAddNewInfo();
        editNewsModal.inputNewsTitle(createdTitle);
        editNewsModal.inputNewsLinkTranslit(createdLink);
        editNewsModal.inputNewsTextEditor(createdText);
        editNewsModal.inputNewsCreationDate(new java.sql.Date(System.currentTimeMillis()));
        editNewsModal.clickUploadNews();
        editNewsModal.saveNews();
    }

    @Test
    @Issue("160")
    public void testDeleteNews() {
        NewsPageAdminPanel newsPage = new NewsPageAdminPanel(driver);

        NewsPageGridComponent newsGrid = newsPage.getNewsPageGridComponent();
        newsGrid.updateNewsRows(driver);

        for (int i = 0; i < newsGrid.getRowCount(); i++) {
            NewsRowComponent newsToDelete = newsGrid.getRowById(i);
            if (newsToDelete.getName().getText().equals(createdTitle)) {
                newsPage.deleteNewsByIndex(i).clickOkButton();
                break;
            }
        }

        newsGrid.updateNewsRows(driver);
        boolean newsExists = false;
        for (int i = 0; i < newsGrid.getRowCount(); i++) {
            NewsRowComponent news = newsGrid.getRowById(i);
            if (news.getName().getText().equals(createdTitle)) {
                newsExists = true;
                break;
            }
        }

        assertFalse(newsExists, "The news should have been deleted.");
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

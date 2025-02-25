package com.historycode.ui.adminPanel.NewsPage;

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

public class CreateNewsWithoutMandatoryFields extends BaseTestRunnerWithAdmin {

    private String createdTitle;
    private String createdLink;
    private String createdText;
    private Date createdDate;
    private String imagePath = "src/test/resources/newsTest.png";

    @BeforeMethod
    public void beforeMethod() {
        Random rand = new Random();
        int n = rand.nextInt(50);
        createdTitle = "Тестова новина " + n;
        createdLink = "test-link-" + n;
        createdText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras et commodo ex. Pellentesque id sagittis ex. Morbi tincidunt volutpat ante, ut elementum turpis pulvinar et.";
        createdDate = new Date(System.currentTimeMillis());

        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel/news");
    }

    @Test
    @Issue("169")
    public void testCreateNewsEmptyFields() {
        NewsPageAdminPanel newsPage = new NewsPageAdminPanel(driver);

        CreateEditNewsModal editNewsModal = newsPage.clickAddNewInfo();

        editNewsModal.inputNewsLinkTranslit(createdLink);
        editNewsModal.inputNewsTextEditor(createdText);
        editNewsModal.inputNewsCreationDate(createdDate);
        editNewsModal.clickUploadNewsPhoto(imagePath);
        assertFalse(editNewsModal.isSaveButtonEnabled());

        editNewsModal.inputNewsTitle(createdTitle);
        editNewsModal.inputNewsTextEditor(createdText);
        editNewsModal.inputNewsCreationDate(createdDate);
        editNewsModal.clickUploadNewsPhoto(imagePath);
        assertFalse(editNewsModal.isSaveButtonEnabled());

        editNewsModal.inputNewsTitle(createdTitle);
        editNewsModal.inputNewsLinkTranslit(createdLink);
        editNewsModal.inputNewsCreationDate(createdDate);
        editNewsModal.clickUploadNewsPhoto(imagePath);
        assertFalse(editNewsModal.isSaveButtonEnabled());

        editNewsModal.inputNewsTitle(createdTitle);
        editNewsModal.inputNewsLinkTranslit(createdLink);
        editNewsModal.inputNewsTextEditor(createdText);
        editNewsModal.inputNewsCreationDate(createdDate);
        assertFalse(editNewsModal.isSaveButtonEnabled());

        editNewsModal.inputNewsTitle(createdTitle);
        editNewsModal.inputNewsLinkTranslit(createdLink);
        editNewsModal.inputNewsTextEditor(createdText);
        editNewsModal.clickUploadNewsPhoto(imagePath);
        assertFalse(editNewsModal.isSaveButtonEnabled());
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

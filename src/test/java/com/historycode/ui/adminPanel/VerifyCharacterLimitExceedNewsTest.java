package com.historycode.ui.adminPanel;

import com.historycode.ui.page.adminpanel.newspage.NewsPageAdminPanel;
import com.historycode.ui.page.adminpanel.newspage.NewsPageGridComponent;
import com.historycode.ui.page.adminpanel.newspage.NewsRowComponent;
import com.historycode.ui.page.adminpanel.newspage.modal.CreateEditNewsModal;
import com.historycode.ui.testrunners.BaseTestRunnerWithAdmin;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;

import static org.testng.Assert.*;

public class VerifyCharacterLimitExceedNewsTest extends BaseTestRunnerWithAdmin {

    private String createdTitle;
    private String createdLink;
    private String createdText;
    private String imagePath = "src/test/resources/newsTest.png";

    private static final String TITLE_ERROR_MESSAGE = "Ви перевищили максимально доступну кількість символів";
    private static final String LINK_ERROR_MESSAGE = "Ви перевищили максимально доступну кількість символів";
    private static final String CREATE_UPDATE_ERROR_MESSAGE = "Не вдалося оновити/створити новину. Спробуйте ще раз.";

    @BeforeMethod
    public void setupForCreateNews() {
        createdTitle = "Тестова новина, яка перевищує ліміт по кількості символів в назві, довжина якої більше ніж ліміт, на 1 символ";
        createdLink = "test-link-that-exceeds-the-character-limit-in-url-field-which-should-not-be-accepted" + new String(new char[100]);
        createdText = new String(new char[15001]).replace("\0", "a");

        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel/news");
        NewsPageAdminPanel newsPage = new NewsPageAdminPanel(driver);
        CreateEditNewsModal editNewsModal = newsPage.clickAddNewInfo();

        editNewsModal.inputNewsTitle(createdTitle);
        editNewsModal.inputNewsLinkTranslit(createdLink);
        editNewsModal.inputNewsTextEditor(createdText);
    }

    @Test
    public void testCreateNewsWithExceedingCharacterLimits() {
        NewsPageAdminPanel newsPage = new NewsPageAdminPanel(driver);

        CreateEditNewsModal editNewsModal = newsPage.clickAddNewInfo();

        editNewsModal.inputNewsTitle(createdTitle);
        editNewsModal.inputNewsLinkTranslit(createdLink);
        editNewsModal.inputNewsTextEditor(createdText);

        editNewsModal.saveNews();

        String actualTitleMessage = editNewsModal.getNewsTitleErrorMessage();
        assertTrue(actualTitleMessage.contains(TITLE_ERROR_MESSAGE), "Error message for title exceeded the limit is not displayed");

        String actualLinkMessage = editNewsModal.getNewsLinkTranslitErrorMessage();
        assertTrue(actualLinkMessage.contains(LINK_ERROR_MESSAGE), "Error message for link exceeded the limit is not displayed");

        assertTrue(actualTitleMessage.contains(CREATE_UPDATE_ERROR_MESSAGE), "Error message for text exceeded the limit is not displayed.");
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

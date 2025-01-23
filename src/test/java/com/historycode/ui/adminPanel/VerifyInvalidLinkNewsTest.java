package com.historycode.ui.adminPanel;

import com.historycode.ui.page.adminpanel.newspage.NewsPageAdminPanel;
import com.historycode.ui.page.adminpanel.newspage.modal.EditNewsModal;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;

import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class VerifyInvalidLinkNewsTest extends TestRunnerWithAdmin {

    private String newsTitle;
    private String newsText;
    private EditNewsModal editNewsModal;

    @BeforeMethod
    public void setupForCreateNews() {
        login();

        newsTitle = "Test News";
        newsText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";

        driver.get(testValueProvider.getBaseUIUrl() + "/admin-panel/news");
        NewsPageAdminPanel newsPage = new NewsPageAdminPanel(driver);
        editNewsModal = newsPage.clickAddNewInfo();

        editNewsModal.inputNewsTitle(newsTitle);
        editNewsModal.inputNewsTextEditor(newsText);
        editNewsModal.inputNewsCreationDate(new java.sql.Date(System.currentTimeMillis()));
    }

    @Test
    @Issue("158.1")
    public void testLinkFieldWithUppercaseLatin() {
        String invalidLink = "TESTLINK";  
    
        editNewsModal.inputNewsLinkTranslit(invalidLink);
    
        assertFalse(editNewsModal.isSaveButtonEnabled());
    
        String errorMessage = editNewsModal.getNewsLinkTranslitErrorMessage();
        assertEquals(errorMessage, "Транслітерація має містити лише малі латинські літери, цифри та дефіс");
    }
    
    @Test
    @Issue("158.2")
    public void testLinkFieldWithCyrillic() {
        String invalidLink = "Тестлінк";  
    
        editNewsModal.inputNewsLinkTranslit(invalidLink);
    
        assertFalse(editNewsModal.isSaveButtonEnabled());
    
        String errorMessage = editNewsModal.getNewsLinkTranslitErrorMessage();
        assertEquals(errorMessage, "Транслітерація має містити лише малі латинські літери, цифри та дефіс");
    }
    
    @Test
    @Issue("158.3")
    public void testLinkFieldWithSpecialCharacters() {
        String invalidLink = "№\"?:*";  
    
        editNewsModal.inputNewsLinkTranslit(invalidLink);
    
        assertFalse(editNewsModal.isSaveButtonEnabled());
    
        String errorMessage = editNewsModal.getNewsLinkTranslitErrorMessage();
        assertEquals(errorMessage, "Транслітерація має містити лише малі латинські літери, цифри та дефіс");
    }    
}

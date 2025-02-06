package com.historycode.ui.adminPanel.NewsPage;

import com.historycode.ui.data_provider.NewsPageAdminDP;
import com.historycode.ui.page.adminpanel.newspage.modal.CreateEditNewsModal;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import io.qameta.allure.Issue;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class VerifyInvalidLinkNewsTest extends TestRunnerWithAdmin {

    private static final String EXPECTED_ERROR_MESSAGE = "Транслітерація має містити лише малі латинські літери, цифри та дефіс";
    private CreateEditNewsModal editNewsModal;


    @Test(dataProvider = "invalidLinks", dataProviderClass = NewsPageAdminDP.class)
    @Issue("#{issueId}")
    public void testInvalidLink(String invalidLink, String issueId) {
        editNewsModal.inputNewsLinkTranslit(invalidLink);
        assertFalse(editNewsModal.isSaveButtonEnabled());
        assertEquals(editNewsModal.getNewsLinkTranslitErrorMessage(), EXPECTED_ERROR_MESSAGE);
    }
}

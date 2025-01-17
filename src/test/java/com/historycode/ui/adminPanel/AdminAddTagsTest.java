package com.historycode.ui.adminPanel;

import com.historycode.ui.page.adminpanel.editorpage.TagsPage;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AdminAddTagsTest extends TestRunnerWithAdmin {

    private String newTag;

    @BeforeMethod
    @Step("Adding new Tag")
    public void createNewTag() {
        this.newTag = "Я_newTag_" + RandomStringUtils.randomAlphanumeric(10);
        new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToEditorPage()
                .moveToTags()
                .clickAddTag()
                .enterTag(newTag)
                .save()
                .close();
    }

    @Test
    @Issue("102")
    @Description("Verify that admin can edit existing tag")
    public void addTagsTest() {
        TagsPage tagsPage = new TagsPage(driver);
        SoftAssert softAssert = new SoftAssert();
        String editTagName = newTag + RandomStringUtils.randomAlphanumeric(10);

        softAssert.assertNotNull(tagsPage.getTableRowByTitle(newTag), "The tag was successfully created");

        tagsPage.editTableRow(tagsPage.getTableRowByTitle(newTag))
                .enterTag(editTagName)
                .save()
                .close();

        tagsPage = new TagsPage(driver);
        newTag = editTagName;

        softAssert.assertNotNull(tagsPage.getTableRowByTitle(newTag), "The tag was successfully edited");
        softAssert.assertAll();
    }

    @AfterMethod
    @Step("Deleting new Tag")
    public void deleteNewTag() {
        new TagsPage(driver)
                .deleteTableRow(
                        new TagsPage(driver)
                        .getTableRowByTitle(newTag))
                .clickOkButton();
    }
}

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

public class AdminAddTagsTest extends TestRunnerWithAdmin {

    private String newTag;

    @BeforeMethod
    @Step("Adding new Tag")
    public void createNewTag() {
        this.newTag = "newTag_" + RandomStringUtils.randomAlphanumeric(10);

        new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToEditorPage()
                .moveToTags();
//                .clickAddTag()
//                .enterTag(newTag)
//                .save()
//                .close();
    }

    @Test
    @Issue("102")
    @Description("Verify that admin can edit existing tag")
    public void addTagsTest() {
        TagsPage tagsPage = new TagsPage(driver);
        tagsPage.scrollToEndOfPage();

        //todo: Fix the problem with page switching
        //Go to the last page to check if a new tag has been created and then delete it
        while (tagsPage.getGrid().getPagination().hasNextPage()) {
            tagsPage = tagsPage.clickNextPage();
        }

        Assert.assertNotNull(tagsPage.getTableRowByTitle(newTag));
    }

    @AfterMethod
    @Step("Deleting new Tag")
    public void deleteNewTag() {
//        TagsRowComponent leftoverContext = new TagsPage(driver).getTableRowByTitle(newTag);
//        if (leftoverContext != null) {
//            new TagsPage(driver).deleteTableRow(leftoverContext).clickOkButton();
//        }
    }
}

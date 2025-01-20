package com.historycode.ui.adminPanel;

import com.historycode.ui.page.adminpanel.editorpage.TagsPage;
import com.historycode.ui.page.adminpanel.editorpage.components.modals.TagsModalComponent;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AdminEditorTagsTest extends TestRunnerWithAdmin {

    private String newTag;
    private SoftAssert softAssert;
    private static final String TAG_PREFIX = "Я_newTag_";

    @Step("Adding new Tag")
    public void createNewTag() {
        int lengthOfNewName = 10;
        this.newTag = TAG_PREFIX + RandomStringUtils.randomAlphanumeric(lengthOfNewName);

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
    @Issue("107")
    @Description("Verify that admin can save a new tag if the mandatory field is full")
    public void createTagTest() {
        softAssert = new SoftAssert();

        TagsModalComponent tagsModalComponent = new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToEditorPage()
                .moveToTags()
                .clickAddTag();

        softAssert.assertFalse(tagsModalComponent.getSaveButton().isEnabled(), "The name field is required");

        int characterLimit = 50;
        int lengthOfNewName = 60;
        tagsModalComponent.enterTag(TAG_PREFIX + RandomStringUtils.randomAlphanumeric(lengthOfNewName));

        softAssert.assertTrue(tagsModalComponent.getInputComponent().getInputText().length() <= characterLimit, "Header length limit is valid = 50");

        lengthOfNewName = 10;
        this.newTag = TAG_PREFIX + RandomStringUtils.randomAlphanumeric(lengthOfNewName);

        tagsModalComponent
                .setTag(newTag)
                .save()
                .close();

        softAssert.assertNotNull(new TagsPage(driver).getTableRowByTitle(newTag), "The tag was successfully created");
        softAssert.assertAll();

        deleteNewTag();
    }

    @Test
    @Issue("102")
    @Description("Verify that admin can edit existing tag")
    public void editTagTest() {
        createNewTag();

        TagsPage tagsPage = new TagsPage(driver);
        softAssert = new SoftAssert();
        String editTagName = "_edited";

        softAssert.assertNotNull(tagsPage.getTableRowByTitle(newTag), "The tag was successfully created");

        tagsPage.editTableRow(tagsPage.getTableRowByTitle(newTag))
                .enterTag(editTagName)
                .save()
                .close();

        newTag += editTagName;

        softAssert.assertNotNull(tagsPage.getTableRowByTitle(newTag), "The tag was successfully edited");
        softAssert.assertAll();

        deleteNewTag();
    }

    @Test
    @Issue("103")
    @Description("Verify that admin can delete existing tag")
    public void deleteTagTest() {
        createNewTag();

        TagsPage tagsPage = new TagsPage(driver);
        softAssert = new SoftAssert();

        softAssert.assertNotNull(tagsPage.getTableRowByTitle(newTag), "The tag was successfully created");

        deleteNewTag();
        /*todo: After deleting an item from the table, the grid is not updated.
                1. Is this a bug?
                2. If not, then to check it, you need to write a page refresh
                   to reload the grid, how best to do it? */
        tagsPage = new TagsPage(driver);

        softAssert.assertNull(tagsPage.getTableRowByTitle(newTag), "The tag was successfully deleted");
        softAssert.assertAll();
    }

    @Step("Deleting new Tag")
    public void deleteNewTag() {
        new TagsPage(driver)
                .deleteTableRow(
                        new TagsPage(driver)
                                .getTableRowByTitle(newTag))
                .clickOkButton();
    }
}

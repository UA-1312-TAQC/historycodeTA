package com.historycode.ui.adminPanel;

import com.historycode.ui.page.adminpanel.editorpage.TagsPage;
import com.historycode.ui.page.adminpanel.editorpage.components.modals.TagsModalComponent;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.testrunners.BaseTestRunnerWithAdmin;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminEditorTagsTest extends BaseTestRunnerWithAdmin {

    private String newTag;
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
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify that admin can save a new tag if the mandatory field is full")
    public void createTagTest() {
        TagsModalComponent tagsModalComponent =
                new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToEditorPage()
                .moveToTags()
                .clickAddTag();
        Assert.assertFalse(tagsModalComponent.getSaveButton().isEnabled(), "The name field is required");

        int characterLimit = 50;
        int lengthOfNewName = 60;
        tagsModalComponent.enterTag(TAG_PREFIX + RandomStringUtils.randomAlphanumeric(lengthOfNewName));
        Assert.assertTrue(tagsModalComponent.getInputComponent().getInputValue().length() <= characterLimit, "Header length limit is valid = 50");

        lengthOfNewName = 10;
        this.newTag = TAG_PREFIX + RandomStringUtils.randomAlphanumeric(lengthOfNewName);
        tagsModalComponent
                .setTag(newTag)
                .save()
                .close();

        Assert.assertNotNull(new TagsPage(driver).getTableRowByTitle(newTag), "The tag was not successfully created");
        deleteNewTag();
    }

    @Test
    @Issue("102")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify that admin can edit existing tag")
    public void editTagTest() {
        createNewTag();
        TagsPage tagsPage = new TagsPage(driver);
        Assert.assertNotNull(tagsPage.getTableRowByTitle(newTag), "The tag was not successfully created");
        String newName = newTag + "_edited";

        tagsPage.editTableRow(tagsPage.getTableRowByTitle(newTag))
                .setTag(newName)
                .save()
                .close();

        newTag += "_edited";

        Assert.assertNotNull(tagsPage.getTableRowByTitle(newTag), "The tag was not successfully edited");
        deleteNewTag();
    }

    @Test
    @Issue("103")
    @Epic("(Epic#5) Admin/Other pages")
    @Description("Verify that admin can delete existing tag")
    public void deleteTagTest() {
        createNewTag();
        Assert.assertNotNull( new TagsPage(driver).getTableRowByTitle(newTag), "The tag was not successfully created");

        deleteNewTag();
        Assert.assertNull(new TagsPage(driver).getTableRowByTitle(newTag), "The tag was not successfully deleted");
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

package com.historycode.cucumber.steps.editor;

import com.historycode.cucumber.contexts.ScenarioContext;
import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import com.historycode.ui.page.adminpanel.editorpage.*;
import com.historycode.ui.page.adminpanel.editorpage.components.modals.*;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.checkerframework.checker.nullness.qual.AssertNonNullIfNonNull;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class BaseEditorSteps {

    WebDriver driver = ScenarioContext.getDriver();

    @Given("I clicked the {string} on the upper tab panel.")
    @When("I click the {string} on the upper tab panel.")
    public void clickButtonInUpperTabPanel(String name) {
        BaseEditorPage page = new BaseEditorPage(driver);
        switch (name) {
            case "Categories" -> page.moveToCategories();
            case "Tags" -> page.moveToTags();
            case "Contexts" -> page.moveToContexts();
            case "Positions" -> page.moveToPositions();
        }
    }

    @Given("I clicked the {string} add button.")
    @When("I click the {string} add button.")
    public void clickAddButton(String name) {
        switch (name) {
            case "Додати нову категорію" -> {new CategoriesPage(driver).clickAddCategory();}
            case "Додати новий тег" -> {new TagsPage(driver).clickAddTag();}
            case "Додати контекст" -> {new ContextsPage(driver).clickAddContext();}
            case "Додати нову позицію" -> {new PositionsPage(driver).clickAddPosition();}
        }
    }

    @Given("I clicked the modal {string} close button.")
    @When("I click the modal {string} close button.")
    public void clickModalCloseButton(String name) {
        BaseCreateEditModalComponent modal =
                new BaseCreateEditModalComponent(
                        driver,
                        new BaseEditorPage(driver).getDisplayedModalRoot());
        modal.close();
    }

    @Given("I clicked the modal {string} save button.")
    @When("I click the modal {string} save button.")
    public void clickModalSaveButton(String name) {
        String section = new BaseEditorPage(driver).getSelectedSection();
        switch (section) {
            case "Categories" -> { new CategoriesModalComponent(driver,
                    new BaseEditorPage(driver).getDisplayedModalRoot()).save();}
            case "Tags" -> { new TagsModalComponent(driver,
                    new BaseEditorPage(driver).getDisplayedModalRoot()).save();}
            case "Contexts" -> { new ContextsModalComponent(driver,
                    new BaseEditorPage(driver).getDisplayedModalRoot()).save();}
            case "Positions" -> { new PositionsModalComponent(driver,
                    new BaseEditorPage(driver).getDisplayedModalRoot()).save();}
        }
    }

    @Given("I filled the modal title field with {string}.")
    @When("I fill the modal title field with {string}.")
    public void fillOutModalFieldWithData(String name) {
        String section = new BaseEditorPage(driver).getSelectedSection();
        switch (section) {
            case "Categories" -> { new CategoriesModalComponent(driver,
                    new BaseEditorPage(driver).getDisplayedModalRoot()).enterCategory(name);}
            case "Tags" -> { new TagsModalComponent(driver,
                    new BaseEditorPage(driver).getDisplayedModalRoot()).enterTag(name);}
            case "Contexts" -> { new ContextsModalComponent(driver,
                    new BaseEditorPage(driver).getDisplayedModalRoot()).enterContext(name);}
            case "Positions" -> { new PositionsModalComponent(driver,
                    new BaseEditorPage(driver).getDisplayedModalRoot()).enterPosition(name);}
        }
    }

    @Given("I searched the row with {string} name.")
    @When("I search the row with {string} name.")
    public void searchRowWithName(String name) {
        String section = new BaseEditorPage(driver).getSelectedSection();
        switch (section) {
            case "Categories" -> { new CategoriesPage(driver).moveToPageWithRow(name);}
            case "Tags" -> { new TagsPage(driver).moveToPageWithRow(name);}
            case "Contexts" -> { new ContextsPage(driver).moveToPageWithRow(name);}
            case "Positions" -> { new PositionsPage(driver).moveToPageWithRow(name);}
        }
    }

    @Given("I clicked the {string} action button on the row with {string}}.")
    @When("I click the {string} action button on the row with {string}.")
    public void clickRowActionButton(String button, String name) {
        switch (button) {
            case "delete" -> { clickRowDeleteActionButton(name);}
            case "edit" -> { clickRowEditActionButton(name);}
        }
    }

    @Given("I filled the image field with the image {string}.")
    @When("I fill the image field with the image {string}.")
    public void fillOutImageFieldWithData(String path) {
        String section = new BaseEditorPage(driver).getSelectedSection();
        switch (section) {
            case "Categories" -> { new CategoriesModalComponent(driver,
                    new BaseEditorPage(driver).getDisplayedModalRoot()).enterImage(path);}
        }
    }

    @Given("I created position with name {string}.")
    @When("I create position with name {string}.")
    public void createPositionWithName(String name) {
        moveToAdminPanelIfNeed();
        new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToEditorPage()
                .moveToPositions()
                .clickAddPosition()
                .enterPosition(name)
                .save()
                .close();
    }

    @Given("I created tag with name {string}.")
    @When("I create tag with name {string}.")
    public void createTagWithName(String name) {
        moveToAdminPanelIfNeed();
        new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToEditorPage()
                .moveToTags()
                .clickAddTag()
                .enterTag(name)
                .save()
                .close();
    }

    @Given("I created context with name {string}.")
    @When("I create context with name {string}.")
    public void createContextWithName(String name) {
        moveToAdminPanelIfNeed();
        new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToEditorPage()
                .moveToContexts()
                .clickAddContext()
                .enterContext(name)
                .save()
                .close();
    }

    @Given("I created category with name {string} and image {string}.")
    @When("I create category with name {string} and image {string}.")
    public void createCategoryWithName(String name, String path) {
        moveToAdminPanelIfNeed();
        new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToEditorPage()
                .moveToCategories()
                .clickAddCategory()
                .enterCategory(name)
                .enterImage(path)
                .save()
                .close();
    }

    @Then("list of {string} is displayed.")
    public void checkTagsListIsDisplayed(String name) {
        switch (name) {
            case "categories" -> {Assert.assertTrue(new CategoriesPage(driver).isGridDisplayed());}
            case "tags" -> {Assert.assertTrue(new TagsPage(driver).isGridDisplayed());}
            case "contexts" -> {Assert.assertTrue(new ContextsPage(driver).isGridDisplayed());}
            case "positions" -> {Assert.assertTrue(new PositionsPage(driver).isGridDisplayed());}
        }
    }

    @Then("table has columns {string}.")
    public void checkTableHasColumns(String columnsString) {
        List<String> columns = Arrays.asList(columnsString.split(",\\s*"));
        String section = new BaseEditorPage(driver).getSelectedSection();
        List<String> tableHeaders = null;
        switch (section) {
            case "Categories" -> {tableHeaders = new CategoriesPage(driver).getTableHeadersString();}
            case "Tags" -> {tableHeaders = new TagsPage(driver).getTableHeadersString();}
            case "Contexts" -> {tableHeaders = new ContextsPage(driver).getTableHeadersString();}
            case "Positions" -> {tableHeaders = new PositionsPage(driver).getTableHeadersString();}
        }
        Assert.assertEquals(tableHeaders, columns);
    }

    @Then("modal window is displayed.")
    public void checkModalWindowIsDisplayed() {
        String section = new BaseEditorPage(driver).getSelectedSection();
        Assert.assertNotNull(new BaseEditorPage(driver).getDisplayedModalRoot());
        switch (section) {
            case "Categories" -> {Assert.assertTrue(new CategoriesModalComponent(driver,
                    new BaseEditorPage(driver).getDisplayedModalRoot()).isExist());}
            case "Tags" -> {Assert.assertTrue(new TagsModalComponent(driver,
                    new BaseEditorPage(driver).getDisplayedModalRoot()).isExist());}
            case "Contexts" -> {Assert.assertTrue(new ContextsModalComponent(driver,
                    new BaseEditorPage(driver).getDisplayedModalRoot()).isExist());}
            case "Positions" -> {Assert.assertTrue(new PositionsModalComponent(driver,
                    new BaseEditorPage(driver).getDisplayedModalRoot()).isExist());}
        }
    }

    @Then("modal has title {string}.")
    public void checkModalHasTitle(String title) {
        String section = new BaseEditorPage(driver).getSelectedSection();
        switch (section) {
            case "Categories" -> {Assert.assertEquals(new CategoriesModalComponent(driver,
                    new BaseEditorPage(driver).getDisplayedModalRoot()).getTitleString(), title);}
            case "Tags" -> {Assert.assertEquals(new TagsModalComponent(driver,
                    new BaseEditorPage(driver).getDisplayedModalRoot()).getTitleString(), title);}
            case "Contexts" -> {Assert.assertEquals(new ContextsModalComponent(driver,
                    new BaseEditorPage(driver).getDisplayedModalRoot()).getTitleString(), title);}
            case "Positions" -> {Assert.assertEquals(new PositionsModalComponent(driver,
                    new BaseEditorPage(driver).getDisplayedModalRoot()).getTitleString(), title);}
        }
    }

    @Then("table has {string} action.")
    public void checkTableHasAction(String name) {
        switch (name) {
            case "delete" -> {checkTableHasDeleteAction();}
            case "edit" -> {checkTableHasEditAction();}
            default -> {Assert.fail("Unknown action: " + name);}
        }
    }

    @Then("edited {string} {string} is exist with new name {string}.")
    public void checkCategoryIsDisplayedWithNewName(String tab, String oldName, String newName) {
        switch (tab) {
            case "Category" -> {
                Assert.assertNull(new CategoriesPage(driver).moveToPageWithRow(oldName).getTableRowByTitle(oldName));
                Assert.assertNotNull(new CategoriesPage(driver).moveToPageWithRow(newName).getTableRowByTitle(newName));
            }
            case "Tag" -> {
                Assert.assertNull(new TagsPage(driver).moveToPageWithRow(oldName).getTableRowByTitle(oldName));
                Assert.assertNotNull(new TagsPage(driver).moveToPageWithRow(newName).getTableRowByTitle(newName));
            }
            case "Context" -> {
                Assert.assertNull(new ContextsPage(driver).moveToPageWithRow(oldName).getTableRowByTitle(oldName));
                Assert.assertNotNull(new ContextsPage(driver).moveToPageWithRow(newName).getTableRowByTitle(newName));
            }
            case "Position" -> {
                Assert.assertNull(new PositionsPage(driver).moveToPageWithRow(oldName).getTableRowByTitle(oldName));
                Assert.assertNotNull(new PositionsPage(driver).moveToPageWithRow(newName).getTableRowByTitle(newName));
            }
            default -> {Assert.fail("Unknown tab: " + tab);}
        }
    }

    @Then("created {string} {string} is exist.")
    public void checkCategoryIsDisplayed(String tab, String name) {
        switch (tab) {
            case "Category" -> {Assert.assertNotNull(new CategoriesPage(driver).moveToPageWithRow(name).getTableRowByTitle(name));}
            case "Tag" -> {Assert.assertNotNull(new TagsPage(driver).moveToPageWithRow(name).getTableRowByTitle(name));}
            case "Context" -> {Assert.assertNotNull(new ContextsPage(driver).moveToPageWithRow(name).getTableRowByTitle(name));}
            case "Position" -> {Assert.assertNotNull(new PositionsPage(driver).moveToPageWithRow(name).getTableRowByTitle(name));}
            default -> {Assert.fail("Unknown tab: " + tab);}
        }
    }

    @Then("entered title is less than {int} characters.")
    public void checkTitleLength(int length) {
        String section = new BaseEditorPage(driver).getSelectedSection();
        switch (section) {
            case "Categories" -> {Assert.assertTrue(new CategoriesModalComponent(driver,
                    new BaseEditorPage(driver).getDisplayedModalRoot()).getInputFieldText().length() <= length);}
            case "Tags" -> {Assert.assertTrue(new TagsModalComponent(driver,
                    new BaseEditorPage(driver).getDisplayedModalRoot()).getInputFieldText().length() <= length);}
            case "Contexts" -> {Assert.assertTrue(new ContextsModalComponent(driver,
                    new BaseEditorPage(driver).getDisplayedModalRoot()).getInputFieldText().length() <= length);}
            case "Positions" -> {Assert.assertTrue(new PositionsModalComponent(driver,
                    new BaseEditorPage(driver).getDisplayedModalRoot()).getInputFieldText().length() <= length);}
        }
    }

    public void checkTableHasDeleteAction() {
        String section = new BaseEditorPage(driver).getSelectedSection();
        boolean actual = false;
        switch (section) {
            case "Categories" -> {actual = new CategoriesPage(driver)
                    .getTableRowByNumber(1)
                    .getDeleteAction()
                    .isDisplayed();}
            case "Tags" -> {actual = new TagsPage(driver)
                    .getTableRowByNumber(1)
                    .getDeleteAction()
                    .isDisplayed();}
            case "Contexts" -> {actual = new ContextsPage(driver)
                    .getTableRowByNumber(1)
                    .getDeleteAction()
                    .isDisplayed();}
            case "Positions" -> {actual = new PositionsPage(driver)
                    .getTableRowByNumber(1)
                    .getDeleteAction()
                    .isDisplayed();}
        }
        Assert.assertTrue(actual);
    }

    public void checkTableHasEditAction() {
        String section = new BaseEditorPage(driver).getSelectedSection();
        boolean actual = false;
        switch (section) {
            case "Categories" -> {actual = new CategoriesPage(driver)
                    .getTableRowByNumber(1)
                    .getEditAction()
                    .isDisplayed();}
            case "Tags" -> {actual = new TagsPage(driver)
                    .getTableRowByNumber(1)
                    .getEditAction()
                    .isDisplayed();}
            case "Contexts" -> {actual = new ContextsPage(driver)
                    .getTableRowByNumber(1)
                    .getEditAction()
                    .isDisplayed();}
            case "Positions" -> {actual = new PositionsPage(driver)
                    .getTableRowByNumber(1)
                    .getEditAction()
                    .isDisplayed();}
        }
        Assert.assertTrue(actual);
    }

    public void clickRowDeleteActionButton(String name) {
        String section = new BaseEditorPage(driver).getSelectedSection();
        switch (section) {
            case "Categories" -> { new CategoriesPage(driver).deleteCategory(name);}
            case "Tags" -> { new TagsPage(driver).deleteTag(name);}
            case "Contexts" -> { new ContextsPage(driver).deleteContext(name);}
            case "Positions" -> { new PositionsPage(driver).deletePosition(name);}
        }
    }

    public void clickRowEditActionButton(String name) {
        String section = new BaseEditorPage(driver).getSelectedSection();
        switch (section) {
            case "Categories" -> { new CategoriesPage(driver).editCategory(name);}
            case "Tags" -> { new TagsPage(driver).editTag(name);}
            case "Contexts" -> { new ContextsPage(driver).editContext(name);}
            case "Positions" -> { new PositionsPage(driver).editPosition(name);}
        }
    }

    public void moveToAdminPanelIfNeed() {
        if (!(ScenarioContext.getProvider().getBaseUIUrl() + "/admin-panel").equals(driver.getCurrentUrl())) {
            driver.get(ScenarioContext.getProvider().getBaseUIUrl() + "/admin-panel");
        }
    }

}

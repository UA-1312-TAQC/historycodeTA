package com.historycode.cucumber.steps.editor;

import com.historycode.cucumber.contexts.ScenarioContext;
import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import com.historycode.ui.page.adminpanel.editorpage.*;
import com.historycode.ui.page.adminpanel.editorpage.components.modals.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

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
            case "Додати новий контекст" -> {new ContextsPage(driver).clickAddContext();}
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
                    new BaseEditorPage(driver).getDisplayedModalRoot()).save();
            }
            case "Tags" -> { new TagsModalComponent(driver,
                    new BaseEditorPage(driver).getDisplayedModalRoot()).save();
            }
            case "Contexts" -> { new ContextsModalComponent(driver,
                    new BaseEditorPage(driver).getDisplayedModalRoot()).save();
            }
            case "Positions" -> { new PositionsModalComponent(driver,
                    new BaseEditorPage(driver).getDisplayedModalRoot()).save();
            }
        }
    }

    @Given("I filled the modal title field with {string}.")
    @When("I fill the modal title field with {string}.")
    public void fillOutModalFieldWithData(String name) {
        String section = new BaseEditorPage(driver).getSelectedSection();
        switch (section) {
            case "Categories" -> { new CategoriesModalComponent(driver,
                    new BaseEditorPage(driver).getDisplayedModalRoot()).enterCategory(name);
            }
            case "Tags" -> { new TagsModalComponent(driver,
                    new BaseEditorPage(driver).getDisplayedModalRoot()).enterTag(name);
            }
            case "Contexts" -> { new ContextsModalComponent(driver,
                    new BaseEditorPage(driver).getDisplayedModalRoot()).enterContext(name);
            }
            case "Positions" -> { new PositionsModalComponent(driver,
                    new BaseEditorPage(driver).getDisplayedModalRoot()).enterPosition(name);
            }
        }
    }

    @Given("I searched the row with {string} name.")
    @When("I search the row with {string} name.")
    public void searchRowWithName(String name) {
        String section = new BaseEditorPage(driver).getSelectedSection();
        switch (section) {
            case "Categories" -> { new CategoriesPage(driver).moveToPageWithRow(name);
            }
            case "Tags" -> { new TagsPage(driver).moveToPageWithRow(name);
            }
            case "Contexts" -> { new ContextsPage(driver).moveToPageWithRow(name);
            }
            case "Positions" -> { new PositionsPage(driver).moveToPageWithRow(name);
            }
        }
    }


}

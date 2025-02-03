package com.historycode.ui.page.adminpanel.editorpage;

import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import com.historycode.ui.page.adminpanel.editorpage.components.SectionsComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;

public abstract class BaseEditorPage extends BasePageAdminPanel {

    @FindBy(xpath = "//div[@class='ant-tabs-content-holder']//div[@class='container-justify-end']")
    private List<WebElement> rootAddButtonAll;
    @FindBy(xpath = "//div[@class='ant-tabs-nav-list']")
    private WebElement rootSections;

    private WebElement rootAddButton;
    private SectionsComponent sections;

    public BaseEditorPage(WebDriver driver) {
        super(driver);
        setRootAddButton();
        sections = new SectionsComponent(driver, rootSections);
    }

    public void setRootAddButton() {
        rootAddButton = rootAddButtonAll.stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No visible element found"));
    }

    public WebElement getRootAddButton() {
        return rootAddButton;
    }

    public CategoriesPage moveToCategories() {
        sections.clickCategories();
        sleep(1000);
        return new CategoriesPage(driver);
    }

    public TagsPage moveToTags() {
        sections.clickTags();
        sleep(1000);
        return new TagsPage(driver);
    }

    public PositionsPage moveToPositions() {
        sections.clickPositions();
        sleep(1000);
        return new PositionsPage(driver);
    }

    public ContextsPage moveToContexts() {
        sections.clickContexts();
        sleep(1000);
        return new ContextsPage(driver);
    }

    public WebElement getDisplayedModalRoot() {
        String MODAL_XPATH = "//div[@class='ant-modal-content']";
        List<WebElement> rootElementAll = driver.findElements(By.xpath(MODAL_XPATH));
        return rootElementAll
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .orElse(null);
    }

    //TODO Add pagination component
}

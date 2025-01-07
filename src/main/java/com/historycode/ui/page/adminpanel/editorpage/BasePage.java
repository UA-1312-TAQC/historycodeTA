package com.historycode.ui.page.adminpanel.editorpage;

import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import com.historycode.ui.page.adminpanel.editorpage.components.SectionsComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;

public abstract class BasePage extends BasePageAdminPanel {
    private static final String ROOT_SECTIONS_XPATH = "//div[@class='ant-tabs-nav-list']";
    private static final String ROOT_ADD_BUTTON_XPATH = "//div[@class='ant-tabs-content-holder']//div[@class='container-justify-end']";
    private static final String ROOT_GRID_XPATH = "//div[contains(@class, 'ant-table-wrapper')]";

    @FindBy(xpath = ROOT_ADD_BUTTON_XPATH)
    private List<WebElement> rootAddButtonAll;
    @FindBy(xpath = ROOT_SECTIONS_XPATH)
    private WebElement rootSections;
    private final SectionsComponent sections = new SectionsComponent(driver, rootSections);
    @FindBy(xpath = ROOT_GRID_XPATH)
    private WebElement rootGrid;
    private WebElement rootAddButton;

    public BasePage(WebDriver driver) {
        super(driver);
        setRootAddButton();
    }

    public static void moveToElement(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
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

    public WebElement getRootGrid() {
        return rootGrid;
    }

    public CategoriesPage moveToCategories() throws InterruptedException {
        sections.clickCategories();
        //TODO Maybe add waiting
        return new CategoriesPage(driver);
    }

    public TagsPage moveToTags() throws InterruptedException {
        sections.clickTags();
        Thread.sleep(2000);
        return new TagsPage(driver);
    }

    public PositionsPage moveToPositions() throws InterruptedException {
        sections.clickPositions();
        Thread.sleep(2000);
        return new PositionsPage(driver);
    }

    public ContextsPage moveToContexts() throws InterruptedException {
        sections.clickContexts();
        Thread.sleep(2000);
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

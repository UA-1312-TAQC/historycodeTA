package com.historycode.ui.page.adminpanel.editorpage;

import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import com.historycode.ui.page.adminpanel.editorpage.components.SectionsComponent;
import com.historycode.ui.utils.customExpectedConditions.StalenessOfElementLocatedBy;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.NoSuchElementException;

public abstract class BaseEditorPage extends BasePageAdminPanel {

    @Getter
    private final String LOADING_GIF_XPATH = "//div[@id='loadingGif']";

    @FindBy(xpath = "//div[@class='ant-tabs-content-holder']//div[@class='container-justify-end']")
    private List<WebElement> addButtonsNodes;
    @Getter
    @FindBy(xpath = "//div[contains(@class, 'ant-table-wrapper')]")
    private List<WebElement> gridNodes;
    @Getter
    @FindBy(xpath = "//div[@class='ant-tabs-nav-list']")
    private WebElement sectionsNode;

    @Getter
    private WebElement addButtonNode;
    @Getter
    protected WebElement gridNode;
    private SectionsComponent sections;

    public BaseEditorPage(WebDriver driver) {
        super(driver);
        wait.until(new StalenessOfElementLocatedBy(By.xpath(getLOADING_GIF_XPATH())));
        setAddButtonNode();
        setGridNode();
        sections = new SectionsComponent(driver, sectionsNode);
    }

    public void setAddButtonNode() {
        addButtonNode = addButtonsNodes.stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No visible element found"));
    }

    public void setGridNode() {
        gridNode = gridNodes.stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No visible element found"));
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

        //ToDo Add waiter until new Position Page be fully loaded
        //ToDo Remove sleep
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

    @Override
    public void scrollToElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        threadJs.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    //TODO Add pagination component
}

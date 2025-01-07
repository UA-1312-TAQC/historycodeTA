package com.historycode.ui.page.adminpanel.editorpage.components.modals;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.page.adminpanel.editorpage.BasePage;
import com.historycode.ui.page.adminpanel.editorpage.elements.modalInputElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ModalComponent extends BaseComponent {
    private static final String CLOSE_XPATH = ".//button[@aria-label='Close']";
    private static final String TITLE_XPATH = ".//div[@class='ant-modal-title']";
    private static final String SAVE_XPATH = ".//button";
    public final modalInputElement inputComponent;
    @FindBy(xpath = CLOSE_XPATH)
    private WebElement closeButton;
    @FindBy(xpath = TITLE_XPATH)
    private WebElement title;
    @FindBy(xpath = SAVE_XPATH)
    private WebElement saveButton;

    public ModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        inputComponent = new modalInputElement(driver, rootElement);
    }

    public void close() {
        BasePage.moveToElement(driver, closeButton);
        closeButton.click();
    }

    public void save() {
        saveButton.click();
    }

    public WebElement getTitle() {
        return title;
    }

    public WebElement getCloseButton() {
        return closeButton;
    }

    public WebElement getSaveButton() {
        return saveButton;
    }

    public String getTitleString() {
        return title.getText();
    }

    public String getSaveButtonText() {
        return saveButton.getText();
    }

    public String getInputTitleString() {
        return inputComponent.getLabelString();
    }
}

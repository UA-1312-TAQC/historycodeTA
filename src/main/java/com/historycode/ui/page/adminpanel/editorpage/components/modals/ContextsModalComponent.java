package com.historycode.ui.page.adminpanel.editorpage.components.modals;

import com.historycode.ui.page.adminpanel.editorpage.elements.modalInputElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContextsModalComponent extends ModalComponent {

    @FindBy(xpath = ".//div[@class='center']/h2")
    private WebElement title;

    public ContextsModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void enterContext(String name) {
        inputComponent.setInput(name);
    }

    public boolean isExist() {
        return closeButton.isDisplayed() && title.isDisplayed() && saveButton.isDisplayed();
    }

    public WebElement getTitle() {
        return title;
    }

    public String getTitleString() {
        return title.getText();
    }
}

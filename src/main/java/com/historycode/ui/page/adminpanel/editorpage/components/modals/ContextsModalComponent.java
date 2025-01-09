package com.historycode.ui.page.adminpanel.editorpage.components.modals;

import com.historycode.ui.page.adminpanel.editorpage.elements.modalInputElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContextsModalComponent extends ModalComponent {
    private static final String TITLE_XPATH = ".//div[@class='center']//h2";

    @FindBy(xpath = TITLE_XPATH)
    private WebElement title;

    public ContextsModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void enterContext(String name) {
        inputComponent.setInput(name);
    }

    public WebElement getTitle() {
        return title;
    }

    public String getTitleString() {
        return title.getText();
    }
}

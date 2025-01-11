package com.historycode.ui.page.adminpanel.editorpage.components.modals;

import com.historycode.ui.component.adminPanel.modalAdminPanel.BaseEditModal;
import com.historycode.ui.elements.adminPanel.InputElement;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PositionsModalComponent extends BaseEditModal {
    @FindBy(xpath = "//label[@for = 'position']/../..")
    private WebElement positionContainer;

    @Getter
    private InputElement positionInput;

    public PositionsModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.positionInput = new InputElement(driver, positionContainer);
    }

    public PositionsModalComponent inputNewPosition(String name) {
        positionInput.setInputField(name);
        return this;
    }

    public PositionsModalComponent saveNewPosition() throws InterruptedException {
        clickSaveButton();
        Thread.sleep(4000);
        return this;
    }
}

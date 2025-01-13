package com.historycode.ui.page.adminpanel.editorpage.components.modals;

import com.historycode.ui.component.adminPanel.modalAdminPanel.BaseEditModal;
import com.historycode.ui.elements.adminPanel.InputElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PositionsModalComponent extends BaseEditModal {
    //TODO here also ./ doesnt work
    @FindBy(xpath = "//label[@for = 'position']/../..")
    private WebElement positionContainer;

    @Getter
    private InputElement positionInput;

    public PositionsModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.positionInput = new InputElement(driver, positionContainer);
    }

    @Step("Input new position value")
    public PositionsModalComponent inputNewPosition(String name) {
        positionInput.setInputField(name);
        return this;
    }

    @Step("Save new position")
    public PositionsModalComponent saveNewPosition() throws InterruptedException {
        clickSaveButton();
        Thread.sleep(4000);
        return this;
    }

    @Step("Close position modal")
    public void closeModal(){
        clickCloseButton();
    }
}

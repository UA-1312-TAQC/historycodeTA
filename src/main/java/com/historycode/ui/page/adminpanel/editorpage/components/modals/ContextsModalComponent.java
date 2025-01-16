package com.historycode.ui.page.adminpanel.editorpage.components.modals;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContextsModalComponent extends ModalComponent {
    @FindBy(xpath = ".//div[@class='center']/h2")
    private WebElement title;

    public ContextsModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Enter '{name}' Into The Context Name Input.")
    public ContextsModalComponent enterContext(String name) {
        inputComponent.setInput(name);
        return new ContextsModalComponent(driver, rootElement);
    }

    @Step("Check Context Modal is Displayed.")
    public boolean isExist() {
        return closeButton.isDisplayed() && title.isDisplayed() && saveButton.isDisplayed();
    }

    public WebElement getTitle() {
        return title;
    }

    public String getTitleString() {
        return title.getText();
    }

    @Step("Click Contexts Modal Save Button.")
    public ContextsModalComponent save() {
        sleep(1000);
        saveButton.click();
        sleep(1000);
        return new ContextsModalComponent(driver, rootElement);
    }
}

//public class ContextsModalComponent extends BaseEditModal {
//
//    @FindBy(xpath = "//label[@for = 'context']/../..")
//    private WebElement contextContainer;
//
//    private InputElement contextInput;
//
//    public ContextsModalComponent(WebDriver driver, WebElement rootElement) {
//        super(driver, rootElement);
//        this.contextInput = new InputElement(driver, contextContainer);
//    }
//
//    public ContextsModalComponent inputNewContext(String name) {
//        contextInput.setInputField(name);
//        return this;
//    }
//
//    public ContextsModalComponent saveNewContext() {
//        clickSaveButton();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.invisibilityOf(rootElement));
//        return this;
//    }
//
//    public void closeModal() {
//        clickCloseButton();
//    }
//}

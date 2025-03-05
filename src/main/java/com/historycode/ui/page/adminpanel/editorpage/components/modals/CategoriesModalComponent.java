package com.historycode.ui.page.adminpanel.editorpage.components.modals;

import com.historycode.ui.page.adminpanel.editorpage.CategoriesPage;
import com.historycode.ui.page.adminpanel.editorpage.PositionsPage;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.CategoriesRowComponent;
import com.historycode.ui.page.adminpanel.editorpage.components.rows.PositionsRowComponent;
import com.historycode.ui.page.adminpanel.editorpage.elements.InputImageElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.historycode.ui.utils.ImageLoader.loadImageUsingRelativePath;

public class CategoriesModalComponent extends BaseCreateEditModalComponent {

    @Getter
    @FindBy(xpath = ".//div[@class='ant-modal-title']")
    private WebElement title;

    @Getter
    private InputImageElement inputImageComponent;

    public CategoriesModalComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        inputImageComponent = new InputImageElement(driver, rootElement);
    }

    @Step("Enter '{name}' Into The Category Name Input.")
    public CategoriesModalComponent enterCategory(String name) {
        inputComponent.setInputField(name);
        return new CategoriesModalComponent(driver, rootElement);
    }

    @Step("Check Category Modal is Displayed.")
    public boolean isExist() {
        return closeButton.isDisplayed() && title.isDisplayed() && saveButton.isDisplayed();
    }

    public String getTitleString() {
        return title.getText();
    }

    @Step("Click Categories Modal Save Button.")
    public CategoriesModalComponent save() {
        saveButton.click();
        sleep(2000); //ToDo Remove it
        return new CategoriesModalComponent(driver, rootElement);
    }

    @Step("Insert image into the image input.")
    public CategoriesModalComponent enterImage(String imagePath) {
        inputImageComponent.uploadImage(imagePath);
        return new CategoriesModalComponent(driver, rootElement);
    }

}

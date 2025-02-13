package com.historycode.ui.page.adminpanel.editorpage.components.rows;

import com.historycode.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RowComponent extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//td[@class='ant-table-cell'][1]//div")
    private WebElement title;

    public RowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getTitleString() {
        return title.getText();
    }
}

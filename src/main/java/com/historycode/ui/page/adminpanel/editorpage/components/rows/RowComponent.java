package com.historycode.ui.page.adminpanel.editorpage.components.rows;

import com.historycode.ui.component.BaseComponent;
import jdk.jfr.Frequency;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RowComponent extends BaseComponent {

    @FindBy(xpath = ".//td[@class='ant-table-cell'][1]//div")
    private WebElement title;

    public RowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getTitleString() {
        return title.getText();
    }

    public WebElement getTitle() {
        return title;
    }
}

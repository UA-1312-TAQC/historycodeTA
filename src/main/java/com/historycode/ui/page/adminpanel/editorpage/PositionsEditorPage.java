package com.historycode.ui.page.adminpanel.editorpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PositionsEditorPage extends BaseEditorPage{
    @FindBy(xpath="//div[@class = 'container-justify-end']//button[contains(@class, 'positions')]")
    WebElement addNewPositionButton;

    public PositionsEditorPage(WebDriver driver){
        super(driver);
    }
}

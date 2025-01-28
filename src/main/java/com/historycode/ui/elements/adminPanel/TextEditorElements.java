package com.historycode.ui.elements.adminPanel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TextEditorElements {

    @FindBy(xpath = "//button[contains(@class, 'ql-bold')]")
    public WebElement boldIcon;

    @FindBy(xpath = "//button[contains(@class, 'ql-italic')]")
    public WebElement italicIcon;

    @FindBy(xpath = "//button[contains(@class, 'ql-strike')]")
    public WebElement strikethroughIcon;

    @FindBy(xpath = "//button[contains(@class, 'ql-underline')]")
    public WebElement underlineIcon;

    @FindBy(xpath = "//button[contains(@class, 'ql-clear')]")
    public WebElement clearTextFormatIcon;

    @FindBy(xpath = "//button[contains(@class, 'ql-list') and @value='ordered']")
    public WebElement numberedListIcon;

    @FindBy(xpath = "//button[contains(@class, 'ql-list') and @value='bullet']")
    public WebElement bulletedListIcon;

    public TextEditorElements(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickTextEditorButton(String button) {
        WebElement buttonElement = null;
        switch (button.toLowerCase()) {
            case "bold":
                buttonElement = boldIcon;
                break;
            case "italic":
                buttonElement = italicIcon;
                break;
            case "strikethrough":
                buttonElement = strikethroughIcon;
                break;
            case "underline":
                buttonElement = underlineIcon;
                break;
            case "clear":
                buttonElement = clearTextFormatIcon;
                break;
            case "numberedlist":
                buttonElement = numberedListIcon;
                break;
            case "bulletedlist":
                buttonElement = bulletedListIcon;
                break;
            default:
                throw new IllegalArgumentException("Unknown button: " + button);
        }
        if (buttonElement != null) {
            buttonElement.click();
        }
    }
}

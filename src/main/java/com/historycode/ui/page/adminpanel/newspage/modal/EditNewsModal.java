package com.historycode.ui.page.adminpanel.newspage.modal;

import com.historycode.ui.component.adminPanel.modalAdminPanel.BaseEditModal;
import com.historycode.ui.elements.adminPanel.InputElement;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.sql.Date;

public class EditNewsModal extends BaseEditModal {
    @FindBy(xpath = "//label[@for = 'title']/../..")
    private WebElement newsTitleContainer;
    @Getter
    private final InputElement newsTitle;

    @FindBy(xpath = "//label[@for = 'url']/../..")
    private WebElement newsLinkTranslitContainer;
    @Getter
    private final InputElement newsLinkTranslit;

    @FindBy(xpath = "//label[@for = 'editor']/../..")
    @Getter
    @Setter
    private WebElement newsTextEditor;

    @FindBy(xpath = "//a[@class='ant-upload-list-item-thumbnail']//img")
    private WebElement newsPhoto;

    @FindBy(xpath = "//span[@role='img' and @aria-label='eye']")
    private WebElement prevNewsPhoto;

    @FindBy(xpath = "//button[@title='Remove file' and contains(@class, 'ant-btn-icon-only')]")
    private WebElement deleteNewsPhoto;

    @FindBy(xpath = "//div[@class='ant-upload ant-upload-select']/span[@role='button']")
    private WebElement uploadNews;

    @FindBy(xpath = "//label[@for = 'creationDate']/../..")
    private WebElement newsCreationDateContainer;
    @Getter
    private final InputElement newsCreationDate;

    @FindBy(xpath = "//button[contains(@class, 'ql-bold')]") 
    private WebElement boldIcon;

    @FindBy(xpath = "//button[contains(@class, 'ql-italic')]") 
    private WebElement italicIcon;

    @FindBy(xpath = "//button[contains(@class, 'ql-strike')]")  
    private WebElement strikethroughIcon;

    @FindBy(xpath = "//button[contains(@class, 'ql-underline')]")  
    private WebElement underlineIcon;

    @FindBy(xpath = "//button[contains(@class, 'ql-clear')]")  
    private WebElement clearTextFormatIcon;

    @FindBy(xpath = "//button[contains(@class, 'ql-list') and @value='ordered']")  
    private WebElement numberedListIcon;

    @FindBy(xpath = "//button[contains(@class, 'ql-list') and @value='bullet']")  
    private WebElement bulletedListIcon;

    @FindBy(xpath = "//button[span[text()='Зберегти']]")
    private WebElement saveButton;

    @FindBy(xpath = "//button[@aria-label='Close']")
    private WebElement closeButton;


    public EditNewsModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.newsTitle = new InputElement(driver, newsTitleContainer);
        this.newsLinkTranslit = new InputElement(driver, newsLinkTranslitContainer);
        this.newsCreationDate = new InputElement(driver, newsCreationDateContainer);
    }

    public void inputNewsTitle(String newsTitle) {
        this.newsTitle.getInputField().clear();
        this.newsTitle.setInputField(newsTitle);
    }

    public void inputNewsLinkTranslit(String newsLink) {
        this.newsLinkTranslit.getInputField().clear();
        this.newsLinkTranslit.setInputField(newsLink);
    }

    public void inputNewsTextEditor(String newsText) {
        newsTextEditor.clear();
        newsTextEditor.sendKeys(newsText);
    }

    public void clickPrevPhoto() {
        prevNewsPhoto.click();
    }

    public void clickDeletePhoto() {
        deleteNewsPhoto.click();
    }

    public void clickUploadNews() {
        uploadNews.click();
    }

    public void inputNewsCreationDate(Date newsCreationDate) {
        this.newsCreationDate.getInputField().clear();
        this.newsCreationDate.setInputField(newsCreationDate.toString());
    }

    public void clickBoldIcon() {
        boldIcon.click();
    }

    public void clickItalicIcon() {
        italicIcon.click();
    }

    public void clickStrikethroughIcon() {
        strikethroughIcon.click();
    }

    public void clickUnderlineIcon() {
        underlineIcon.click();
    }

    public void clickClearTextFormatIcon() {
        clearTextFormatIcon.click();
    }

    public void clickNumberedListIcon() {
        numberedListIcon.click();
    }

    public void clickBulletedListIcon() {
        bulletedListIcon.click();
    }

    public void saveNews() {
        saveButton.click();
    }

    public void clickCloseButton() {
        closeButton.click();
        waitUntilModalIsClosed();
    }

    private void waitUntilModalIsClosed() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


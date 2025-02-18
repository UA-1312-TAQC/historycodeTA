package com.historycode.ui.page.adminpanel.newspage.modal;

import com.historycode.ui.component.adminPanel.modalAdminPanel.BaseCreateEditModal;
import com.historycode.ui.elements.adminPanel.InputElement;
import com.historycode.ui.elements.adminPanel.TextEditorElements;
import lombok.Getter;
import lombok.Setter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.sql.Date;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

public class CreateEditNewsModal extends BaseCreateEditModal {

    private TextEditorElements textEditorElements;

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
    private WebElement uploadNewsPhoto;

    @FindBy(xpath = "//label[@for = 'creationDate']/../..")
    private WebElement newsCreationDateContainer;
    @Getter
    private final InputElement newsCreationDate;

    @FindBy(xpath = "//button[span[text()='Зберегти']]")
    private WebElement saveButton;

    @FindBy(xpath = "//button[@aria-label='Close']")
    private WebElement closeButton;

    @FindBy(xpath = "//div[contains(@class, 'ant-form-item-explain-error')]")
    private WebElement newsTitleErrorMessage;

    @FindBy(xpath = "//div[contains(@class, 'ant-form-item-explain-error')]")
    private WebElement newsLinkTranslitErrorMessage;

    protected PhotoModalComponent photoModalComponent;

    public CreateEditNewsModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.newsTitle = new InputElement(driver, newsTitleContainer);
        this.newsLinkTranslit = new InputElement(driver, newsLinkTranslitContainer);
        this.newsCreationDate = new InputElement(driver, newsCreationDateContainer);
        this.photoModalComponent = new PhotoModalComponent(driver, rootElement);

        this.textEditorElements = new TextEditorElements(driver);
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

    public void inputNewsCreationDate(Date newsCreationDate) {
        this.newsCreationDate.getInputField().clear();
        this.newsCreationDate.setInputField(newsCreationDate.toString());
    }

    public void clickTextEditorButton(String button) {
        textEditorElements.clickTextEditorButton(button);
    }

    public void saveNews() {
        saveButton.click();
    }

    public void clickCloseButton() {
        closeButton.click();
        waitUntilModalIsClosed();
    }

    private void waitUntilModalIsClosed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(closeButton));
    }

    public void clickUploadNewsPhoto(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist: " + filePath);
        }
        uploadNewsPhoto.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='ant-upload ant-upload-select']//input[@type='file']")));
        fileInput.sendKeys(filePath);
        waitUntilPhotoIsUploaded();
    }

    public boolean isNewsPhotoPresent() {
        return newsPhoto.isDisplayed();
    }

    public String getNewsPhotoURL() {
        return newsPhoto.getDomAttribute("src");
    }

    public void waitUntilPhotoIsUploaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(newsPhoto));
    }

    public boolean isNewsPhotoRemoved() {
        try {
            return !newsPhoto.isDisplayed();
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    public void closePhotoModal() {
        photoModalComponent.close();
    }

    public boolean isPlaceholderClickable() throws TimeoutException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement placeholderIcon = driver.findElement(By.xpath("//span[@role='img' and contains(@class, 'anticon-picture')]"));
        wait.until(ExpectedConditions.elementToBeClickable(placeholderIcon));
        return true;
    }

    public boolean isPhotoUploaded() {
        try {
            return newsPhoto.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickPreviewButton() {
        prevNewsPhoto.click();
        photoModalComponent.waitForModalToAppear();
    }

    public void clickDeleteButton() {
        deleteNewsPhoto.click();
    }

public TextEditorElements getTextEditorElements() {
        return textEditorElements;
    }

    public String getNewsTitleErrorMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(newsTitleErrorMessage));
            return newsTitleErrorMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getNewsLinkTranslitErrorMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(newsLinkTranslitErrorMessage));
            return newsLinkTranslitErrorMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }
}

package com.historycode.ui.page.adminpanel.newspage.modal;

import com.historycode.ui.component.adminPanel.modalAdminPanel.BaseEditModal;
import com.historycode.ui.elements.adminPanel.InputElement;
import com.historycode.ui.elements.adminPanel.TextEditorButtonLocators;

import lombok.Getter;
import lombok.Setter;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Date;
import java.time.Duration;

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
    private WebElement uploadNewsPhoto;

    @FindBy(xpath = "//label[@for = 'creationDate']/../..")
    private WebElement newsCreationDateContainer;
    @Getter
    private final InputElement newsCreationDate;

    @FindBy(xpath = TextEditorButtonLocators.BOLD_ICON)
    private WebElement boldIcon;

    @FindBy(xpath = TextEditorButtonLocators.ITALIC_ICON)
    private WebElement italicIcon;

    @FindBy(xpath = TextEditorButtonLocators.STRIKETHROUGH_ICON)
    private WebElement strikethroughIcon;

    @FindBy(xpath = TextEditorButtonLocators.UNDERLINE_ICON)
    private WebElement underlineIcon;

    @FindBy(xpath = TextEditorButtonLocators.CLEAR_TEXT_FORMAT_ICON)
    private WebElement clearTextFormatIcon;

    @FindBy(xpath = TextEditorButtonLocators.NUMBERED_LIST_ICON)
    private WebElement numberedListIcon;

    @FindBy(xpath = TextEditorButtonLocators.BULLETED_LIST_ICON)
    private WebElement bulletedListIcon;

    @FindBy(xpath = "//button[span[text()='Зберегти']]")
    private WebElement saveButton;

    @FindBy(xpath = "//button[@aria-label='Close']")
    private WebElement closeButton;

    @FindBy(xpath = "//div[contains(@class, 'ant-form-item-explain-error')]")
    private WebElement newsLinkTranslitErrorMessage;

    protected PhotoModalComponent photoModalComponent;

    public EditNewsModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.newsTitle = new InputElement(driver, newsTitleContainer);
        this.newsLinkTranslit = new InputElement(driver, newsLinkTranslitContainer);
        this.newsCreationDate = new InputElement(driver, newsCreationDateContainer);
        this.photoModalComponent = new PhotoModalComponent(driver, rootElement);
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
        uploadNewsPhoto.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='ant-upload ant-upload-select']//input[@type='file']")));
        fileInput.sendKeys(filePath);
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

    public boolean isPlaceholderClickable() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement placeholderIcon = driver.findElement(By.xpath("//span[@role='img' and contains(@class, 'anticon-picture')]"));
            wait.until(ExpectedConditions.elementToBeClickable(placeholderIcon));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
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

    public String getNewsLinkTranslitErrorMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(newsLinkTranslitErrorMessage));
            return newsLinkTranslitErrorMessage.getText();
        } catch (Exception e) {
            return ""; // or throw a custom exception depending on your error handling strategy
        }
    }
}

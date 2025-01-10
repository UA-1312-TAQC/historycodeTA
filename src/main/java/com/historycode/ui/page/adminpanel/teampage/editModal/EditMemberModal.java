package com.historycode.ui.page.adminpanel.teampage.editModal;

import com.historycode.ui.component.adminPanel.dropDownAdminPanel.DropdownComponent;
import com.historycode.ui.component.adminPanel.modalAdminPanel.BaseEditModal;
import com.historycode.ui.elements.adminPanel.CheckboxElement;
import com.historycode.ui.elements.adminPanel.InputElement;
import com.historycode.ui.page.adminpanel.teampage.editModal.photoElement.PhotoModalComponent;
import com.historycode.ui.page.adminpanel.teampage.editModal.photoElement.PhotoWindowComponent;
import com.historycode.ui.page.adminpanel.teampage.editModal.socialMediaElement.SocialMediaExistedComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Getter
public class EditMemberModal extends BaseEditModal {

    @FindBy(xpath = "//label[contains(@class, 'ant-checkbox-wrapper ant-checkbox-wrapper-checked ant-checkbox-wrapper-in-form-item css-k7429z')]/../..")
    protected WebElement keyMemberCheckboxRoot;
    protected CheckboxElement keyMemberCheckbox;

    @FindBy(xpath = "//label[@for = 'name']/../..")
    protected WebElement nameInputRoot;
    protected InputElement nameInput;

    @FindBy(xpath = "//label[normalize-space(text())='Позиції']/../..")
    protected WebElement positionsDropdownRoot;
    protected DropdownComponent positionsDropdown;
    protected By SELECTED_POSITIONS_PATH = By.xpath("//div[@class='ant-select-selection-overflow']");

    @FindBy(xpath = "//label[@for = 'description']/../..")
    protected WebElement descriptionTextareaElementRoot;
    protected InputElement descriptionTextareaElement;

    @FindBy(xpath = "//a[@class='ant-upload-list-item-thumbnail']//img")
    protected WebElement uploadedPhoto;

    @FindBy(xpath = "//span[@role='img' and @aria-label='eye']")
    protected WebElement previewPhotoButton;

    @FindBy(xpath = "//button[@title='Remove file' and contains(@class, 'ant-btn-icon-only')]")
    protected WebElement deletePhotoButton;

    @FindBy(xpath = "//div[@class='ant-upload ant-upload-select']/span[@role='button']")
    protected WebElement uploadButton;

    @FindBy(xpath = "//div[@class='team-source-list']//div[contains(@class, 'link-container')]")
    protected List<WebElement> existingSocialMedia;

    @FindBy(xpath = "//label[@for = 'logotype']/../..")
    protected WebElement socialMediaDropdownRoot;
    protected DropdownComponent socialMediaDropdown;

    @FindBy(xpath = "//label[@for = 'url']/../..")
    protected WebElement socialMediaInputRoot;
    protected InputElement socialMediaInput;

    @FindBy(xpath = "//button[@type='submit']")
    protected WebElement addSocialMediaButton;

    protected PhotoModalComponent photoModalComponent;
    protected PhotoWindowComponent photoWindowComponent;

    public EditMemberModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(driver, this);

        this.keyMemberCheckbox = new CheckboxElement(driver, keyMemberCheckboxRoot);
        this.nameInput = new InputElement(driver, nameInputRoot);
        this.positionsDropdown = new DropdownComponent(driver, positionsDropdownRoot);
        this.descriptionTextareaElement = new InputElement(driver, descriptionTextareaElementRoot);
        this.socialMediaDropdown = new DropdownComponent(driver, socialMediaDropdownRoot);
        this.socialMediaInput = new InputElement(driver, socialMediaInputRoot);

        this.photoModalComponent = new PhotoModalComponent(driver, rootElement);
        this.photoWindowComponent = new PhotoWindowComponent(driver, rootElement);
    }

    public void setKeyMemberStatus(boolean isKeyMember) {
        if (isKeyMember) {
            keyMemberCheckbox.check();
        } else {
            keyMemberCheckbox.uncheck();
        }
    }

    public boolean isKeyMemberChecked() {
        return keyMemberCheckbox.isChecked();
    }

    public void setName(String name) {
        nameInput.setInputField(name);
    }

    public String getName() {
        return nameInput.getInputField();
    }

    public void setPositions(List<String> positions) {
        positionsDropdown.openDropdown();
        positionsDropdown.selectMultipleOptions(positions);
    }

    public List<String> getSelectedPositions() {
        return positionsDropdown.getSelectedMultipleOptions();
    }

    public void setDescription(String description) {
        descriptionTextareaElement.setInputField(description);
    }

    public String getDescription() {
        return descriptionTextareaElement.getInputField();
    }

    public void previewPhoto() {
        if (photoWindowComponent.isPhotoUploaded()) {
            photoWindowComponent.clickPreviewButton();
        }
    }

    public void deletePhoto() {
        if (photoWindowComponent.isPhotoUploaded()) {
            photoWindowComponent.clickDeleteButton();
        }
    }

    public void closePhotoModal() {
        photoModalComponent.close();
    }

    public void uploadFile(String filePath) {
        uploadButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='ant-upload ant-upload-select']//input[@type='file']")));
        fileInput.sendKeys(filePath);
    }

    //TODO Do I need to check uploadedPhoto.isDisplayed(), too?
    public boolean isPhotoUploaded() {
        return !photoWindowComponent.isPlaceholderClickable(); //&& !uploadedPhoto.isDisplayed();
    }

    public void addSocialMedia(String platform, String link) {
        selectDropdownOption(socialMediaDropdown, platform);
        socialMediaInput.setInputField(link);
        addSocialMediaButton.click();
    }

    public void navigateToSocialMediaAccount(int index) {
        if (index >= 0 && index < existingSocialMedia.size()) {
            SocialMediaExistedComponent socialMediaComponent = new SocialMediaExistedComponent(driver, existingSocialMedia.get(index));
            socialMediaComponent.navigateToSocialMediaAccount();
        } else {
            throw new IndexOutOfBoundsException("Invalid social media index: " + index);
        }
    }

    public void deleteSocialMedia(int index) {
        if (index >= 0 && index < existingSocialMedia.size()) {
            SocialMediaExistedComponent socialMediaComponent = new SocialMediaExistedComponent(driver, existingSocialMedia.get(index));
            socialMediaComponent.deleteSocialMedia();
        } else {
            throw new IndexOutOfBoundsException("Invalid social media index: " + index);
        }
    }

    public List<String> getExistingSocialMediaLinks() {
        List<String> socialMediaLinks = new ArrayList<>();
        for (WebElement socialMediaElement : existingSocialMedia) {
            SocialMediaExistedComponent socialMediaComponent = new SocialMediaExistedComponent(driver, socialMediaElement);
            socialMediaLinks.add(socialMediaComponent.getLink());
        }
        return socialMediaLinks;
    }

    private void selectDropdownOption(DropdownComponent dropdown, String optionText) {
        dropdown.clickOptionByText(optionText);
    }

    public EditMemberModal saveEditedMember() {
        clickSaveButton();
        return this;
    }

    public void closeEditMemberModal() {
        clickCloseButton();
    }

    public boolean isTooltipVisibleOnHoverCloseButton() {
        hoverOverCloseButton();
        return isTooltipVisible();
    }

    public String getTextFromTooltip() {
        return getTooltipText();
    }

}

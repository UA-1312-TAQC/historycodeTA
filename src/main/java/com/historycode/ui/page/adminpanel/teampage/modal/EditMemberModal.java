package com.historycode.ui.page.adminpanel.teampage.modal;

import com.historycode.ui.component.adminPanel.dropDownAdminPanel.DropdownComponent;
import com.historycode.ui.component.adminPanel.modalAdminPanel.BaseEditModal;
import com.historycode.ui.elements.adminPanel.CheckboxElement;
import com.historycode.ui.elements.adminPanel.InputElement;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    //TODO xpath to the 'team-source-list' or to a specific 'link-container'?
    // What about the buttons on the 'link-container'?
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


    public EditMemberModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(driver, this);

        this.keyMemberCheckbox = new CheckboxElement(driver, keyMemberCheckboxRoot);
        this.nameInput = new InputElement(driver, nameInputRoot);
        this.positionsDropdown = new DropdownComponent(driver, positionsDropdownRoot);
        this.descriptionTextareaElement = new InputElement(driver, descriptionTextareaElementRoot);
        this.socialMediaDropdown = new DropdownComponent(driver, socialMediaDropdownRoot);
        this.socialMediaInput = new InputElement(driver, socialMediaInputRoot);
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

    //TODO Do I need getters?
    public void setName(String name) {
        nameInput.setInputField(name);
    }

    public String getName() {
        return nameInput.getInputField().getAttribute("value");
    }

    public void setPositions(List<String> positions) {
        positionsDropdown.openDropdown();
        for (String position : positions) {
            positionsDropdown.clickOptionByText(position);
        }
    }

    public List<String> getPositions() {
        List<String> selectedPositions = new ArrayList<>();

        // Locate selected items directly from the dropdown's container
        List<WebElement> selectedTags = positionsDropdownRoot.findElements(SELECTED_POSITIONS_PATH);

        for (WebElement tag : selectedTags) {
            selectedPositions.add(tag.getText().trim());
        }
        return selectedPositions;
    }


    public void setDescription(String description) {
        descriptionTextareaElement.setInputField(description);
    }

    public String getDescription() {
        return descriptionTextareaElement.getAttribute("value");
    }

    //TODO Is it necessary?
    public boolean isUploadedPhotoVisible() {
        try {
            // Check if the uploaded photo is displayed on the page
            return uploadedPhoto.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void deletePhoto() {
        deletePhotoButton.click();
    }

    public void previewPhoto() {
        previewPhotoButton.click();
    }

    //TODO Do I need to implement both clicking and dragging the photo?
    public void uploadFile(String filePath) {
        uploadButton.click();
        fileInput.sendKeys(filePath);
    }

    public void addSocialMedia(String platform, String link) {
        selectDropdownOption(socialMediaDropdown, platform);
        socialMediaInput.setInputField(link);
        addSocialMediaButton.click();
    }

    public void deleteSocialMedia(int index) {
        WebElement deleteButton = existingSocialMedia.get(index).findElement(By.className("anticon anticon-delete"));
        deleteButton.click();
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

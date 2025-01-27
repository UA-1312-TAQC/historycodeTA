package com.historycode.ui.page.adminpanel.teampage.createEditModal;

import com.historycode.ui.component.adminPanel.dropDownAdminPanel.DropdownComponent;
import com.historycode.ui.component.adminPanel.modalAdminPanel.BaseEditModal;
import com.historycode.ui.elements.adminPanel.CheckboxElement;
import com.historycode.ui.elements.adminPanel.InputElement;
import com.historycode.ui.elements.adminPanel.TextAreaElement;
import com.historycode.ui.page.adminpanel.teampage.TeamPageAdminPanel;
import com.historycode.ui.page.adminpanel.teampage.createEditModal.photoElement.PhotoModalComponent;
import com.historycode.ui.page.adminpanel.teampage.createEditModal.photoElement.PhotoWindowComponent;
import com.historycode.ui.page.adminpanel.teampage.createEditModal.socialMediaElement.SocialMediaExistedComponent;
import com.historycode.ui.utils.ImageLoader;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


@Getter
public class CreateEditMemberModal extends BaseEditModal {

    @FindBy(xpath = ".//label[contains(@class, 'ant-checkbox-wrapper ant-checkbox-wrapper-checked ant-checkbox-wrapper-in-form-item css-k7429z')]/../..")
    protected WebElement keyMemberCheckboxRoot;
    protected CheckboxElement keyMemberCheckbox;

    @FindBy(xpath = ".//label[@for = 'name']/../..")
    protected WebElement nameInputRoot;
    protected InputElement nameInput;
//TODO
    @FindBy(xpath = ".//input[@aria-label='Позиції']/../..")
    protected WebElement positionsDropdownRoot;
    protected SocialMediaDropdownComponent positionsDropdown;
    protected By SELECTED_POSITIONS_PATH = By.xpath("//div[@class='ant-select-selection-overflow']");

    @FindBy(xpath = ".//label[@for = 'description']/../..")
    protected WebElement descriptionTextareaElementRoot;
    protected TextAreaElement descriptionTextAreaElement;

    @FindBy(xpath = ".//span[@class='ant-upload']/input")
    protected WebElement photoInputField;

    @FindBy(xpath = ".//a[@class='ant-upload-list-item-thumbnail']//img")
    protected WebElement uploadedPhoto;

    @FindBy(xpath = ".//span[@role='img' and @aria-label='eye']")
    protected WebElement previewPhotoButton;

    @FindBy(xpath = ".//button[@title='Remove file' and contains(@class, 'ant-btn-icon-only')]")
    protected WebElement deletePhotoButton;

    @FindBy(xpath = ".//div[@class='ant-upload ant-upload-select']/span[@role='button']")
    protected WebElement uploadButton;

    @FindBy(xpath = ".//div[@class='team-source-list']//div[contains(@class, 'link-container')]")
    protected List<WebElement> existingSocialMedia;

    @FindBy(xpath = ".//label[@for = 'logotype']/../..")
    protected WebElement socialMediaDropdownRoot;
    protected SocialMediaDropdownComponent socialMediaDropdown;

    @FindBy(xpath = ".//label[@for = 'url']/../..")
    protected WebElement socialMediaInputRoot;
    protected InputElement socialMediaInput;

    @FindBy(xpath = ".//button[@type='submit']")
    protected WebElement addSocialMediaButton;

    protected PhotoModalComponent photoModalComponent;
    protected PhotoWindowComponent photoWindowComponent;

    public CreateEditMemberModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        //this.photoModalComponent = new PhotoModalComponent(driver, rootElement);
        //this.photoWindowComponent = new PhotoWindowComponent(driver, rootElement);
    }

    private CheckboxElement getKeyMemberCheckbox(){
        if(keyMemberCheckbox == null){
            this.keyMemberCheckbox = new CheckboxElement(driver, keyMemberCheckboxRoot);
        }
        return this.keyMemberCheckbox;
    }

    private InputElement getNameInput(){
        if(nameInput == null){
            this.nameInput = new InputElement(driver, nameInputRoot);
        }
        return this.nameInput;
    }
//TODO
    private SocialMediaDropdownComponent getPositionsDropdown(){
        if(positionsDropdown == null){
            this.positionsDropdown = new SocialMediaDropdownComponent(driver, positionsDropdownRoot);
        }
        return this.positionsDropdown;
    }

    private TextAreaElement getDescriptionTextAreaElement(){
        if(descriptionTextAreaElement == null){
            this.descriptionTextAreaElement = new TextAreaElement(driver, descriptionTextareaElementRoot);
        }
        return this.descriptionTextAreaElement;
    }

    private SocialMediaDropdownComponent getSocialMediaDropdown(){
        if(socialMediaDropdown == null){
            this.socialMediaDropdown = new SocialMediaDropdownComponent(driver, socialMediaDropdownRoot);
        }
        return this.socialMediaDropdown;
    }

    private InputElement getSocialMediaInput(){
        if(socialMediaInput == null){
            this.socialMediaInput = new InputElement(driver, socialMediaInputRoot);
        }
        return this.socialMediaInput;
    }

    public CreateEditMemberModal setKeyMemberStatus(boolean isKeyMember) {
        if (isKeyMember) {
            getKeyMemberCheckbox().check();
        } else {
            getKeyMemberCheckbox().uncheck();
        }
        return this;
    }

    public boolean isKeyMemberChecked() {
        return getKeyMemberCheckbox().isChecked();
    }

    public String getName() {
        return getNameInput().getInputValue();
    }

    @Step("Enter name {name} into Name input field")
    public CreateEditMemberModal setName(String name) {
        getNameInput().setInputField(name);
        return this;
    }

//    public void setPositions(List<String> positions) {
//        getPositionsDropdown().openDropdown();
//        positionsDropdown.selectMultipleOptions(positions);
//    }
//
//    public List<String> getSelectedPositions() {
//        return getPositionsDropdown().getSelectedMultipleOptions();
//    }

    public String getDescription() {
        return getDescriptionTextAreaElement().getInputValue();
    }

    @Step("Enter description {description} into Name input field")
    public CreateEditMemberModal setDescription(String description) {
        getDescriptionTextAreaElement().setInputField(description);
        return this;
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

    /*public void closePhotoModal() {
        photoModalComponent.close();
    }*/

    public void uploadFile(String filePath) {
        uploadButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='ant-upload ant-upload-select']//input[@type='file']")));
        fileInput.sendKeys(filePath);
    }

    @Step("Loading image {imageName} as a team member photo")
    public CreateEditMemberModal loadPhoto(String imageName){
        ImageLoader.loadImageUsingRelativePath(imageName, photoInputField);
        return this;
    }

    public boolean isPhotoUploaded() {
        return !photoWindowComponent.isPlaceholderClickable();
    }

    @Step("Choose social media {platform} from the social media dropdown")
    public CreateEditMemberModal addSocialMedia(String platform) {
        openSocialMediaDropdown();
        socialMediaDropdown.clickOptionByText(platform);
        return this;
    }
//TODO
    @Step("Choose positions {position} from the social media dropdown")
    public CreateEditMemberModal addPosition(String position) {
        openPositionsDropdown();
        positionsDropdown.clickOptionByText(position);
        return this;
    }

    @Step("Add social media link {link}")
    public CreateEditMemberModal addSocialMediaLink(String link) {
        getSocialMediaInput().setInputField(link);
        return this;
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

    @Step("Click the 'Зберегти' button")
    public CreateEditMemberModal saveEditedMember() {
        clickSaveButton();
        return this;
    }

    @Step("Close the modal window")
    public TeamPageAdminPanel closeEditMemberModal() {
        actions.moveToElement(closeButton).perform();
        waitUntilElementClickable(closeButton);
        clickCloseButton();
        wait.until(ExpectedConditions.invisibilityOf(closeButton));
        return new TeamPageAdminPanel(driver);
    }

    @Step("Close the modal window")
    public void closeEditMemberModalWithoutGridRefresh() {
        actions.moveToElement(closeButton).perform();
        waitUntilElementClickable(closeButton);
        clickCloseButton();
        wait.until(ExpectedConditions.invisibilityOf(closeButton));
    }

    public boolean isTooltipVisibleOnHoverCloseButton() {
        hoverOverCloseButton();
        return isTooltipVisible();
    }

    public String getTextFromTooltip() {
        return getTooltipText();
    }

    public void openSocialMediaDropdown(){
        getSocialMediaDropdown().openDropdown();
    }

    public void openPositionsDropdown(){
        getPositionsDropdown().openDropdownPosition();
    }
}

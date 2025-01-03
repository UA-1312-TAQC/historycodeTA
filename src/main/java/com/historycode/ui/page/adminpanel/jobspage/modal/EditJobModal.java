package com.historycode.ui.page.adminpanel.jobspage.modal;

import com.historycode.ui.component.adminPanel.dropDownAdminPanel.DropdownComponent;
import com.historycode.ui.component.adminPanel.modalAdminPanel.BaseEditModal;
import com.historycode.ui.elements.adminPanel.InputElement;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
@Setter
public class EditJobModal extends BaseEditModal {
    InputElement jobTitleInput;

    DropdownComponent jobStatusDropdown;

    @FindBy(xpath = "//div[contains(@class, 'ant-form-item-label')]")
    WebElement jobDescriptionLabel;

    @FindBy(xpath = "//div[@class='ql-editor ql-blank']")
    WebElement jobDescriptionTextArea;

    @FindBy(xpath = "//div[@class= 'editorInfoContainer']/div")
    WebElement charsCounter;

    InputElement salaryInput;

    public EditJobModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void inputNewJobTitle(String jobTitle) {
        jobTitleInput.getInputField().clear();
        jobTitleInput.getInputField().sendKeys(jobTitle);
    }

    public String getJobStatusDropdown() {
        return jobStatusDropdown.getSelectedOptionText();
    }

    public DropdownComponent setNewJobStatusDropdown(String newJobStatus) {
        jobStatusDropdown.clickOptionByText(newJobStatus);
        return jobStatusDropdown;
    }

    public void inputNewJobDescription(String jobTitle) {
        jobDescriptionTextArea.clear();
        jobDescriptionTextArea.sendKeys(jobTitle);
    }

    public void inputNewSalary(Float salary) {
        salaryInput.getInputField().clear();
        salaryInput.getInputField().sendKeys(String.valueOf(salary));
    }

    public EditJobModal saveEditedJob() {
        clickSaveButton();
        return this;
    }

    public void closeEditJobModal() {
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

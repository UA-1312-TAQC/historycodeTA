package com.historycode.ui.page.adminpanel.jobspage.modal;

import com.historycode.ui.component.adminPanel.dropDownAdminPanel.DropdownComponent;
import com.historycode.ui.component.adminPanel.modalAdminPanel.BaseEditModal;
import com.historycode.ui.elements.adminPanel.InputElement;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditJobModal extends BaseEditModal {
    @FindBy(xpath = "//label[@for = 'title']/../..")
    private WebElement titleContainer;

    @Getter
    private InputElement title;

    @FindBy(xpath = "//label[@for = 'status']/../..")
    private WebElement statusContainer;

    private DropdownComponent jobStatusDropdown;

    @Getter
    @FindBy(xpath = "//div[contains(@class, 'ant-form-item-label')]")
    private WebElement jobDescriptionLabel;

    @Getter
    @Setter
    @FindBy(xpath = "//div[@class='ql-editor ql-blank']")
    private WebElement jobDescriptionTextArea;

    @Getter
    @FindBy(xpath = "//div[@class= 'editorInfoContainer']/div")
    private WebElement charsCounter;

    @FindBy(xpath = "//label[@for = 'salary']/../..")
    private WebElement salaryContainer;

    @Getter
    private InputElement salary;

    public EditJobModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        this.title = new InputElement(driver, titleContainer);
        this.salary = new InputElement(driver, salaryContainer);
        this.jobStatusDropdown = new DropdownComponent(driver, statusContainer);
    }

    public void inputNewJobTitle(String jobTitle) {
        this.title.getInputField().clear();
        this.title.setInputField(jobTitle);
    }

    public String getJobStatusDropdown() {
        return jobStatusDropdown.getSelectedOptionText();
    }

    public DropdownComponent setNewJobStatusDropdown(String newJobStatus) {
        jobStatusDropdown.clickOptionByText(newJobStatus);
        return jobStatusDropdown;
    }

    public void inputNewJobDescription(String jobDescription) {
        jobDescriptionTextArea.clear();
        jobDescriptionTextArea.sendKeys(jobDescription);
    }

    public void inputNewSalary(Float salary) {
        this.salary.getInputField().clear();
        this.salary.setInputField(salary.toString());
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

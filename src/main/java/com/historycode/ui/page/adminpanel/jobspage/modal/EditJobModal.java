package com.historycode.ui.page.adminpanel.jobspage.modal;

import com.historycode.ui.component.BaseModal;
import com.historycode.ui.component.adminPanel.dropDownAdminPanel.DropdownComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditJobModal extends BaseModal {
    @FindBy(xpath = "//div[@class='center']/h2[text()='Вакансії']")
    public WebElement title;
    @FindBy(xpath = "//input[@id='title']")
    public WebElement JobTitle;
    public DropdownComponent jobStatus;
    @FindBy(xpath = "")
    public DropdownComponent jobDescription;
    @FindBy(xpath = "//input[@id='salary']")
    public WebElement salary;
    public EditJobModal(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
}

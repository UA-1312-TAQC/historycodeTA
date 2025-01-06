package com.historycode.ui.page.adminpanel.jobspage;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.component.adminPanel.dropDownAdminPanel.DropdownComponent;
import com.historycode.ui.component.adminPanel.modalAdminPanel.DeleteItemModal;
import com.historycode.ui.page.adminpanel.jobspage.modal.EditJobModal;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class JobsRowComponent extends BaseComponent {
    @Getter
    @FindBy(xpath = "//td[@class='ant-table-cell'][1]")
    WebElement title;

    @Getter
    @FindBy(xpath = "//td[@class='ant-table-cell'][2]")
    WebElement salary;

    @Getter
    DropdownComponent dropdownStatus;
    @FindBy(xpath = "//td[contains(@class, 'ant-table-cell')][4]//span[contains(@class, 'delete')]")
    WebElement deleteAction;

    @FindBy(xpath = "//td[contains(@class, 'ant-table-cell')][4]//span[contains(@class, 'edit')]")
    WebElement editAction;

    public JobsRowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void getJobStatus() {
        dropdownStatus.getSelectedOptionText();
    }

    public void setJobStatus(String status) {
        dropdownStatus.clickOptionByText(status);
    }

    public EditJobModal clickEdit() {
        editAction.click();
        return new EditJobModal(driver, rootElement);
    }

    public DeleteItemModal clickDelete() {
        deleteAction.click();
        return new DeleteItemModal(driver, rootElement);
    }
}

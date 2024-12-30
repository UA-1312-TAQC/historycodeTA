package com.historycode.ui.page.adminpanel.jobspage;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.component.adminPanel.dropDownAdminPanel.DropdownComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class JobsRowComponent extends BaseComponent {
    @FindBy(xpath = "//td[@class='ant-table-cell'][1]")
    WebElement title;
    @FindBy(xpath = "//td[@class='ant-table-cell'][2]")
    WebElement salary;
    DropdownComponent dropdownStatus;
    @FindBy(xpath = "//td[contains(@class, 'ant-table-cell')][4]//span[contains(@class, 'anticon-delete')]//*[name()='svg']")
    WebElement deleteAction;
    @FindBy(xpath = "//td[contains(@class, 'ant-table-cell')][4]//span[contains(@class, 'anticon-edit')]//*[name()='svg']")
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

    public void clickEdit() {
        editAction.click();
    }

    public void clickDelete() {
        deleteAction.click();
    }
}

package com.historycode.ui.page.adminpanel.jobspage;

import com.historycode.ui.component.adminPanel.modalAdminPanel.DeleteItemModal;
import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import com.historycode.ui.page.adminpanel.jobspage.modal.EditJobModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JobsPageAdminPanel extends BasePageAdminPanel {
    @FindBy(xpath = "//button[span[text()='Додати нову вакансію']]")
    WebElement addNewJobButton;

    @FindBy(xpath = "//div[@role = 'dialog']//div")
    private WebElement editModalRootElement;

    @FindBy(xpath = "//div[@class='partners-page']//div[@class='partners-page-container']")
    private WebElement gridRootElement;

    JobsPageGridComponent jobsPageGridComponent;

    public JobsPageAdminPanel(WebDriver driver) {
        super(driver);
        jobsPageGridComponent = new JobsPageGridComponent(driver, gridRootElement);
    }

    public EditJobModal clickEditJobById(int id) {
        JobsRowComponent jobToEdit = jobsPageGridComponent.getRowById(id);
        jobToEdit.clickEdit();
        return new EditJobModal(driver, editModalRootElement);
    }

    public DeleteItemModal clickDeleteJobById(int id) {
        JobsRowComponent jobToDelete = jobsPageGridComponent.getRowById(id);
        jobToDelete.clickDelete();
        return null;
        //TODO what rootElement should I pass here?
//        return new DeleteItemModal(driver);
    }

    public EditJobModal clickAddNewJobButton() {
        addNewJobButton.click();
        return new EditJobModal(driver, editModalRootElement);
    }

    public JobsPageAdminPanel clickNextPage() {
        jobsPageGridComponent.clickNextPage();
        return this;
    }

    public JobsPageAdminPanel clickPrevPage() {
        jobsPageGridComponent.clickPrevPage();
        return this;
    }

    public JobsPageAdminPanel clickPaginationItem(int index) {
        jobsPageGridComponent.clickPaginationItem(index);
        return this;
    }

    public JobsPageAdminPanel clickNextFivePages() {
        jobsPageGridComponent.clickNextFivePages();
        return this;
    }

    public JobsPageAdminPanel clickPrevFivePages() {
        jobsPageGridComponent.clickPrevFivePages();
        return this;
    }
}

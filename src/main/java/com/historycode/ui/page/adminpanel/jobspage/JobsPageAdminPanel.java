package com.historycode.ui.page.adminpanel.jobspage;

import com.historycode.ui.component.adminPanel.modalAdminPanel.DeleteItemModal;
import com.historycode.ui.page.adminpanel.BasePageAdminPanel;
import com.historycode.ui.page.adminpanel.jobspage.modal.CreateEditJobModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


//TODO here i should use //? Like it is my main root
public class JobsPageAdminPanel extends BasePageAdminPanel {
    @FindBy(xpath = "//button[span[text()='Додати нову вакансію']]")
    WebElement addNewJobButton;

    @FindBy(xpath = "(//div[@class='ant-modal-content'])[2]")
    private WebElement editModalRootElement;

    @FindBy(xpath = "(//div[@class='ant-modal-content'])[1]")
    private WebElement deleteModalRootElement;

    @FindBy(xpath = "//div[@class='partners-page']//div[@class='partners-page-container']")
    private WebElement gridRootElement;

    JobsPageGridComponent jobsPageGridComponent;

    public JobsPageAdminPanel(WebDriver driver) {
        super(driver);
        jobsPageGridComponent = new JobsPageGridComponent(driver, gridRootElement);
    }

    public CreateEditJobModal clickEditJobById(int id) {
        JobsRowComponent jobToEdit = jobsPageGridComponent.getRowById(id);
        jobToEdit.clickEdit();
        return new CreateEditJobModal(driver, editModalRootElement);
    }

    public DeleteItemModal clickDeleteJobById(int id) {
        JobsRowComponent jobToDelete = jobsPageGridComponent.getRowById(id);
        jobToDelete.clickDelete();
        return new DeleteItemModal(driver, deleteModalRootElement);
    }

    public CreateEditJobModal clickAddNewJobButton() {
        addNewJobButton.click();
        return new CreateEditJobModal(driver, editModalRootElement);
    }

    public JobsPageAdminPanel clickNextPage() {
        jobsPageGridComponent = jobsPageGridComponent.clickNextPage();
        return this;
    }

    public JobsPageAdminPanel clickPrevPage() {
        jobsPageGridComponent = jobsPageGridComponent.clickPrevPage();
        return this;
    }

    public JobsPageAdminPanel clickPaginationItem(int index) {
        jobsPageGridComponent = jobsPageGridComponent.clickPaginationItem(index);
        return this;
    }

    public JobsPageAdminPanel clickNextFivePages() {
        jobsPageGridComponent = jobsPageGridComponent.clickNextFivePages();
        return this;
    }

    public JobsPageAdminPanel clickPrevFivePages() {
        jobsPageGridComponent = jobsPageGridComponent.clickPrevFivePages();
        return this;
    }
}

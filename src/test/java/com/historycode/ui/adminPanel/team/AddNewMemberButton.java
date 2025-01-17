package com.historycode.ui.adminPanel.team;

import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.page.adminpanel.teampage.editModal.CreateEditMemberModal;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddNewMemberButton extends TestRunnerWithAdmin {

    @Issue("115")
    @Test
    @Description("Verify the ability to create a new team member through the admin panel")
    public void verifyAdminPanelAddNewMemberButton() {
        CreateEditMemberModal createMemberModal = new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToTeamPage()
                .clickAddNewMemberButton();

        Assert.assertTrue(createMemberModal.isModalDisplayed(), "Modal window is not displayed");
    }

}
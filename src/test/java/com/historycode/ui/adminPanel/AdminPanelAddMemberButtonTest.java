package com.historycode.ui.adminPanel;

import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.page.adminpanel.teampage.editModal.EditMemberModal;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminPanelAddMemberButtonTest extends TestRunnerWithAdmin {


    @Issue("115")
    @Test
    @Description("Verify the ability to create a new team member through the admin panel")
    public void verifyAdminPanelAddMemberButtonTest() {
        EditMemberModal createMemberModal = new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToTeamPage()
                .clickAddNewMemberButton();

        Assert.assertTrue(createMemberModal.isModalDisplayed(), "Modal window is not displayed");
    }


}
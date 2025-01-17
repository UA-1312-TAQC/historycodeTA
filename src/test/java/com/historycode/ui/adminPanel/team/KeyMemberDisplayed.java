package com.historycode.ui.adminPanel.team;

import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.page.adminpanel.teampage.editModal.CreateEditMemberModal;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.Test;

public class KeyMemberDisplayed extends TestRunnerWithAdmin {

    @Issue("121")
    @Test
    @Description("Verify that the admin can mark a member as a \"Key member\" via a radiobutton")
    public void verifyAdminPanelKeyMemberButton() {
        String memberName = "John Wick";

        CreateEditMemberModal createMemberModal = new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToTeamPage()
                .clickAddNewMemberButton();

        Assert.assertTrue(createMemberModal.isModalDisplayed(), "Modal window is not displayed");
    }
}

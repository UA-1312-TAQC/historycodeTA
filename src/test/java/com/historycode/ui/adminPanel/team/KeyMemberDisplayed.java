package com.historycode.ui.adminPanel.team;

import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.page.adminpanel.teampage.TeamPageAdminPanel;
import com.historycode.ui.page.adminpanel.teampage.TeamRowComponent;
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
        String photo = "memberImage.jpg";
        String socialMedia = "Youtube";
        String socialMediaLink = "https://www.youtube.com/";
        boolean keyRole = true;

        TeamPageAdminPanel memberModal = new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToTeamPage()
                .clickAddNewMemberButton()
                .setName(memberName)
                .loadPhoto(photo)
                .addSocialMedia(socialMedia)
                .addSocialMediaLink(socialMediaLink)
                .setKeyMemberStatus(keyRole)
                .saveEditedMember()
                .closeEditMemberModal();

        TeamRowComponent teamMember = memberModal.getTeamPageGridComponent().findUserByName(memberName);

        if (teamMember == null) {
            memberModal = memberModal.clickLastPaginationItem();
            teamMember = memberModal.getTeamPageGridComponent().findUserByName(memberName);
        }
        Assert.assertNotNull(teamMember, String.format("The member %s is not found in the team grid", teamMember));

        boolean isKeyRoleAssigned = teamMember.getKeyMemberRole().isDisplayed();
        Assert.assertTrue(isKeyRoleAssigned, String.format("The member %s is not marked with a key role", teamMember));

        teamMember.clickDelete().clickOkButton();
    }
}

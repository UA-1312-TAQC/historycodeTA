package com.historycode.ui.adminPanel.team;

import com.historycode.ui.data_provider.enums.SocialMedia;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.page.adminpanel.teampage.TeamRowComponent;

import com.historycode.ui.testrunners.BaseTestRunnerWithAdmin;
import com.historycode.utils.CustomStringGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class KeyMemberDisplayedTest extends BaseTestRunnerWithAdmin {

    TeamRowComponent teamMember;

    @Issue("121")
    @Test
    @Description("Verify that the admin can mark a member as a \"Key member\" via a radiobutton")
    public void verifyAdminPanelKeyMemberButton() {
        String memberName = CustomStringGenerator.generateUserLastFirstName(7, 10);
        String photo = "memberImage.jpg";
        boolean keyRole = true;

        teamMember = new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToTeamPage()
                .clickAddNewMemberButton()
                .setName(memberName)
                .loadPhoto(photo)
                .addSocialMedia(SocialMedia.YOUTUBE.getName())
                .addSocialMediaLink(SocialMedia.YOUTUBE.getValidLink())
                .setKeyMemberStatus(keyRole)
                .saveEditedMember()
                .closeEditMemberModal()
                .findUserOnCurrentOrLastPage(memberName);
        Assert.assertNotNull(teamMember, String.format("The member %s is not found in the team grid", memberName));

        boolean isKeyRoleAssigned = teamMember.getKeyMemberRole().isDisplayed();
        Assert.assertTrue(isKeyRoleAssigned, String.format("The member %s is not marked with a key role", memberName));
    }

    @AfterMethod
    public void deleteMember(){
        teamMember.clickDelete().clickOkButton();
    }
}

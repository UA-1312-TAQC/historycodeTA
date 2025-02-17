package com.historycode.ui.adminPanel.team;

import com.historycode.ui.data_provider.enums.SocialMedia;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.page.adminpanel.teampage.TeamPageAdminPanel;
import com.historycode.ui.page.adminpanel.teampage.TeamRowComponent;
import com.historycode.ui.testrunners.BaseTestRunnerWithAdmin;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

import com.historycode.utils.CustomStringGenerator;

public class PositionMemberDisplayed extends BaseTestRunnerWithAdmin {

    TeamRowComponent teamMember;

    @Issue("118")
    @Test
    @Description("Verify that the admin can add multiple positions from the dropdown list")
    public void verifyAdminPanelPositionDisplayed() {
        String memberName = CustomStringGenerator.generateUserLastFirstName(7, 10);
        String photo = "memberImage.jpg";
        List<String> positions = List.of("Дизайнер", "SMM");

        TeamPageAdminPanel memberModal = new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToTeamPage()
                .clickAddNewMemberButton()
                .setName(memberName)
                .loadPhoto(photo)
                .addSocialMedia(SocialMedia.YOUTUBE.getName())
                .addSocialMediaLink(SocialMedia.YOUTUBE.getValidLink())
                .addPositions(positions)
                .saveEditedMember()
                .closeEditMemberModal();

        memberModal = memberModal.clickLastPaginationItem();

        memberModal.waitForRowToBeVisible(memberName);

        teamMember = memberModal.getTeamPageGridComponent().findUserByName(memberName);

        Assert.assertNotNull(teamMember, String.format("The member %s is not found in the team grid", memberName));

        List<String> actualPositions = teamMember.getPositions();
        System.out.println("Positions found for member: " + actualPositions);

        Assert.assertTrue(actualPositions.containsAll(positions),
                String.format("Positions '%s' are not fully displayed for the team member %s. Actual positions: %s",
                        positions, memberName, actualPositions));
    }

    @AfterMethod
    public void deleteMember(){
        teamMember.clickDelete().clickOkButton();
    }

}

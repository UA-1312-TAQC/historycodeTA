package com.historycode.ui.adminPanel.team;

import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.page.adminpanel.teampage.TeamPageAdminPanel;
import com.historycode.ui.page.adminpanel.teampage.TeamRowComponent;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class PositionMemberDisplayed extends TestRunnerWithAdmin {

    @Issue("118")
    @Test
    @Description("Verify that the admin can add multiple positions from the dropdown list")
    public void verifyAdminPanelPositionDisplayed() {
        String memberName = "John Wick";
        String photo = "memberImage.jpg";
        String socialMedia = "Youtube";
        String socialMediaLink = "https://www.youtube.com/";
        List<String> positions = List.of("SMM", "Дизайнер");

        TeamPageAdminPanel memberModal = new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToTeamPage()
                .clickAddNewMemberButton()
                .setName(memberName)
                .loadPhoto(photo)
                .addSocialMedia(socialMedia)
                .addSocialMediaLink(socialMediaLink)
                .addPositions(positions)
                .saveEditedMember()
                .closeEditMemberModal();

        driver.navigate().refresh();

        memberModal = new TeamPageAdminPanel(driver);

        TeamRowComponent teamMember = memberModal.getTeamPageGridComponent().findUserByName(memberName);

        if (teamMember == null) {
            memberModal = memberModal.clickLastPaginationItem();
            teamMember = memberModal.getTeamPageGridComponent().findUserByName(memberName);
        }

        Assert.assertNotNull(teamMember, String.format("The member %s is not found in the team grid", memberName));

        List<String> actualPositions = teamMember.getPositions();
        System.out.println("Positions found for member: " + actualPositions);

        Assert.assertTrue(actualPositions.containsAll(positions),
                String.format("Positions '%s' are not fully displayed for the team member %s. Actual positions: %s",
                        positions, memberName, actualPositions));

        teamMember.clickDelete().clickOkButton();
    }

}

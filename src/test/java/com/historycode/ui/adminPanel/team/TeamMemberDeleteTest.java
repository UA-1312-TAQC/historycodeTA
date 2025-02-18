package com.historycode.ui.adminPanel.team;

import com.historycode.ui.data_provider.enums.SocialMedia;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.page.adminpanel.teampage.TeamPageAdminPanel;
import com.historycode.ui.page.adminpanel.teampage.TeamRowComponent;
import com.historycode.ui.testrunners.BaseTestRunnerWithAdmin;
import com.historycode.utils.CustomStringGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamMemberDeleteTest extends BaseTestRunnerWithAdmin {

    TeamRowComponent target;

    String teamMemberName;


    @BeforeMethod
    public void createTeamMember(){
        teamMemberName = CustomStringGenerator.generateUserLastFirstName(7, 10);
        target = new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToTeamPage()
                .clickAddNewMemberButton()
                .setName(teamMemberName)
                .loadPhoto("TeamMemberImage.png")
                .addSocialMedia(SocialMedia.LINKEDIN.getName())
                .addSocialMediaLink(SocialMedia.LINKEDIN.getValidLink())
                .saveEditedMember()
                .closeEditMemberModal()
                .findUserOnCurrentOrLastPage(teamMemberName);
    }

    @Test
    @Issue("122")
    @Epic("(Epic #5) Admin/other pages")
    @Story("95")
    @Description("Verify that the admin can delete the team member")
    public void deleteTeamMemberTest() {
        target.clickDelete().clickOkButton();
        TeamRowComponent res = new TeamPageAdminPanel(driver)
                .findUserOnCurrentOrLastPage(teamMemberName);
        Assert.assertNull(res, "The user is present in the grid after deleting");
    }
}

package com.historycode.ui.adminPanel.team;

import com.historycode.ui.data_provider.enums.SocialMedia;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.page.adminpanel.teampage.TeamPageAdminPanel;
import com.historycode.ui.page.adminpanel.teampage.TeamRowComponent;
import com.historycode.ui.testrunners.BaseTestRunnerWithAdmin;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamMemberDeleteTest extends BaseTestRunnerWithAdmin {

    TeamRowComponent target;

    String teamMemberName;


    @BeforeMethod
    public void createTeamMember(){
        teamMemberName = RandomStringUtils.randomAlphabetic(7) + " " + RandomStringUtils.randomAlphabetic(10);
        TeamPageAdminPanel res= new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToTeamPage()
                .clickAddNewMemberButton()
                .setName(teamMemberName)
                .loadPhoto("TeamMemberImage.png")
                .addSocialMedia(SocialMedia.LINKEDIN.getName())
                .addSocialMediaLink(SocialMedia.LINKEDIN.getValidLink())
                .saveEditedMember()
                .closeEditMemberModal();
        if(res.getTeamPageGridComponent().findUserByName(teamMemberName) == null)
            res = res.clickLastPaginationItem();
        target = res.getTeamPageGridComponent().findUserByName(teamMemberName);
    }


    @Test
    @Issue("122")
    @Epic("(Epic #5) Admin/other pages")
    @Story("95")
    @Description("Verify that the admin can delete the team member")
    public void deleteTeamMemberTest() {
        target.clickDelete().clickOkButton();
        TeamPageAdminPanel res = new TeamPageAdminPanel(driver);
        if(res.getTeamPageGridComponent().findUserByName(teamMemberName) == null)
            res = res.clickLastPaginationItem();
        Assert.assertNull(res.getTeamPageGridComponent().findUserByName(teamMemberName));
    }
}

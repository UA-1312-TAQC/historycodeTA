package com.historycode.ui.adminPanel.team;

import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.page.adminpanel.teampage.TeamPageAdminPanel;
import com.historycode.ui.page.adminpanel.teampage.TeamRowComponent;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TeamMemberCreationTest extends TestRunnerWithAdmin {

    @Issue("117")
    @Epic("(Epic #5) Admin/other pages")
    @Story("95")
    @Test
    @Description("Verify that the new team member is immediately displayed in the list of team members")
    public void testTeamMemberPresentAfterCreation(){
        String teamMember = RandomStringUtils.randomAlphabetic(7) + " " + RandomStringUtils.randomAlphabetic(10);
        TeamPageAdminPanel res= new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToTeamPage()
                .clickAddNewMemberButton()
                .setName(teamMember)
                .loadPhoto("TeamMemberImage.png")
                .addSocialMedia("LinkedIn")
                .addSocialMediaLink("https://ua.linkedin.com/")
                .saveEditedMember()
                .closeEditMemberModal();
        if(res.getTeamPageGridComponent().findUserByName(teamMember) == null)
            res = res.clickLastPaginationItem();
        TeamRowComponent result = res.getTeamPageGridComponent().findUserByName(teamMember);
        Assert.assertNotNull(result, String.format("User with name %s was not found after creation", teamMember));
        result.clickDelete().clickOkButton();
    }
}

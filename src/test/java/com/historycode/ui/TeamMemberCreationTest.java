package com.historycode.ui;

import com.historycode.ui.page.adminpanel.historycodepage.HistoryCodePage;
import com.historycode.ui.page.adminpanel.teampage.TeamPageAdminPanel;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import com.historycode.utils.StringGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TeamMemberCreationTest extends TestRunnerWithAdmin {



    @Issue("117")
    @Story("95")
    @Test
    @Description("Verify that the new team member is immediately displayed in the list of team members")
    public void testTeamMemberPresentAfterCreation(){
        login();
        String teamMember = StringGenerator.generateUserData(7, 10);
       // TeamPageGridComponent res
        TeamPageAdminPanel res= new HistoryCodePage(driver)
                .getAdminMenuBar()
                .goToTeamPage()
                .clickAddNewMemberButton()
                .setName(teamMember)
                .loadPhoto("src/test/resources/TeamMemberImage.png")
                .addSocialMedia("LinkedIn")
                .addSocialMediaLink("https://ua.linkedin.com/")
                .saveEditedMember()
                .closeEditMemberModal();
        if(res.getTeamPageGridComponent().findUserByName(teamMember) == null)
            res = res.clickLastPaginationItem();
        Object result = res.getTeamPageGridComponent().findUserByName(teamMember);
        Assert.assertNotNull(result, String.format("User with name %s was not found after creation", teamMember));
    }

}

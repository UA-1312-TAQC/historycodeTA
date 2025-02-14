package com.historycode.ui.adminPanel.team;

import com.historycode.ui.data_provider.enums.SocialMedia;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.page.adminpanel.teampage.TeamPageAdminPanel;
import com.historycode.ui.page.adminpanel.teampage.TeamRowComponent;
import com.historycode.ui.page.adminpanel.teampage.TeamSocialMediaComponent;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.historycode.ui.data_provider.StreetCodeDP;

import java.util.List;

public class TeamMemberCreationTest extends TestRunnerWithAdmin{

    TeamRowComponent targetTeamMember;

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
                .addSocialMedia(SocialMedia.LINKEDIN.getName())
                .addSocialMediaLink(SocialMedia.LINKEDIN.getValidLink())
                .saveEditedMember()
                .closeEditMemberModal();
        if(res.getTeamPageGridComponent().findUserByName(teamMember) == null)
            res = res.clickLastPaginationItem();
        targetTeamMember = res.getTeamPageGridComponent().findUserByName(teamMember);
        Assert.assertNotNull(targetTeamMember, String.format("User with name %s was not found after creation", teamMember));
    }

    @Issue("123")
    @Epic("(Epic #5) Admin/other pages")
    @Story("95")
    @Description("Verify that the admin can add 8 social links to the team member")
    @Test(dataProvider = "socialMediaDataProvider",  dataProviderClass = StreetCodeDP.class)
    public void addDifferentSocialMediaTest(SocialMedia socialMedia){
        String teamMember = RandomStringUtils.randomAlphabetic(7) + " " + RandomStringUtils.randomAlphabetic(10);
        TeamPageAdminPanel res= new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToTeamPage()
                .clickAddNewMemberButton()
                .setName(teamMember)
                .loadPhoto("TeamMemberImage.png")
                .addSocialMedia(socialMedia.getName())
                .addSocialMediaLink(socialMedia.getValidLink())
                .saveEditedMember()
                .closeEditMemberModal();
        if(res.getTeamPageGridComponent().findUserByName(teamMember) == null)
            res = res.clickLastPaginationItem();
        targetTeamMember = res.getTeamPageGridComponent().findUserByName(teamMember);
        List<TeamSocialMediaComponent> result = targetTeamMember.getSocialMediaLinks();
        Assert.assertFalse(result.isEmpty(), "Team member has no added social media");
        Assert.assertEquals(result.get(0).getIcon(), socialMedia.getIcon(), "The social media icon is incorrect");
    }


    @AfterMethod
    public void deleteUser(){
        if(targetTeamMember != null){
            targetTeamMember.clickDelete().clickOkButton();
        }
    }
}

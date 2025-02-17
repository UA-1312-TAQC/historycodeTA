package com.historycode.ui.adminPanel.team;

import com.historycode.ui.component.adminPanel.modalAdminPanel.PopUpMessageComponent;
import com.historycode.ui.data_provider.enums.SocialMedia;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.page.adminpanel.teampage.TeamPageAdminPanel;
import com.historycode.ui.page.adminpanel.teampage.TeamRowComponent;
import com.historycode.ui.page.adminpanel.teampage.TeamSocialMediaComponent;
import com.historycode.ui.page.adminpanel.teampage.createEditModal.CreateEditMemberModal;
import com.historycode.ui.testrunners.BaseTestRunnerWithAdmin;
import com.historycode.utils.CustomStringGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.historycode.ui.data_provider.StreetCodeDP;

import java.util.List;

public class TeamMemberCreationTest extends BaseTestRunnerWithAdmin {
    private static final String SUCCESS_MESSAGE = "Члена команди успішно додано/оновлено!";
    TeamRowComponent targetTeamMember;

    CreateEditMemberModal modalWindow;

    @BeforeMethod
    public void openTeamMemberCreationModal(){
        modalWindow = new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToTeamPage()
                .clickAddNewMemberButton();
    }


    @Issue("117")
    @Epic("(Epic #5) Admin/other pages")
    @Story("95")
    @Test
    @Description("Verify that the new team member is immediately displayed in the list of team members")
    public void testTeamMemberPresentAfterCreation(){
        String teamMember = CustomStringGenerator.generateUserLastFirstName(7, 10);
        targetTeamMember = modalWindow
                .setName(teamMember)
                .loadPhoto("TeamMemberImage.png")
                .addSocialMedia(SocialMedia.LINKEDIN.getName())
                .addSocialMediaLink(SocialMedia.LINKEDIN.getValidLink())
                .saveEditedMember()
                .closeEditMemberModal()
                .findUserOnCurrentOrLastPage(teamMember);
        Assert.assertNotNull(targetTeamMember, String.format("User with name %s was not found after creation", teamMember));
    }

    @Issue("116")
    @Test
    @Description("Verify if the admin can add a new team member using only the mandatory fields")
    public void verifyAdminPanelAddMemberOnlyMandatoryFields() {
        String memberName = CustomStringGenerator.generateUserLastFirstName(7, 10);
        String photo = "memberImage.jpg";

        TeamPageAdminPanel res= modalWindow
                .setName(memberName)
                .loadPhoto(photo)
                .addSocialMedia(SocialMedia.YOUTUBE.getName())
                .addSocialMediaLink(SocialMedia.YOUTUBE.getValidLink())
                .saveEditedMember()
                .closeEditMemberModal();

        targetTeamMember = res.findUserOnCurrentOrLastPage(memberName);
        Assert.assertNotNull(targetTeamMember, String.format("The member %s is not found in the team grid", memberName));

        //Validate the success popup firstly
        PopUpMessageComponent popUpMessageComponent = res.getPopUpMessageComponent();
        Assert.assertTrue(popUpMessageComponent.isSuccessPopUpDisplayed(), "Success popup is not displayed.");

        //Then check the presence of the message
        String actualMessage = popUpMessageComponent.getSuccessMessage();
        Assert.assertEquals(actualMessage, SUCCESS_MESSAGE);
    }


    @Issue("123")
    @Epic("(Epic #5) Admin/other pages")
    @Story("95")
    @Description("Verify that the admin can add 8 social links to the team member")
    @Test(dataProvider = "socialMediaDataProvider",  dataProviderClass = StreetCodeDP.class)
    public void addDifferentSocialMediaTest(SocialMedia socialMedia){
        String teamMember = CustomStringGenerator.generateUserLastFirstName(7, 10);
        TeamPageAdminPanel res= modalWindow
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

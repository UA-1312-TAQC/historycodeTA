package com.historycode.ui.adminPanel.team;

import com.historycode.ui.component.adminPanel.modalAdminPanel.PopUpMessageComponent;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.page.adminpanel.teampage.TeamPageAdminPanel;
import com.historycode.ui.page.adminpanel.teampage.TeamRowComponent;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MandatoryFieldsMemberAdd extends TestRunnerWithAdmin {

    private static final String SUCCESS_MESSAGE = "Члена команди успішно додано/оновлено!";

    @Issue("116")
    @Test
    @Description("Verify if the admin can add a new team member using only the mandatory fields")
    public void verifyAdminPanelAddMemberOnlyMandatoryFields() {
        String memberName = "John Wick";
        String photo = "memberImage.jpg";
        String socialMedia = "Youtube";
        String socialMediaLink = "https://www.youtube.com/";

        TeamPageAdminPanel memberModal = new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToTeamPage()
                .clickAddNewMemberButton()
                .setName(memberName)
                .loadPhoto(photo)
                .addSocialMedia(socialMedia)
                .addSocialMediaLink(socialMediaLink)
                .saveEditedMember()
                .closeEditMemberModal();

        TeamRowComponent teamMember = memberModal.getTeamPageGridComponent().findUserByName(memberName);

        if (teamMember == null) {
            memberModal = memberModal.clickLastPaginationItem();
            teamMember = memberModal.getTeamPageGridComponent().findUserByName(memberName);
        }
        Assert.assertNotNull(teamMember, String.format("The member %s is not found in the team grid", memberName));

        //Validate the success popup firstly
        PopUpMessageComponent popUpMessageComponent = new PopUpMessageComponent(driver);
        Assert.assertTrue(popUpMessageComponent.isSuccessPopUpDisplayed(), "Success popup is not displayed.");

        //Then check the presence of the message
        String actualMessage = popUpMessageComponent.getSuccessMessage();
        Assert.assertEquals(actualMessage, SUCCESS_MESSAGE);

        teamMember.clickDelete().clickOkButton();
    }

}

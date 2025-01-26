package com.historycode.ui.adminPanel.team;

import com.historycode.ui.component.adminPanel.modalAdminPanel.SuccessPopupComponent;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.page.adminpanel.teampage.TeamPageAdminPanel;
import com.historycode.ui.page.adminpanel.teampage.TeamRowComponent;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MandatoryFieldsMemberAdd extends TestRunnerWithAdmin {

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
        Assert.assertNotNull(teamMember, String.format("The member %s is not found in the team grid", teamMember));

        //Validate the success popup firstly
        SuccessPopupComponent successPopupComponent = new SuccessPopupComponent(driver);
        Assert.assertTrue(successPopupComponent.isSuccessPopUpDisplayed(), "Success popup is not displayed.");

        String actualMessage = successPopupComponent.getSuccessMessage();
        Assert.assertEquals(actualMessage, "Члена команди успішно додано/оновлено!", "Unexpected success message.");

        teamMember.clickDelete().clickOkButton();
    }

}

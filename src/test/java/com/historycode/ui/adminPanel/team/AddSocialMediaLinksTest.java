package com.historycode.ui.adminPanel.team;

import com.historycode.ui.data_provider.enums.SocialMedia;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.page.adminpanel.teampage.TeamPageAdminPanel;
import com.historycode.ui.page.adminpanel.teampage.TeamRowComponent;
import com.historycode.ui.page.adminpanel.teampage.createEditModal.CreateEditMemberModal;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddSocialMediaLinksTest extends TestRunnerWithAdmin {
    @Issue("123")
    @Epic("(Epic #5) Admin/other pages")
    @Story("95")
    @Test
    @Description("Verify that the admin can add 8 social links to the team member")
    public void testTeamMemberPresentAfterCreation(){
        String teamMember = RandomStringUtils.randomAlphabetic(7) + " " + RandomStringUtils.randomAlphabetic(10);
        int socialMediaLimiter = 8;
        CreateEditMemberModal res= new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToTeamPage()
                .clickAddNewMemberButton()
                .setName(teamMember)
                .loadPhoto("TeamMemberImage.png");
        for(SocialMedia socialMedia : SocialMedia.values()){
            socialMedia.getName();
            socialMedia.getValidLink();//add social media
            socialMediaLimiter--;
            if(socialMediaLimiter == 0)
                break;//if there are more social media than we need
        }
    }

}

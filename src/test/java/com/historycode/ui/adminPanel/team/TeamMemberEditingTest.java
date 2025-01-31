package com.historycode.ui.adminPanel.team;

import com.historycode.ui.data_provider.enums.SocialMedia;
import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.page.adminpanel.teampage.TeamRowComponent;
import com.historycode.ui.page.adminpanel.teampage.createEditModal.CreateEditMemberModal;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import com.historycode.utils.ImageProcessor;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.Base64;


@Slf4j
public class TeamMemberEditingTest extends TestRunnerWithAdmin {

    TeamRowComponent targetTeamMember;
    String teamMemberName;

    String teamMemberDescription;
    @BeforeMethod
    public void createTeamMemberForEditing(){
        teamMemberName = RandomStringUtils.randomAlphabetic(7) + " " + RandomStringUtils.randomAlphabetic(10);
        teamMemberDescription = RandomStringUtils.randomAlphabetic(20);
        targetTeamMember =
                new HistoryCodesAdminPanelPage(driver)
                .getAdminMenuBar()
                .goToTeamPage()
                .clickAddNewMemberButton()
                .setName(teamMemberName)
                .setDescription(teamMemberDescription)
                .loadPhoto("TeamMemberImage.png")
                .addSocialMedia(SocialMedia.LINKEDIN.getName())
                .addSocialMediaLink(SocialMedia.LINKEDIN.getValidLink())
                .saveEditedMember()
                .closeEditMemberModal()
                //.clickLastPaginationItem()
                .getTeamPageGridComponent()
                .findUserByName(teamMemberName);
    }

    @Test
    @Issue("120")
    @Epic("(Epic #5) Admin/other pages")
    @Story("95")
    @Description("Verify that the admin can edit the team member name")
    public void EditTeamMemberNameTest(){
       String newTeamMemberName =  RandomStringUtils.randomAlphabetic(7) + " " + RandomStringUtils.randomAlphabetic(11);
       targetTeamMember
                .clickEdit()
                .setName(newTeamMemberName)
                .saveEditedMember()
                .closeEditMemberModalWithoutGridRefresh();
        CreateEditMemberModal res = targetTeamMember.clickEdit();
        String actual = res.getName();
        res.closeEditMemberModalWithoutGridRefresh();
        Assert.assertEquals(newTeamMemberName, actual);
    }

    @Test
    @Issue("120")
    @Story("95")
    @Epic("(Epic #5) Admin/other pages")
    @Description("Verify that the admin cannot set the team member name longer than 41 character")
    public void EditTeamMemberSetTooLongNameTest(){
        String newTeamMemberName =  RandomStringUtils.randomAlphabetic(50);
        targetTeamMember
                .clickEdit()
                .setName(newTeamMemberName)
                .saveEditedMember()
                .closeEditMemberModalWithoutGridRefresh();
        CreateEditMemberModal res = targetTeamMember.clickEdit();
        String actual = res.getName();
        res.closeEditMemberModalWithoutGridRefresh();
        Assert.assertEquals(newTeamMemberName.substring(0,41), actual);
    }


    @Test
    @Issue("120")
    @Story("95")
    @Epic("(Epic #5) Admin/other pages")
    @Description("Verify that the admin cannot set an empty team member name")
    public void EditTeamMemberNameSetEmptyTest(){
        targetTeamMember
                .clickEdit()
                .setName("")
                .saveEditedMember()
                .closeEditMemberModalWithoutGridRefresh();
        CreateEditMemberModal res = targetTeamMember.clickEdit();
        String actual = res.getName();
        res.closeEditMemberModalWithoutGridRefresh();
        Assert.assertEquals(teamMemberName, actual);
    }

    @Test
    @Issue("120")
    @Story("95")
    @Epic("(Epic #5) Admin/other pages")
    @Description("Verify that the admin can edit the team member description")
    public void EditTeamMemberDescriptionTest(){
        String newTeamMemberDescription =  RandomStringUtils.randomAlphabetic(50);
        targetTeamMember
                .clickEdit()
                .setDescription(newTeamMemberDescription)
                .saveEditedMember()
                .closeEditMemberModalWithoutGridRefresh();
        CreateEditMemberModal res = targetTeamMember.clickEdit();
        String actual = res.getDescription();
        res.closeEditMemberModalWithoutGridRefresh();
        Assert.assertEquals(newTeamMemberDescription, actual);
    }

    @Test
    @Issue("120")
    @Story("95")
    @Epic("(Epic #5) Admin/other pages")
    @Description("Verify that the admin cannot set the team member description longer than 70 character")
    public void EditTeamMemberDescriptionSetTooLongTest(){
        String newTeamMemberDescription =  RandomStringUtils.randomAlphabetic(100);
        targetTeamMember
                .clickEdit()
                .setDescription(newTeamMemberDescription)
                .saveEditedMember()
                .closeEditMemberModalWithoutGridRefresh();
        CreateEditMemberModal res = targetTeamMember.clickEdit();
        String actual = res.getDescription();
        res.closeEditMemberModalWithoutGridRefresh();
        Assert.assertEquals(newTeamMemberDescription.substring(0,70), actual);
    }

    @Test
    @Issue("120")
    @Story("95")
    @Epic("(Epic #5) Admin/other pages")
    @Description("Verify that the admin can delete the team member description")
    public void EditTeamMemberDescriptionSetEmptyTest(){
        targetTeamMember
                .clickEdit()
                .setDescription("")
                .saveEditedMember()
                .closeEditMemberModalWithoutGridRefresh();
        CreateEditMemberModal res = targetTeamMember.clickEdit();
        String actual = res.getDescription();
        res.closeEditMemberModalWithoutGridRefresh();
        Assert.assertTrue(actual.isEmpty());
    }

    @Test
    @Issue("120")
    @Story("95")
    @Epic("(Epic #5) Admin/other pages")
    @Description("Verify that the admin can edit the team member photo")
    public void editPhotoTest() {
        CreateEditMemberModal modal;
        String resultPhoto;

        targetTeamMember.clickEdit()
                .loadPhoto("memberImage.jpg")
                .saveEditedMember()
                .closeEditMemberModalWithoutGridRefresh();
        modal = targetTeamMember.clickEdit();
        targetTeamMember.sleep(5000);
        resultPhoto = modal.getRefreshedPhotoWindowComponent()
                                    .getEncodedPhoto();
        modal.closeEditMemberModalWithoutGridRefresh();
        log.debug(ImageProcessor.clearStringMetadata(resultPhoto));
        log.debug(ImageProcessor.encodeImage("src/test/resources/memberImage.jpg"));
        log.debug(ImageProcessor.encodeImage("src/test/resources/TeamMemberImage.png"));
        Assert.assertTrue(ImageProcessor.compareEncodedAndNormalImage("src/test/resources/memberImage.jpg", resultPhoto),
                "New photo is incorrect or not shown in the modal window");
    }

    @AfterMethod
    public void deleteTeamMember(){
        targetTeamMember.clickDelete().clickOkButton();
    }

}

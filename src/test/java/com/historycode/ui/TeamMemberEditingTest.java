package com.historycode.ui;

import com.historycode.ui.page.adminpanel.historycodePage.HistoryCodesAdminPanelPage;
import com.historycode.ui.page.adminpanel.teampage.TeamRowComponent;
import com.historycode.ui.page.adminpanel.teampage.editModal.EditMemberModal;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.*;

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
                .addSocialMedia("LinkedIn")
                .addSocialMediaLink("https://ua.linkedin.com/")
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
        EditMemberModal res = targetTeamMember.clickEdit();
        String actual = res.getName();
        Assert.assertEquals(newTeamMemberName, actual);
        res.closeEditMemberModalWithoutGridRefresh();
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
        EditMemberModal res = targetTeamMember.clickEdit();
        String actual = res.getName();
        Assert.assertEquals(newTeamMemberName.substring(0,41), actual);
        res.closeEditMemberModalWithoutGridRefresh();
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
        EditMemberModal res = targetTeamMember.clickEdit();
        String actual = res.getName();
        Assert.assertEquals(teamMemberName, actual);
        res.closeEditMemberModalWithoutGridRefresh();
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
        EditMemberModal res = targetTeamMember.clickEdit();
        String actual = res.getDescription();
        Assert.assertEquals(newTeamMemberDescription, actual);
        res.closeEditMemberModalWithoutGridRefresh();
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
        EditMemberModal res = targetTeamMember.clickEdit();
        String actual = res.getDescription();
        System.out.println(newTeamMemberDescription);
        Assert.assertEquals(newTeamMemberDescription.substring(0,70), actual);
        res.closeEditMemberModalWithoutGridRefresh();
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
        EditMemberModal res = targetTeamMember.clickEdit();
        String actual = res.getDescription();
        Assert.assertTrue(actual.isEmpty());
        res.closeEditMemberModalWithoutGridRefresh();
    }

    @AfterMethod
    public void deleteTeamMember(){
        targetTeamMember.clickDelete().clickOkButton();
    }

}

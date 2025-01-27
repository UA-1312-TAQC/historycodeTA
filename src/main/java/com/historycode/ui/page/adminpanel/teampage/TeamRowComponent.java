package com.historycode.ui.page.adminpanel.teampage;

import com.historycode.ui.component.BaseComponent;
import com.historycode.ui.component.adminPanel.modalAdminPanel.DeleteItemModal;
import com.historycode.ui.page.adminpanel.teampage.createEditModal.CreateEditMemberModal;
import lombok.Getter;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class TeamRowComponent extends BaseComponent {
    @FindBy(xpath = "./td[1]//div[@class='team-table-item-name']//p")
    protected WebElement lastFirstName;
    @Getter
    @FindBy(xpath = "./td[1]//div[@class='team-table-item-name']//span")
    protected WebElement keyMemberRole;
    @FindBy(xpath = "./td[2]//div[@class='team-table-item-name']//p")
    protected WebElement position;
    protected List<WebElement> positions;
    @FindBy(xpath = "./td[3]//div[@class='team-table-item-name']//p")
    protected WebElement description;
    @FindBy(xpath = "./td[4]//img")
    protected WebElement photo;
    @FindBy(xpath = "./td[5]//a")
    protected List<WebElement> socialMediaElements;
    @FindBy(xpath = "./td[6]//span[contains(@class, 'delete')]")
    protected WebElement deleteAction;
    @FindBy(xpath = "./td[6]//span[contains(@class, 'edit')]")
    protected WebElement editAction;
    @FindBy(xpath = "//h2[contains(text(),'Редагувати')]/ancestor::div[@class = 'ant-modal-content']")
    protected WebElement editModalRoot;
    @FindBy(xpath = "//p[contains(text(),'видалити')]/ancestor::div[@class = 'ant-modal-content']")
    protected WebElement deleteModalRoot;

    private List<TeamSocialMediaComponent> socialMediaLinks;
    private List<String> positionTexts;

    public TeamRowComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getLastFirstName() {
        return lastFirstName.getText();
    }

    public String getPosition() {
        return position.getText();
    }

    public List<String> getPositions() {
        if (positionTexts == null) {
            positionTexts = new ArrayList<>();
            wait.until(ExpectedConditions.visibilityOfAllElements(positions));
            for (WebElement positionElement : positions) {
                String text = positionElement.getText().trim();
                if (!text.isEmpty()) {
                    positionTexts.add(text);
                }
            }
        }
        return positionTexts;
    }

    public String getDescription() {
        return description.getText();
    }

    public String getPhoto() {
        return photo.getDomAttribute("src");
    }

    public List<TeamSocialMediaComponent> getSocialMediaLinks() {
        if (socialMediaLinks == null) {
            socialMediaLinks = new ArrayList<>();
            for (WebElement element : socialMediaElements) {
                socialMediaLinks.add(new TeamSocialMediaComponent(driver, element));
            }
        }
        return socialMediaLinks;
    }

    @Step("Clicking delete button next to team member")
    public DeleteItemModal clickDelete() {
        actions.scrollToElement(lastFirstName).perform();
        deleteAction.click();
        return new DeleteItemModal(driver, deleteModalRoot);
    }


    public CreateEditMemberModal clickEdit() {
        scrollToElement(editAction);
        editAction.click();
        return new CreateEditMemberModal(driver, editModalRoot);
    }

    @Override
    public String toString(){
        return "Lastfirst name " + lastFirstName.getText() + " description " + description.getText();
    }
}

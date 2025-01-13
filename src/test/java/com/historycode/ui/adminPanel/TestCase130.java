package com.historycode.ui.adminPanel;

import com.historycode.ui.component.adminPanel.adminMenuBar.AdminMenuBarComponent;
import com.historycode.ui.elements.adminPanel.InputElement;
import com.historycode.ui.elements.adminPanel.LogoElement;
import com.historycode.ui.elements.adminPanel.TextAreaElement;
import com.historycode.ui.page.adminpanel.partnerspage.modal.CreatePartnersModal;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase130  extends TestRunnerWithAdmin {
    @BeforeMethod
    public void setupForTest() {
        login();
    }

    @Test
    @Issue("130")
    @Description("Verify that the admin can add a description to a partner's card")
    public void test130 () throws InterruptedException {

        AdminMenuBarComponent menuBar;

        menuBar = new AdminMenuBarComponent(
                driver, driver.findElement(By.xpath("//div[@class='BarContainer']")));

        CreatePartnersModal createModal = menuBar.goToPartnersPage().clickAddNewPartnersButton();

        InputElement name = createModal.getName();
        name.setInputField("Ivan PP");
        createModal.sleep(5000);

        TextAreaElement description = createModal.getDiscription();
        description.setTextArea("Ivan is cool man");
        createModal.sleep(5000);

        LogoElement logo = createModal.getLogo();
        logo.uploadLogo("com/historycode/ui/adminPanel/logo.jpeg");
        createModal.sleep(5000);





    }
}


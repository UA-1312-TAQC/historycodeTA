package com.historycode.ui;


import com.historycode.ui.page.homePage.HomePage;
import com.historycode.ui.testrunners.BaseTestRunner;
import com.historycode.ui.testrunners.TestRunnerWithAdmin;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminPanelSmokeTest extends TestRunnerWithAdmin {

    @Test
    public void testOpenPage(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}

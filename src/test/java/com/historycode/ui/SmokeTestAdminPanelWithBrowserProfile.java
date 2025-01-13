package com.historycode.ui;

import com.historycode.ui.testrunners.TestRunnerAdminWithUserProfile;
import org.testng.annotations.Test;

public class SmokeTestAdminPanelWithBrowserProfile extends TestRunnerAdminWithUserProfile {


    @Test
    public void testOpenPage(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    @Test
    public void testProfile(){
        driver.get("chrome://version/");
        try {
            Thread.sleep(25000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}

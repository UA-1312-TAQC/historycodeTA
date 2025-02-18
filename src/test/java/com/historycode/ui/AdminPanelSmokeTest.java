package com.historycode.ui;


import com.historycode.ui.testrunners.BaseTestRunnerWithAdmin;

import org.testng.annotations.Test;

public class AdminPanelSmokeTest extends BaseTestRunnerWithAdmin {

    @Test
    public void testOpenPage(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}

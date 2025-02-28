package com.historycode.cucumber.steps.adminpanel;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class BaseAdminPageSteps extends AdminPanelPages {

    @When("I navigate to the {string} tab")
    public void iNavigateToTab(String name) {
        navigateToTab(name);
        sleep(1);
    }

    @Given("User open the admin-panel page of the site and login admin")
    public void iLoginWithAdmin() {
        loginWithAdmin();
        sleep(1);
    }
}

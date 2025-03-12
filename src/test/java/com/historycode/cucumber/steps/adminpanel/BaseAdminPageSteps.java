package com.historycode.cucumber.steps.adminpanel;


import com.historycode.ui.page.partnerPage.PartnerPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
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

    @Then("I open the StreetCode page")
    public void iOpenStreetcodePage() {
        driver.get(provider.getBaseUIUrl());
    }

    @And("I navigate to the {string} tab on main page")
    public void iNavigateToTabInMainPage(String name) {
        PartnerPage basePage = new PartnerPage(driver);
        switch (name) {
            case "Партнери" ->basePage.openBurgerMenu().goToPartnerPage();
        }
    }
}

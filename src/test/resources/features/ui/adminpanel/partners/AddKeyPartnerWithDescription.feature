Feature: Create a not key partner item with test description

  As an admin
  I want to create a not key partner item with description
  So that it appears on the site with test description

  Background:
    Given User open the admin-panel page of the site and login admin

  Scenario: Successfully create a 'partner' item with test description
    When I navigate to the "Партнери" tab
    And I click on the Створити нового партнера button
    And I check in the keyPartner checkbox for partner
    And I fill in the "Title" field with "Test partner name" for partner
    And I fill in the "Image" field with "uploadfiles/logo.webp" for partner
    And I fill in the "Description" field with "Our cooker and guru" for partner
    And I click on the Зберегти button for partner
    And I click on the close button for partner
    Then I open the StreetCode page
    And I navigate to the "Партнери" tab on main page
    And I see key partner with title "Test partner name" description "Our cooker and guru"
    #And I delete partner with name "Test partner name" using feature "DeletePartner.feature"

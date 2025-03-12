Feature: Delete a Partner Item

  As an admin
  I want to delete a partner item
  So that it no longer appears on the site

  Background:
    Given User open the admin-panel page of the site and login admin
    And I create new partner with name "Test partner name" and with logo "uploadfiles/logo.webp"

  Scenario: Remove a partner from the "Партнери" block
    When I navigate to the "Партнери" tab
    And I find a partner with name "Test partner name"
    And I click on the Видалити button for partner and confirm deletion
    Then I should not see partner with name "Test partner name" in the list

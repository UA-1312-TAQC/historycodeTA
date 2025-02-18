Feature: Admin can delete a position

  Background:
    Given I am on the site
    And I log in as an admin

  Scenario: Verify that the admin can delete a position using the "trash bin" button
    Given I am logged in as an admin on the Стріткод page
    And at least one position exists in the position list
    When I click on the "Едітор" button in the left navigation panel
    And I navigate to the "Позиції" tab
    And I click on the "trash bin" button for a position
    And I confirm deletion by clicking the "OK" button
    Then the deleted position should no longer be visible in the position list

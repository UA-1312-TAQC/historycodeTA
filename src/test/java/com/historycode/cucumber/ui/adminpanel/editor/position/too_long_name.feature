Feature: Admin can manage positions
  # Issue #109: [Admin/Editor/Positions] Verify that the system blocks the admin's attempt to add more than 50 symbols in the "Назва" field

  Background:
    Given I am on the site "{siteUrl}"
    And I log in as an admin

  Scenario: Verify that the system blocks the admin's attempt to add more than 50 symbols in the "Назва" field
    When I click on the "Едітор" button in the left navigation panel
    And I navigate to the "Позиції" tab
    And I click on the "Додати нову позицію" button
    And I fill out the "Назва" field with "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWX"  # 51 characters
    Then I should see an error message saying "Filling out no more than 50 symbols is available"

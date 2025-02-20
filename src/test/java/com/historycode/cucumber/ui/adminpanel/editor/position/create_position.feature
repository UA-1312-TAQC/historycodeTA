Feature: Admin can manage positions
  # Issue #112: [Admin/Editor/Positions] Verify that the admin can add a new position with valid data

  Background:
    Given I am on the site "{siteUrl}"
    And I log in as an admin
    And I am on the "Позиції" tab

  Scenario: Verify that the admin can add a new position with valid data
    Given a new position with the title "{positionTitle}" is unique and valid
    When I click on the "Додати нову позицію" button
    And I fill out the "Назва" field with "{positionTitle}"
    And I save the new position by clicking the "Зберегти" button
    And I close the modal window by clicking the "x" button
    Then the newly added position should appear in the position list with the title "{positionTitle}"

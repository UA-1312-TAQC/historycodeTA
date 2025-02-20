Feature: Admin can edit a position
  # Issue #100: [Admin/Editor/Positions] Verify that the admin can edit existing positions using the "pencil" button

  Background:
    Given I am on the site "{siteUrl}"
    And I log in as an admin
    And at least one position "{existingPositionTitle}" exists in the position list

  Scenario: Verify that the admin can edit an existing position using the "pencil" button
    When I click on the "Едітор" button in the left navigation panel
    And I navigate to the "Позиції" tab
    And I click on the "pencil" button for the position titled "{existingPositionTitle}"
    And I fill out the "Назва" field with "{newPositionTitle}" in the modal window
    And I save the changes by clicking the "Зберегти" button
    And I close the modal window by clicking the "x" button
    Then the edited position should appear in the position list with the correct title "{newPositionTitle}"
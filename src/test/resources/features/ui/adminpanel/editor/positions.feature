Feature: Testing of positions tab on the editor page

  Background:
    Given I opened the admin panel and logged as admin.
    And I created position with name "Cucumber position".

  Scenario: Verify that the admin can edit existing positions using the "pencil" button
    When I click the "Едітор" button in the left navigation panel.
    And I click the "Positions" on the upper tab panel.
    And I click the "edit" action button on the row with "Cucumber position".
    And I fill the modal title field with "Cucumber new".
    And I click the modal "Зберегти" save button.
    And I click the modal "x" close button.
    Then edited "Position" "Cucumber position" is exist with new name "Cucumber new".

  Scenario: Verify that the system blocks the admin's attempt to add more than 50 symbols in the "Назва" field
    When I click the "Едітор" button in the left navigation panel.
    And I click the "Positions" on the upper tab panel.
    And I click the "Додати нову позицію" add button.
    And I fill the modal title field with "Cucumber position with non allowed length 51 symbol".
    Then entered title is less than 52 characters.

  Scenario: Verify that the admin can add a new position with valid data
    When I click the "Едітор" button in the left navigation panel.
    And I click the "Positions" on the upper tab panel.
    And I click the "Додати нову позицію" add button.
    And I fill the modal title field with "Cucumber valid position".
    And I click the modal "Зберегти" save button.
    And I click the modal "x" close button.
    Then created "Position" "Cucumber valid position" is exist.

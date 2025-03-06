Feature: Testing of contexts tab on the editor page

  Background:
    Given I opened the admin panel and logged as admin.

  Scenario: Verify that a new context can be created in the admin panel editor
    When I click the "Едітор" button in the left navigation panel.
    And I click the "Contexts" on the upper tab panel.
    And I click the "Додати контекст" add button.
    Then modal window is displayed.
    And modal has title "Додати контекст".

  Scenario: Verify that context list is displayed
    When I click the "Едітор" button in the left navigation panel.
    And I click the "Contexts" on the upper tab panel.
    Then table has columns "Назва, Дії".
    And table has "edit" action.
    And table has "delete" action.

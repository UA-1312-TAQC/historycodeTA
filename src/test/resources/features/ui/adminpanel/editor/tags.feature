Feature: Testing of tags tab on the editor page

  Background:
    Given I opened the admin panel and logged as admin.

  Scenario: Verify that tag list is displayed
    When I click the "Едітор" button in the left navigation panel.
    And I click the "Tags" on the upper tab panel.
    Then list of "tags" is displayed.
    And table has columns "Назва, Дії".
    And table has "edit" action.
    And table has "delete" action.

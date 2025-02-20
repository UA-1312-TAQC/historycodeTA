Feature: Removing a partner from the StreetCode page

  Background:
    Given I am logged in as an admin
    And I open the StreetCode page for editing
    And At least one partner is added on the StreetCode page

  Scenario: Remove a partner from the "Партнери" block
    When I scroll down to the "Партнери" block
    And I click on the "x" near the partner's name
    Then The partner should be removed from the "Партнери" block
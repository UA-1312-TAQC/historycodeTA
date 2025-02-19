Feature: Adding a partner to the StreetCode page

  Background:
    Given I am logged in as an admin
    And I open the new StreetCode page or the StreetCode page for editing

  Scenario: Add a partner to the "Партнери" block
    When I scroll down to the "Партнери" block
    And I click on the "Додати" button
    And I fill mandatory fields "Назва" and "Лого" in the modal window
    And I click on the "Зберегти" button
    Then The added partner should be displayed in the dropdown in the input field
    And The partner should be saved on the "Партнери" page
Feature: Adding a partner without a logo

  Background:
    Given I am logged in as an admin
    And I open the new StreetCode page or the StreetCode page for editing

  Scenario: Attempt to add a partner without uploading a logo
    When I scroll down to the "Партнери" block
    And I click on the "Додати" button
    And I fill the "Назва" field but don't fill the "Лого" field in the modal window
    And I click on the "Зберегти" button
    And I click on the 'X' button
    Then The new partner should not be saved
    And The "Завантажте лого" warning message should be displayed
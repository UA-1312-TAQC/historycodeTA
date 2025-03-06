Feature: Testing of categories tab on the editor page

  Background:
    Given I opened the admin panel and logged as admin.

  Scenario: Verify admin can create new category
    When I clicked the "Едітор" button in the left navigation panel.
    And I clicked the "Додати нову категорію" add button.
    And I fill the modal title field with "Cucumber category".
    And I fill the image field with the image "uploadfiles/cat.png".
    And I click the modal "Зберегти" save button.
    And I click the modal "x" close button.
    Then created "Category" "Cucumber category" is exist.

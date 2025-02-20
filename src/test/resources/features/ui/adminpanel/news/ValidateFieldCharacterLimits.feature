Feature: Validate field character limits when creating a news article

  Scenario: Admin attempts to create a news article with character limits exceeded
    Given the user is logged in as admin
    When I go to the 'Новини' field and click on the 'Створити новину' button
    And I fill the title field with 101 characters and all other mandatory fields with valid data
    And I fill the link field with 201 characters and all other mandatory fields with valid data
    And I fill the text field with 15001 characters and all other mandatory fields with valid data
    And I click the "Зберегти" button
    Then characters that exceed the limit should not be added to the title field
    And characters that exceed the limit should not be added to the link field
    And I should see a pop-up message saying "Ви перевищили максимально доступну кількість символів"
    When I click the "Зберегти" button
    Then the text field should become empty
    And I should see a pop-up message saying "Не вдалося оновити/створити новину. Спробуйте ще раз."

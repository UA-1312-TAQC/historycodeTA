Feature: Create a news article with a future publication date

  Scenario: Admin creates a news article with a future publication date
    Given the user is logged in as an admin
    And the 'Новини' tab is opened
    When I click on the 'Створити новину' button
    And I fill in the mandatory fields with the following data:
      | Title                | Тестова новина                                                                 |
      | Link                 | test-link                                                                    |
      | Text                 | Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras et commodo ex. Pellentesque id sagittis ex. Morbi tincidunt volutpat ante, ut elementum turpis pulvinar et. |
      | Image                | image                                                                      |
    And I set the date to be one day in the future (Present date + 1 day)
    And I click on the "Зберегти" button
    Then the news item should be created successfully
    And the news item should not be published yet

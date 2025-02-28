Feature: Add news to the system

  Scenario: Admin successfully creates a new news article
    Given I am logged in as an admin
    When I navigate to the 'Новини' tab
    And I click on the 'Створити новину' button
    And I fill in the mandatory fields with the following data:
      | Title                | Тестова новина                                                                 |
      | Link                 | test-link                                                                    |
      | Text                 | Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras et commodo ex. Pellentesque id sagittis ex. Morbi tincidunt volutpat ante, ut elementum turpis pulvinar et. |
      | Image                | image                                                                      |
      | Date                 | Current date                                                               |
    And I click on "Зберегти"
    Then I should see the notification "Новина успішно додана/оновлена"
    And the new news article should be published

  Scenario: Admin deletes the news article
    Given I am logged in as an admin
    When I navigate to the 'Новини' tab
    And I find the news article with title "Тестова новина"
    And I click on the 'Delete' button for the news article
    Then the news article should be deleted
    And I should see a confirmation message

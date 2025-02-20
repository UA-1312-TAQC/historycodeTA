Feature: Delete an existing news article

  Scenario: Admin successfully deletes a news article
    Given the user is logged in as an admin
    And the 'Новини' tab is opened
    And the news has been created with the following data:
      | Title                | Тестова новина                                                                 |
      | Link                 | test-link                                                                    |
      | Text                 | Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras et commodo ex. Pellentesque id sagittis ex. Morbi tincidunt volutpat ante, ut elementum turpis pulvinar et. |
      | Image                | image                                                                      |
      | Date                 | Current date                                                               |
    When I click on the "trash bin" icon next to the news
    And I click on the "Підтвердити" button
    Then a confirmation dialog for deletion should appear
    And the news article should be deleted

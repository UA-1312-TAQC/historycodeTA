Feature: Link field validation when creating a news article

  Scenario: Admin enters invalid link characters while creating a news article
    Given the user is logged in as an admin
    And the 'Новини' tab is opened
    When I click on the 'Створити новину' button
    And I fill in the mandatory fields with the following data:
      | Title                | Тестова новина                                                                 |
      | Text                 | Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras et commodo ex. Pellentesque id sagittis ex. Morbi tincidunt volutpat ante, ut elementum turpis pulvinar et. |
      | Image                | image                                                                      |
      | Date                 | Current date                                                               |
    And I input the link "TESTLINK" into the link field
    And I click on the "Зберегти" button
    Then I should see the error message "Посилання має містити лише малі латинські літери, цифри та дефіс" below the link field in the modal window 'Додати новину'

  Scenario: Admin inputs Cyrillic characters in the link field
    Given the user is logged in as an admin
    And the 'Новини' tab is opened
    When I click on the 'Створити новину' button
    And I fill in the mandatory fields with the following data:
      | Title                | Тестова новина                                                                 |
      | Text                 | Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras et commodo ex. Pellentesque id sagittis ex. Morbi tincidunt volutpat ante, ut elementum turpis pulvinar et. |
      | Image                | image                                                                      |
      | Date                 | Current date                                                               |
    And I input the link "Тестлінк" into the link field
    And I click on the "Зберегти" button
    Then I should see the error message "Посилання має містити лише малі латинські літери, цифри та дефіс" below the link field in the modal window 'Додати новину'

  Scenario: Admin inputs non-allowed special characters in the link field
    Given the user is logged in as an admin
    And the 'Новини' tab is opened
    When I click on the 'Створити новину' button
    And I fill in the mandatory fields with the following data:
      | Title                | Тестова новина                                                                 |
      | Text                 | Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras et commodo ex. Pellentesque id sagittis ex. Morbi tincidunt volutpat ante, ut elementum turpis pulvinar et. |
      | Image                | image                                                                      |
      | Date                 | Current date                                                               |
    And I input the link "№\"?:*" into the link field
    And I click on the "Зберегти" button
    Then I should see the error message "Посилання має містити лише малі латинські літери, цифри та дефіс" below the link field in the modal window 'Додати новину'

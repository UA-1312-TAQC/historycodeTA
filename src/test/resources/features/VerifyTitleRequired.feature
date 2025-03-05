Feature: Verify that the 'title' field is required when updating news using the PUT method

  Background:
    Given User open the admin-panel page of the site and login admin

  Scenario: Verify the 'title' field is required
    When I navigate to the "Новини" tab
    And I click on the Створити новину button
    And Create the "Title" to ""
    And Create the "Link" to "ukraine-link"
    And Create the "Text" to 'Only the brave have happiness'
    And Create the "Image" to 'Додайте зображення'
    And Create the "Date" to 'Введіть дату'
    And I click on the Save button
    Then The news item should be error

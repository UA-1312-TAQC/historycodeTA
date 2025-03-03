Feature: Verify that the 'text' field is required when updating news using the PUT method

  Background:
    Given User open the admin-panel page of the site and login admin

  Scenario: Verify the 'text' field is required
    When I navigate to the "Новини" tab
    And I click on the Створити новину button
    And I will create the "Title" to "Ukraine"
    And I will create the "Link" to "ukraine-link"
    And I will create the "Text" to ''
    And I will create the "Image" to 'Додайте зображення'
    And I will create the "Date" to 'Введіть дату'
    And I click on the Save button
    Then The news item should be error
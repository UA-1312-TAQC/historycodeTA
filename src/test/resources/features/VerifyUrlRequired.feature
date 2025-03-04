Feature: Verify that the 'url' field is required when updating news using the PUT method

  Background:
    Given User open the admin-panel page of the site and login admin

  Scenario: Verify the 'url' field is required
    When I navigate to the "Новини" tab
    And I click on the Створити новину button
    And fill in the field "Title" to "History"
    And fill in the field "Link" to ''
    And fill in the field "Text" to "Test Text"
    And fill in the field "Image" to 'Додайте зображення'
    And fill in the field "Date" to 'Введіть дату'
    And I click on the Save button
    Then The news item should be error

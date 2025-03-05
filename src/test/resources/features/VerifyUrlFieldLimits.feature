Feature: Verify that the 'url' field does not accept values exceeding the 200-character limit when updating news using the PUT method

  Background:
    Given User open the admin-panel page of the site and login admin

  Scenario: Verify the 'url' field rejects values exceeding 200 characters
    When I navigate to the "Новини" tab
    And I click on the Створити новину button
    And I will create the "Title" to "Test Title"
    And I will create the "Link" to ""
    And I will create the "Text" to 'Only the brave have happiness'
    And I will create the "Image" to 'Додайте зображення'
    And I will create the "Date" to 'Введіть дату'
    And I click on the Save button
    Then The news item should be error

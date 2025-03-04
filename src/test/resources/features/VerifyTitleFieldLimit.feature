Feature: Verify that the 'title' field does not accept values exceeding the 100-character limit when updating news using the PUT method

  Background:
    Given User open the admin-panel page of the site and login admin

  Scenario: Verify the 'title' field rejects values exceeding 100 characters
    When I navigate to the "Новини" tab
    And I click on the Створити новину button
    And I will change the "Title" to "100"
    And I will change the "Link" to ""
    And I will change the "Text" to ""
    And I will change the "Image" to 'Додайте зображення'
    And I will change the "Date" to 'Введіть дату'
    And I click on the Save button
    And The test news item is deleted


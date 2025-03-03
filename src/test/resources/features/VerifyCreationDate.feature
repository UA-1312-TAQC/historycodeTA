Feature: Verify that the 'creationDate' field is required when updating news using the PUT method

  Background:
    Given User open the admin-panel page of the site and login admin

  Scenario: Verify the 'creationDate' field is required
    When I navigate to the "Новини" tab
    And I click on the Створити новину button
    And I will verify the "Title" to "Текстова новина"
    And I will verify the "link" to "test-link"
    And I will verify the "Text" to "Lorem ipsum dolor sit amet,.."
    And I will verify the "Image" to 'Додайте зображення'
    And I will verify the "Date" to ''
    And I click on the Save button
    Then The news item should be error
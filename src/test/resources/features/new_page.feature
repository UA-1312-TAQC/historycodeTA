Feature: Create a News Item

  As an admin
  I want to create a news item
  So that it appears on the site

  Background:
    Given User open the admin-panel page of the site and login admin

  Scenario: Successfully create a 'news' item
    When I navigate to the "Новини" tab
    And I click on the Створити новину button
    And I fill in the "Title" field with "Тестова новина"
    And I fill in the "Link" field with "test-link"
    And I click on the Зберегти button
    Then I should for field "Text" see the error notification 'Введіть текст'
    And I should for field "Image" see the error notification 'Додайте зображення'
    And I should for field "Date" see the error notification 'Введіть дату'

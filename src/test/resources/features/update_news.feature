Feature: News API Testing
  As a user
  I want to verify different API functionalities
  So that I can ensure correct behavior
  Background:
    Given User open the admin-panel page of the site and login admin

  Scenario: Successfully update an existing news item
    When I navigate to the "Новини" tab
    And I click on the Edite News
    And I will change the "Текстова новина" to "Ukraine"
    And I will change the "test-link" to "ukraine-link"
    And I will change the "Lorem ipsum dolor sit amet,.." to "The brave have happiness"
    And I will change the "Image" to 'Додайте зображення'
    And I will change the "Date" to 'Введіть дату'
    And I click on the Save button


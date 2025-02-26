Feature: Create a Partner Item

  As an admin
  I want to create a partner item
  So that it appears on the site

  Background:
    Given User open the admin-panel page of the site and login admin

  Scenario: Successfully create a 'partner' item
    When I navigate to the "Партнери" tab
    And I click on the Створити нового партнера button
    And I fill in the "Title" field with "Тестова назва партнера" for partner
    And I fill in the "Image" field with "uploadfiles/logo.webp" for partner
    And I click on the Зберегти button for partner
    Then I should see new partner with name "Тестова назва партнера" and logo "uploadfiles/logo.webp"
    #And The added partner should be displayed in the dropdown in the input field
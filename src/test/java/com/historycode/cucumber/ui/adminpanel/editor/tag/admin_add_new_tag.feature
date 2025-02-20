Feature: Admin can add a new tag

  Background:
    Given I am on the site
    And I log in as an admin

  Scenario: Verify that admin cannot save a tag without filling the mandatory field
    Given I am logged in as an admin on the admin panel page
    When I click on the "Едітор" button
    And I navigate to the "Теги" tab
    And I click on the "Додати новий тег" button
    And the modal window appears
    And I click on the "Зберегти" button without entering a title
    Then an error message should appear stating that the title is a mandatory field

  Scenario: Verify that admin can save a tag with a valid title (<= 50 symbols)
    Given I am logged in as an admin on the admin panel page
    When I click on the "Едітор" button
    And I navigate to the "Теги" tab
    And I click on the "Додати новий тег" button
    And the modal window

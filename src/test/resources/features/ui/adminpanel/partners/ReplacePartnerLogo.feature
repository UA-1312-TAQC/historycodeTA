Feature: Replacing partner logo

  Background:
    Given I go to the site
    And I log in as an admin
    And I open the new StreetCode page or the StreetCode page for editing
    And All mandatory fields are filled

  Scenario: Uploading a new partner logo replaces the previous one
    When I scroll down to the "Partners" block
    And I click on the "Додати" button
    And I fill the "Назва" field
    And I click on the "Виберіть чи перетягніть файл" button
    And I choose the first required file and press the "Відкрити" button
    And I click on the "Виберіть чи перетягніть файл" button again
    And I choose the second required file and press the "Відкрити" button
    Then Only the second photo should be uploaded, replacing the first one

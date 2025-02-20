Feature: Adding a partner with a logo

  Background:
    Given I go to the site
    And I log in as an admin
    And I open the new StreetCode page or the StreetCode page for editing
    And All mandatory fields are filled

  Scenario: Adding a partner with a logo and publishing it
    When I scroll down to the "Partners" block
    And I click on the "Додати" button
    And I fill the "Назва" field
    And I click on the "Виберіть чи перетягніть файл" button
    And I choose the required file and press the "Відкрити" button
    And I click on the "Зберегти" button
    And I click on the "Опублікувати" button
    And I open the StreetCode page
    Then The logo of the added partner card should be displayed properly and in accordance with the mockup
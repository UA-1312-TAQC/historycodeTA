Feature: Adding a partner with description

  Background:
    Given The "StreetCode page" is opened
    And I am logged in as an admin

  Scenario: Adding a partner with a description and publishing it
    When I scroll down to the "Partners" block
    And I click on the "Додати" button
    And I enter the description and all mandatory fields (Title and Logo)
    And I click the "Save" button
    And I click the "Publish" button
    And I open the StreetCode page
    Then The description of the added partner should be displayed properly and in accordance with the mockup

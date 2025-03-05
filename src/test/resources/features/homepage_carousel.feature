Feature: Carousel Functionality

  Scenario: Verify news carousel slides
    Given I open the Home Page
    When I scroll to the "news" carousel
    Then I should see at least one slide in the "news" carousel
    And I should be able to navigate through each slide in the "news" carousel

  Scenario: Verify news carousel navigation
    Given I open the Home Page
    When I scroll to the "news" carousel
    Then I should be able to navigate right and left in the "news" carousel
    And the "news" carousel should loop correctly from first to last slide

  Scenario: Verify team carousel slides
    Given I open the Home Page
    When I scroll to the "team" carousel
    Then I should see at least one slide in the "team" carousel
    And I should be able to navigate through each slide in the "team" carousel

  Scenario: Verify person carousel slides
    Given I open the Home Page
    When I scroll to the "person" carousel
    Then I should see at least one slide in the "person" carousel
    And I should be able to navigate through each slide in the "person" carousel

  Scenario: Find a news card by title
    Given I open the Home Page
    When I scroll to the "news" carousel
    Then I should find a card with title "Уроки Незламності — старт!" in the "news" carousel

  Scenario: Find a person card by name and validate its description
    Given I open the Home Page
    When I scroll to the "person" carousel
    Then I should find a card with title "Христина Скачківська-Сушко" in the "person" carousel and validate its description

  Scenario: Click 'toHistoryCode' button and verify navigation
    Given I open the Home Page
    When I scroll to the "person" carousel
    Then I should click the 'toHistoryCode' button in the "person" carousel and verify the page changes

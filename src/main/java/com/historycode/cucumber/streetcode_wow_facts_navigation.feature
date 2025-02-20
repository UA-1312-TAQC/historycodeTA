Feature: Verify different types of navigation on the StreetCode page

  Scenario: Interacting with navigation elements in the interesting facts block
    Given the StreetCode page is opened at "http://20.120.82.146/roman-ratushnyi"
    And at least 4 interesting facts are added
    And the interesting facts block is present on the page

    When I click on the 'previous' card (on the left side from the one that is in the front)
    Then the clicked card should move to the front

    When I click on the right arrow
    Then the cards should change along with the squares under the cards

    When I click on the left arrow
    Then the cards should change along with the squares under the cards

    When I click on any navigation indicator under the cards
    Then the cards should change
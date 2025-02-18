Feature: StreetCode Page Verification

  Scenario: Verify teaser text adheres to character limitations based on paragraph count
    Given the "StreetCode" page is open in a supported browser
    And the teaser text block is fully loaded and visible
    When I locate the teaser text block
    And I copy the teaser text and check the character count
    Then the teaser text should not exceed 520 characters for a single paragraph
    And the teaser text should not exceed 455 characters for two paragraphs
    And the text should be displayed without truncation or overflow

  Scenario: Verify modal window appears after clicking on "Задонатити" button
    Given the "StreetCode" page is open in a supported browser
    When I click on the "Задонатити" button
    Then a modal window with a donation option should be displayed

  Scenario: Verify 'Трохи ще' and 'Дещо менше' buttons functionality
    Given the "StreetCode" page is open in a supported browser
    And I scroll down to the 'Text and Video' block
    And the main text fits on one screen
    And there is more text available on the page
    When I click on the 'Трохи ще' button
    Then the text should extend and all available text should be visible
    And the 'Дещо менше' button should be displayed
    When I click on the 'Дещо менше' button
    Then the text should collapse to the previous size

  Scenario: Verify single fact display in 'Wow-факти' block
    Given the "StreetCode" page is open in a supported browser
    And one fact has been added
    When I scroll down to the "Wow-факти" block
    Then the fact should be displayed in the center of the "Wow-факти" block
    And navigation arrows and navigation indicators should be absent

  Scenario: Verify 'Wow-факти' navigation functionality
    Given the "StreetCode" page is open in a supported browser
    And 3 facts have been added
    When I go to the "Wow-факти" block
    And I click on the navigation arrows (e.g. right) 3 times
    Then the first fact should return to the center as it was at the beginning

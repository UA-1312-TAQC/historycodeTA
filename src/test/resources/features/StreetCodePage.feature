Feature: StreetCode Page Verification

  Scenario: Verify teaser text adheres to character limitations based on paragraph count
    Given the "StreetCode" page is open in a supported browser
    And the teaser text block is fully loaded and visible
    When User locates the teaser text block
    And User copies the teaser text and checks the character count
    Then the teaser text should not exceed 520 characters for a single paragraph
    And the teaser text should not exceed 455 characters for two paragraphs
    And the text should be displayed without truncation or overflow

  Scenario: Verify modal window appears after clicking on "Задонатити" button
    Given the "StreetCode" page is open in a supported browser
    When User clicks on the "Задонатити" button
    Then a modal window with a donation option should be displayed

  Scenario: Verify 'Трохи ще' and 'Дещо менше' buttons functionality
    Given the "StreetCode" page is open in a supported browser
    And User scrolls down to the 'Text and Video' block
    And the main text fits on one screen
    And there is more text available on the page
    When User clicks on the 'Трохи ще' button
    Then the text should extend and all available text should be visible
    And the 'Дещо менше' button should be displayed
    When User clicks on the 'Дещо менше' button
    Then the text should collapse to the previous size

  Scenario: Verify single fact display in 'Wow-факти' block
    Given the "StreetCode" page is open in a supported browser
    And one fact has been added
    When User scrolls down to the "Wow-факти" block
    Then the fact should be displayed in the center of the "Wow-факти" block
    And navigation arrows and navigation indicators should be absent

  Scenario: Verify 'Wow-факти' navigation functionality
    Given the "StreetCode" page is open in a supported browser
    And 3 facts have been added
    When User goes to the "Wow-факти" block
    And User clicks on the navigation arrows (e.g. right) 3 times
    Then the first fact should return to the center as it was at the beginning

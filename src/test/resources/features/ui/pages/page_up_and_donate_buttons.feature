Feature: Page UP and Donate Buttons

  Scenario: Page UP button visibility and functionality
    Given I am on the webpage
    And I scroll down past the second screen
    Then I should see the Page UP button
    When I click the Page UP button
    Then I should be scrolled to the top of the page
    And the Page UP button should be hidden

  Scenario: Donate button visibility and modal opening
    Given I am on the webpage
    Then I should see the Donate button
    When I click the Donate button
    Then a modal window with donate options should open

  Scenario: Closing the donate modal window
    Given the donate modal window is open
    When I click the "X" button
    Then the donate modal window should close

  Scenario: Automatic questionnaire appearance at the bottom of the page
    Given I am scrolling to the bottom of the StreetCode page
    Then a questionnaire should appear automatically

  Scenario: Donate modal should not reopen automatically if previously opened via button
    Given I have opened the donate modal via the Donate button
    And I have closed the donate modal
    When I reach the bottom of the StreetCode page
    Then the donate modal window should not be shown automatically

  Scenario: Donate modal should not reopen if previously viewed automatically
    Given I have viewed the donate modal window automatically
    And I have closed the donate modal
    When I reach the bottom of the StreetCode page again
    Then the donate modal window should not be displayed again

  Scenario: "Підтримати" button should be disabled until agreement checkbox is checked
    Given the donate modal window is open
    Then the "Підтримати" button should be disabled
    When I check the agreement checkbox
    Then the "Підтримати" button should be enabled

  Scenario: User can input a donation amount
    Given the donate modal window is open
    When I type a donation amount
    Then the typed amount should be displayed correctly

  Scenario: Clicking a donation amount button changes its color
    Given the donate modal window is open
    When I click on a donation amount button
    Then the button color should change

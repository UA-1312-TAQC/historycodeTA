Feature: Verify that all buttons in the text and video block of the StreetCode page are clickable


  Background:
    Given the StreetCode page is opened

  Scenario: Interacting with text and video elements on the StreetCode page

    When I click on the "Трохи ще" button
    Then all available text should be visible
    And the "Дещо менше" button should be present

    When I click on the "Дещо менше" button
    Then the text should collapse (fit on one screen)
    And the "Трохи ще" button should be present

    When I click on the play button
    And the video is playing
    Then I click on the pause button
    And the video is paused

    When I click on the source link under the text
    Then I should be redirected to the source page

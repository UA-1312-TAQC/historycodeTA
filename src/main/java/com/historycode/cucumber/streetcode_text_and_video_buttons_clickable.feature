Feature: Verify that all buttons in the text and video block of the StreetCode page are clickable

  Scenario: Interacting with text and video elements on the StreetCode page
    Given the StreetCode page is opened at "http://20.120.82.146/roman-ratushnyi"
    And the text and video block is present on the page
    And all elements from the mockup are present
    When I hover over any complex term
    Then a popover window should open with an explanation

    When I click on the "Трохи ще" button
    Then all available text should be visible
    And the "Дещо менше" button should be present

    When I click on the video
    And I click again
    Then the video should start playing
    And the video should be on pause

    When I click on the "Дещо менше" button
    Then the text should collapse (fit on one screen)
    And the "Трохи ще" button should be present

    When I click on the source link under the text
    Then I should be redirected to the source site
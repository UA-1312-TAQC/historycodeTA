Feature: Chronology Block Testing
  As a user
  I want to verify the chronology block functionality
  So that I can ensure correct behavior

  Scenario: Verify the user is able to see all related events as a timeline
    Given I am on the 'Chronology/Хронологія' block of the StreetCode page
    Then I should see the title "Хронологія"
    And a red timeline should be displayed with a grey square under each year
    And the square under the selected year should be bigger than the others
    When I click on another square
    Then the timeline should scroll to another event
    And each event should be located separately as an element of a camera film
    And each event should contain a period of time, Title, and Main Text
    And each event should have no more than 400 symbols including spaces
    And the background images should be a set of 3 default images
    And events should be displayed from oldest to newest, from left to right
    And the ordering of events should be as follows:
      | Period          | Considered As                |
      | Beginning of year  | 1st of January          |
      | Beginning of season | 1st day of its first month |
      | Beginning of month  | 1st day of this month  |
    And the camera film should react to a scroll:
      | Scroll Direction | Movement              |
      | Right           | Moves to newest events |
      | Left            | Moves to oldest events |
    When I click on a previous or next event
    Then the event cards should move, centering the clicked event
    And the central event should have a white outline

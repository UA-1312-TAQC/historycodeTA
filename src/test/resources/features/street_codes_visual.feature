Feature: StreetCodes Page Visual Consistency

  Scenario: Verify the visual consistency of the StreetCodes page with the design mockup
    Given I open the StreetCodes catalog page
    And I check the title CSS properties
    And I scroll to the end of the page
    Then all catalog items should have correct CSS properties
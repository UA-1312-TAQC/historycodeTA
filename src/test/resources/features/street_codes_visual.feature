Feature: Verify the visual consistency of the StreetCodes page with the design mockup

  Scenario: Verify StreetCodes catalog page styling
    Given I open the StreetCodes catalog page
    When I check the title CSS properties
      | property      | expectedValue                                        |
      | color        | rgba(221, 221, 221, 1)                                |
      | font-family  | "Closer Text", Roboto, "Helvetica Neue", sans-serif   |
      | font-size    | 96px                                                  |
    When I scroll to the end of the page
    Then all catalog items should have correct CSS properties
      | itemType   | property         | expectedValue                                       |
      | Name       | color            | rgba(255, 255, 255, 1)                              |
      | Name       | font-family      | "Closer Text", Roboto, "Helvetica Neue", sans-serif |
      | Name       | font-size        | 20px                                               |
      | Description | color           | rgba(255, 255, 255, 1)                              |
      | Description | font-family     | "Closer Text", Roboto, "Helvetica Neue", sans-serif |
      | Description | font-size       | 14px                                               |
      | Card       | background-color | rgba(137, 31, 22, 1)